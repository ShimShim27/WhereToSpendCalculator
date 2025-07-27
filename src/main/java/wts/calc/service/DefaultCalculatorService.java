package wts.calc.service;

import lombok.Data;
import wts.calc.base.service.CalculatorService;
import wts.calc.data.Wallet;
import wts.calc.data.WalletResult;
import wts.calc.exception.WalletException;
import wts.calc.util.CalculationUtil;
import wts.calc.util.StrUtil;
import wts.calc.util.WalletCalculationUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class DefaultCalculatorService implements CalculatorService {
    private static int COMPOUND_FREQUENCY = 365;

    private Date startDate;

    @Override
    public WalletResult getCheapestWallet(final BigDecimal spendAmount, final List<Wallet> wallets) {
        return getWalletsOrderedByExpensiveness(spendAmount, wallets, true).get(0);
    }

    @Override
    public WalletResult getMostExpensiveWallet(final BigDecimal spendAmount, final List<Wallet> wallets) {
        return getWalletsOrderedByExpensiveness(spendAmount, wallets, false).get(0);
    }

    @Override
    public List<WalletResult> getWalletsOrderedByExpensiveness( final BigDecimal spendAmount, final List<Wallet> wallets, final boolean sortByCheapest) {
        validateWallets(wallets);

       final List<WalletResult> results = wallets.stream().map(wallet->getResultFromWallet(spendAmount, wallet))
                .sorted((c, d) -> {
            int res = c.getFinalBalanceInATimePeriod().compareTo(d.getFinalBalanceInATimePeriod());
            if (res == 0) return 0;
            return res > 0 ? -1 : 1;
        }).collect(Collectors.toList());

       if (!sortByCheapest) {
           Collections.reverse(results);
       }

       return results;
    }

    private WalletResult getResultFromWallet(final BigDecimal spendAmount, final Wallet wallet) {
        final BigDecimal initialBalance = wallet.getBalance();
        final BigDecimal initialExpenditure = wallet.getSpendingFee().add(spendAmount);
        final BigDecimal cashback = WalletCalculationUtil.calculateCashback(initialExpenditure, wallet.getCashbackPercentage(), wallet.getMaxCashback(), wallet.getAlreadyCashedBack());
        final BigDecimal afterFeesAmount = WalletCalculationUtil.calculateAfterFeeAmount(initialBalance.add(cashback),initialExpenditure);
        final BigDecimal finalTotalExpenditure = initialBalance.subtract(afterFeesAmount);

        final Map<BigDecimal, BigDecimal> percentageMap = wallet.getInterestPercentageAndMinSpendMap();
        BigDecimal ratePercentage = BigDecimal.ZERO;
        final BigDecimal totalSpendForRates = initialExpenditure.add(wallet.getAlreadySpent());
        for (BigDecimal percentage : percentageMap.keySet()) {
            final BigDecimal spendingPercentageCriteria = percentageMap.get(percentage);
            if (CalculationUtil.isGreaterThan(totalSpendForRates, spendingPercentageCriteria) && CalculationUtil.isGreaterThan(percentage, ratePercentage)){
                ratePercentage = percentage;
            }
        }

        final BigDecimal finalBalanceAtTheEndOfTheMonth = WalletCalculationUtil.calculateInterestAndGetNewTotal(
                afterFeesAmount, ratePercentage, BigDecimal.valueOf(COMPOUND_FREQUENCY),
                getTimePeriod(wallet.isCompoundedDaily())
        );

        return new WalletResult(wallet, finalTotalExpenditure,  finalBalanceAtTheEndOfTheMonth);
    }

    private BigDecimal getTimePeriod(final boolean isCompoundedDaily) {
        if (!isCompoundedDaily) {
            return BigDecimal.ONE.divide(BigDecimal.valueOf(COMPOUND_FREQUENCY), CalculationUtil.DEFAULT_ACCURACY_SCALE, CalculationUtil.DEFAULT_ROUNDING_MODE);
        }

        final Date dateToUse = startDate == null? new Date():startDate;
        final GregorianCalendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(dateToUse);
        startCalendar.set(Calendar.HOUR, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND,0);
        startCalendar.set(Calendar.MILLISECOND, 0);

        final GregorianCalendar endCalendar = new GregorianCalendar();
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        endCalendar.set(Calendar.HOUR, 0);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND,0);
        endCalendar.set(Calendar.MILLISECOND, 0);

        return BigDecimal.valueOf(endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH) - 1)
                .divide(BigDecimal.valueOf(COMPOUND_FREQUENCY), CalculationUtil.DEFAULT_ACCURACY_SCALE, CalculationUtil.DEFAULT_ROUNDING_MODE);
    }


    private void validateWallets(final List<Wallet> wallets) throws WalletException {
        if (wallets.isEmpty()) {
            throw new WalletException(WalletException.NO_WALLETS);
        }

        if (wallets.stream().anyMatch(w -> StrUtil.isEmpty(w.getName()))){
            throw new WalletException(WalletException.NO_WALLET_NAME);
        }

        if (wallets.size() != wallets.stream().map(Wallet::getName).collect(Collectors.toSet()).size()) {
            throw new WalletException(WalletException.DUPLICATE_WALLET_NAME);
        }
    }
}
