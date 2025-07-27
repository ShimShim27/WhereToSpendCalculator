package wts.calc.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculationUtil {
    public static int DEFAULT_ACCURACY_SCALE = 100;
    public static RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;


    public static BigDecimal stringToNumerical(final String stringNum) {
        try {
            return new BigDecimal(stringNum);
        } catch (IllegalArgumentException e){
            return BigDecimal.ZERO;
        }
    }

    public static BigDecimal percentToDecimal(final BigDecimal percentage) {
        return percentage.divide(stringToNumerical("100"), DEFAULT_ACCURACY_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public static boolean isGreaterThan(final BigDecimal b1, final BigDecimal b2) {
        return b1.compareTo(b2) > 0;
    }

    public static boolean isLessThan(final BigDecimal b1, final BigDecimal b2) {
        return b1.compareTo(b2) < 0;
    }
}
