package wts.calc.test.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wts.calc.data.Wallet;
import wts.calc.util.CalculationUtil;
import wts.calc.util.WalletCalculationUtil;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WalletCalculationUtilTest {

    @Test
    @DisplayName("Get empty wallet list")
    public void GetEmptyWalletList_ShouldReturn_EmptyWallet() {
        final List<Wallet> wallets = WalletCalculationUtil.getEmptyWalletList();
        assertTrue(wallets.isEmpty());
    }

    @Test
    @DisplayName("String to big decimal")
    public void StringToBigDecimal_ShouldReturn_ProperBigDecimal() {
        assertEquals("22.3333355", CalculationUtil.stringToNumerical("22.3333355").toPlainString(), "Decimal test");
        assertEquals(424, CalculationUtil.stringToNumerical("424").intValue(), "Integer test");
        assertEquals(0, CalculationUtil.stringToNumerical("ille").intValue(), "Illegal number test");
    }



    @Test
    @DisplayName("Calculate interest")
    public void CalculateInterest_ShouldReturn_CorrectTotal(){
        final BigDecimal data1 = WalletCalculationUtil.calculateInterestAndGetNewTotal(
                CalculationUtil.stringToNumerical("82925"),
                CalculationUtil.stringToNumerical("4.5"),
                BigDecimal.valueOf(12),
                BigDecimal.valueOf(1)
        );


        final BigDecimal data2 = WalletCalculationUtil.calculateInterestAndGetNewTotal(
                CalculationUtil.stringToNumerical("61777"),
                CalculationUtil.stringToNumerical("2.5"),
                BigDecimal.valueOf(365),
                BigDecimal.valueOf(5)
        );

        assertEquals(CalculationUtil.stringToNumerical("86734.56"), data1);
        assertEquals(CalculationUtil.stringToNumerical("70002.21"), data2);
    }


    @Test
    @DisplayName("Calculate cashback")
    public void CalculateCashback_ShouldReturn_AccurateCashback() {
        final BigDecimal cashback1 = WalletCalculationUtil.calculateCashback(CalculationUtil.stringToNumerical("8299" ),
                CalculationUtil.stringToNumerical("1.5"),
                BigDecimal.ZERO, CalculationUtil.stringToNumerical("250.2"));

        final BigDecimal cashback2 = WalletCalculationUtil.calculateCashback(CalculationUtil.stringToNumerical("8299" ),
                CalculationUtil.stringToNumerical("50"),
                CalculationUtil.stringToNumerical("5000"), CalculationUtil.stringToNumerical("372.29"));



        final BigDecimal cashback3 = WalletCalculationUtil.calculateCashback(CalculationUtil.stringToNumerical("8299" ),
                CalculationUtil.stringToNumerical("50"),
                CalculationUtil.stringToNumerical("300"), CalculationUtil.stringToNumerical("259"));

        final BigDecimal cashback4 = WalletCalculationUtil.calculateCashback(CalculationUtil.stringToNumerical("8299" ),
                CalculationUtil.stringToNumerical("18.2"),
                CalculationUtil.stringToNumerical("1576.46"), CalculationUtil.stringToNumerical("66"));



        assertEquals(CalculationUtil.stringToNumerical("124.49"), cashback1, "No max cashback, already cashed back provided");
        assertEquals(CalculationUtil.stringToNumerical("4149.50"), cashback2, "Cashback is less than remaining cashback");
        assertEquals(CalculationUtil.stringToNumerical("41.00"), cashback3, "Cashback is greater than the remaining cashback");
        assertEquals(CalculationUtil.stringToNumerical("1510.42"), cashback4, "Cashback is equal to the remaining cashback");

    }

    @Test
    @DisplayName("Calculate after fee amount")
    public void CalculateAfterFeeAmount_ShouldReturn_AmountAfterDeductions() {
        assertEquals(CalculationUtil.stringToNumerical("1790.50"),
                WalletCalculationUtil.calculateAfterFeeAmount(CalculationUtil.stringToNumerical("1822.44"), CalculationUtil.stringToNumerical("31.94")));


        assertEquals(CalculationUtil.stringToNumerical("-409.50"),
                WalletCalculationUtil.calculateAfterFeeAmount(CalculationUtil.stringToNumerical("1822.44"), CalculationUtil.stringToNumerical("2231.94")));
    }
}
