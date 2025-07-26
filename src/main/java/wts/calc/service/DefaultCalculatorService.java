package wts.calc.service;

import wts.calc.base.service.CalculatorService;
import wts.calc.data.Wallet;
import wts.calc.data.WalletResult;
import wts.calc.exception.WalletException;
import wts.calc.util.StrUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultCalculatorService implements CalculatorService {

    @Override
    public WalletResult getCheapestWallet(BigDecimal spendAmount, List<Wallet> wallets) {
        validateWallets(wallets);
        return getWalletsOrderedByExpensiveness(spendAmount, wallets, true).get(0);
    }

    @Override
    public WalletResult getMostExpensiveWallet(BigDecimal spendAmount, List<Wallet> wallets) {
        validateWallets(wallets);
        return getWalletsOrderedByExpensiveness(spendAmount, wallets, false).get(0);
    }

    @Override
    public List<WalletResult> getWalletsOrderedByExpensiveness(BigDecimal spendAmount, List<Wallet> wallets, boolean asc) {
        validateWallets(wallets);
        return null;
    }

    private void validateWallets(final List<Wallet> wallets) throws WalletException {
        if (wallets.isEmpty()) {
            throw new WalletException(WalletException.NO_WALLETS);
        }

        if (wallets.stream().anyMatch(w -> StrUtil.isEmpty(w.getName()))){
            throw new WalletException(WalletException.NO_WALLET_NAME);
        }

        if (wallets.size() != wallets.stream().map(Wallet::getName).collect(Collectors.toSet()).size()) {
            throw new WalletException(WalletException.DUPLICATE_WALLET_NAME);
        }
    }
}
