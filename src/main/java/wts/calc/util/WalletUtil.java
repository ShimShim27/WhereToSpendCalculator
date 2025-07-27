package wts.calc.util;

import wts.calc.data.Wallet;

import java.math.BigDecimal;
import java.util.List;

public class WalletUtil {
    public static BigDecimal getTotalBalance(final List<Wallet> wallets) {
        BigDecimal total = BigDecimal.ZERO;
        for (Wallet wallet:wallets) {
            total = total.add(wallet.getBalance());
        }
        return total;
    }
}
