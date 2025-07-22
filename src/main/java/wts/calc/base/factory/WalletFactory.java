package wts.calc.base.factory;

import wts.calc.data.Wallet;

public interface WalletFactory {
    public Wallet fromJsonObject();
}
