package wts.calc.util;

import wts.calc.data.Wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WalletUtil {
    private WalletUtil(){}

    public static List<Wallet> getEmptyWalletList() {
        return new ArrayList<>();
    }

    public static BigDecimal getZeroBalance(){
        return new BigDecimal("0");
    }

    public static BigDecimal stringToNumerical(final String stringNum) {
        try {
            return new BigDecimal(stringNum);
        } catch (IllegalArgumentException e){
            return getZeroBalance();
        }
    }
}
