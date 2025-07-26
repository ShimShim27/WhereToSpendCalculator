package wts.calc.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import wts.calc.data.Wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WalletUtil {
    public static int MONETARY_ACCURACY_SCALE = 2;
    public static List<Wallet> getEmptyWalletList() {
        return new ArrayList<>();
    }




    public static BigDecimal calculateInterestAndGetNewTotal(final BigDecimal principal, final BigDecimal ratePercentage, final int compoundedFrequency, final int timePeriod) {
        final BigDecimal convertedRate = CalculationUtil.percentToDecimal(ratePercentage);
        final BigDecimal a = BigDecimal.ONE.add(convertedRate.divide(new BigDecimal(compoundedFrequency), CalculationUtil.DEFAULT_ACCURACY_SCALE, CalculationUtil.DEFAULT_ROUNDING_MODE));
        final BigDecimal b = a.pow(compoundedFrequency * timePeriod);
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

    private static BigDecimal toMonetaryFormat(final BigDecimal bigDecimal){
        return bigDecimal.setScale(MONETARY_ACCURACY_SCALE, CalculationUtil.DEFAULT_ROUNDING_MODE);
    }
}
