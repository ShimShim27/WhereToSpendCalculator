package wts.calc.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wts.calc.data.Wallet;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WalletUtilTest {

    @Test
    @DisplayName("Get empty wallet list")
    public void GetEmptyWalletList_ShouldReturn_EmptyWallet() {
        final List<Wallet> wallets = WalletUtil.getEmptyWalletList();
        assertTrue(wallets.isEmpty());
    }

    @Test
    @DisplayName("Get zero balance")
    public void GetZeroBalance_ShouldReturn_ZeroBalanceBigDecimal() {
        assertEquals(0, WalletUtil.getZeroBalance().intValue());
    }

    @Test
    @DisplayName("String to big decimal")
    public void StringToBigDecimal_ShouldReturn_ProperBigDecimal() {
        assertEquals("22.3333355", WalletUtil.stringToNumerical("22.3333355").toPlainString(), "Decimal test");
        assertEquals(424, WalletUtil.stringToNumerical("424").intValue(), "Integer test");
        assertEquals(0, WalletUtil.stringToNumerical("ille").intValue(), "Illegal number test");
    }
}
