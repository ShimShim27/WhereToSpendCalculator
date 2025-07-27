package wts.calc.test.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wts.calc.data.Wallet;
import wts.calc.util.CalculationUtil;
import wts.calc.util.WalletUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WalletUtilTest {

    @Test
    @DisplayName("Get total balance")
    public void GetTotalBalance_ShouldReturn_TotalBalance() {
        final Wallet w1 = new Wallet();
        w1.setBalance(CalculationUtil.stringToNumerical("231.4"));
        final Wallet w2 = new Wallet();
        w2.setBalance(CalculationUtil.stringToNumerical("321.4"));
        final Wallet w3 = new Wallet();
        w3.setBalance(CalculationUtil.stringToNumerical("892"));
        final Wallet w4 = new Wallet();
        w4.setBalance(CalculationUtil.stringToNumerical("5.44"));


        assertEquals(CalculationUtil.stringToNumerical("1450.24"), WalletUtil.getTotalBalance(Arrays.asList(w1,w2,w3,w4)));
    }
}
