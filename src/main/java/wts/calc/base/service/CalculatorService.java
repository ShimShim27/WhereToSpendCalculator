package wts.calc.base.service;

import wts.calc.data.Wallet;
import wts.calc.data.WalletResult;

import java.math.BigDecimal;
import java.util.List;

public interface CalculatorService {
     public WalletResult getCheapestWallet(final BigDecimal spendAmount, final List<Wallet> wallets);
     public WalletResult getMostExpensiveWallet(final BigDecimal spendAmount, final List<Wallet> wallets);
     public List<WalletResult> getWalletsOrderedByExpensiveness(final BigDecimal spendAmount, final List<Wallet> wallets, final boolean sortByCheapest);
}
