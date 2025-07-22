package wts.calc.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wts.calc.data.Wallet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WalletUtilTest {

    @Test
    @DisplayName("Get empty wallet list")
    public void GetEmptyWalletList_ShouldReturn_EmptyWallet() {
        final List<Wallet> wallets = WalletUtil.getEmptyWalletList();
        assertTrue(wallets.isEmpty());
    }
}
