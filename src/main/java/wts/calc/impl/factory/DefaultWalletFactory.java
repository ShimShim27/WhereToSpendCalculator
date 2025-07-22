package wts.calc.impl.factory;

import wts.calc.base.factory.WalletFactory;
import wts.calc.data.Wallet;

public class DefaultWalletFactory implements WalletFactory {
    private DefaultWalletFactory(){}

    @Override
    public Wallet fromJsonObject() {
        return null;
    }
}
