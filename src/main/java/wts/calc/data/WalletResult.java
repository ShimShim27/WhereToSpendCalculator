package wts.calc.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletResult {
    private Wallet wallet;
    private BigDecimal spendAmount;
    private BigDecimal savedAmount;
}
