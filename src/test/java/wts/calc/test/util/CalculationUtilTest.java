package wts.calc.test.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wts.calc.util.CalculationUtil;

import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class CalculationUtilTest {


    @Test
    @DisplayName("Convert percentage to decimal")
    public void ConvertPercentageToDecimal_ShouldReturn_AccurateDecimalValue() {
        assertEquals(CalculationUtil.percentToDecimal(CalculationUtil.stringToNumerical("258")), CalculationUtil.stringToNumerical("2.58").setScale(CalculationUtil.DEFAULT_ACCURACY_SCALE, CalculationUtil.DEFAULT_ROUNDING_MODE));
        assertEquals(CalculationUtil.percentToDecimal(CalculationUtil.stringToNumerical("0.8")), CalculationUtil.stringToNumerical("0.008").setScale(CalculationUtil.DEFAULT_ACCURACY_SCALE, CalculationUtil.DEFAULT_ROUNDING_MODE));
        assertEquals(CalculationUtil.percentToDecimal(CalculationUtil.stringToNumerical("-98.5")), CalculationUtil.stringToNumerical("-0.985").setScale(CalculationUtil.DEFAULT_ACCURACY_SCALE, CalculationUtil.DEFAULT_ROUNDING_MODE));
    }

    @Test
    @DisplayName("Greater than equality test")
    public void GreaterThanEqualityTest_ShouldReturn_ProperResult() {
        assertTrue(CalculationUtil.isGreaterThan(CalculationUtil.stringToNumerical("28.22"), CalculationUtil.stringToNumerical("-9290.01")));
        assertTrue(CalculationUtil.isGreaterThan(CalculationUtil.stringToNumerical("28.22"), CalculationUtil.stringToNumerical("0.01")));
        assertFalse(CalculationUtil.isGreaterThan(CalculationUtil.stringToNumerical("28.22"), CalculationUtil.stringToNumerical("5001")));
        assertFalse(CalculationUtil.isGreaterThan(CalculationUtil.stringToNumerical("0.01"), CalculationUtil.stringToNumerical("0.01")));
    }
}
