package wts.calc.main;

import wts.calc.data.Wallet;
import wts.calc.data.WalletResult;

import java.math.BigDecimal;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Wallet w = new Wallet();
        w.setBalance(BigDecimal.valueOf(1));
        w.setInterestPercentageAndMinSpendMap(new HashMap<>());

        Wallet w2 = new Wallet();
        w2.setBalance(BigDecimal.valueOf(1));

        WalletResult wr = new WalletResult(w, BigDecimal.ZERO, BigDecimal.ZERO);
        WalletResult wr2 = new WalletResult(w2, BigDecimal.ZERO, BigDecimal.ZERO);

        System.out.println(wr.equals(wr2));
    }

}