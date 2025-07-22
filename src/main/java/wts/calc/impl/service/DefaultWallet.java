package wts.calc.impl.service;

import wts.calc.base.service.Calculator;
import wts.calc.data.Wallet;
import wts.calc.data.WalletResult;

import java.util.List;

public class DefaultWallet implements Calculator {
    @Override
    public WalletResult getCheapestWallet(List<Wallet> wallets) {
        return null;
    }

    @Override
    public WalletResult getMostExpensiveWallet(List<Wallet> wallets) {
        return null;
    }

    @Override
    public List<WalletResult> getCheapestWalletCombinations(List<Wallet> wallets) {
        return null;
    }

    @Override
    public List<WalletResult> getWalletsOrderedByExpensiveness(List<Wallet> wallets, boolean asc) {
        return null;
    }

}
