package wts.calc.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class WalletResult {
    private Wallet wallet;
    private BigDecimal spendAmount;
    private BigDecimal finalBalanceInATimePeriod;


}
