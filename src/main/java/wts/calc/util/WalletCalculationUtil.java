package wts.calc.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import wts.calc.data.Wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WalletCalculationUtil {
    public static int MONETARY_ACCURACY_SCALE = 2;
    public static List<Wallet> getEmptyWalletList() {
        return new ArrayList<>();
    }

    public static BigDecimal calculateInterestAndGetNewTotal(final BigDecimal principal, final BigDecimal ratePercentage, final BigDecimal compoundedFrequency, final BigDecimal timePeriod) {
        final BigDecimal convertedRate = CalculationUtil.percentToDecimal(ratePercentage);
        final BigDecimal a = BigDecimal.ONE.add(convertedRate.divide(compoundedFrequency, CalculationUtil.DEFAULT_ACCURACY_SCALE, CalculationUtil.DEFAULT_ROUNDING_MODE));
        final BigDecimal b = BigDecimal.valueOf(Math.pow(a.doubleValue(), timePeriod.multiply(compoundedFrequency).doubleValue()));
        return toMonetaryFormat(principal.multiply(b));
    }

    public static BigDecimal calculateCashback(final BigDecimal targetAmount, final BigDecimal percentage, final BigDecimal maxCashback, final BigDecimal alreadyCashedBack) {
       final BigDecimal cashback = targetAmount.multiply(CalculationUtil.percentToDecimal(percentage));
       if (BigDecimal.ZERO.equals(maxCashback)){
           return toMonetaryFormat(cashback);
       }

       final BigDecimal remainingCashback = maxCashback.subtract(alreadyCashedBack);
       if (CalculationUtil.isGreaterThan(cashback, remainingCashback)){
            return toMonetaryFormat(remainingCashback);
       }

        return toMonetaryFormat(cashback);
    }

    public static BigDecimal calculateAfterFeeAmount(final BigDecimal principal, final BigDecimal fee) {
        return toMonetaryFormat(principal.subtract(fee));
    }

    private static BigDecimal toMonetaryFormat(final BigDecimal bigDecimal){
        return bigDecimal.setScale(MONETARY_ACCURACY_SCALE, CalculationUtil.DEFAULT_ROUNDING_MODE);
    }
}
