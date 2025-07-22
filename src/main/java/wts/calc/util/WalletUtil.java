package wts.calc.util;

import wts.calc.data.Wallet;

import java.util.ArrayList;
import java.util.List;

public class WalletUtil {
    private WalletUtil(){}

    public static List<Wallet> getEmptyWalletList() {
        return new ArrayList<>();
    }
}
