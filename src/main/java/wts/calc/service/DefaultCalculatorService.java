package wts.calc.service;

import wts.calc.base.service.CalculatorService;
import wts.calc.data.Wallet;
import wts.calc.data.WalletResult;

import java.math.BigDecimal;
import java.util.List;

public class DefaultCalculatorService implements CalculatorService {

    @Override
    public WalletResult getCheapestWallet(BigDecimal spendAmount, List<Wallet> wallets) {
        return null;
    }

    @Override
    public WalletResult getMostExpensiveWallet(BigDecimal spendAmount, List<Wallet> wallets) {
        return null;
    }

    @Override
    public List<WalletResult> getWalletsOrderedByExpensiveness(BigDecimal spendAmount, List<Wallet> wallets, boolean asc) {
        return null;
    }
}
