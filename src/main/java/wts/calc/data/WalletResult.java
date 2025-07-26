package wts.calc.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
public class WalletResult {
    private Wallet wallet;
    private BigDecimal spendAmount;
    private BigDecimal savedAmount;
}
