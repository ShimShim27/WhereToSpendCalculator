package wts.calc.base.service;

import wts.calc.data.Wallet;
import wts.calc.data.WalletResult;

import java.util.List;

public interface Calculator {
     public WalletResult getCheapestWallet(final List<Wallet> wallets);
     public WalletResult getMostExpensiveWallet(final List<Wallet> wallets);
     public List<WalletResult> getCheapestWalletCombinations(final List<Wallet> wallets);
     public List<WalletResult> getWalletsOrderedByExpensiveness(final boolean asc, final List<Wallet> wallets);
}
