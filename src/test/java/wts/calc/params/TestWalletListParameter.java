package wts.calc.params;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wts.calc.data.Wallet;
import wts.calc.data.WalletResult;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class TestWalletListParameter {
    private List<Wallet> wallets;
    private List<WalletResult> processedWalletsOrderByCheapest;
    private BigDecimal spendAmount;
}
