package wts.calc.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode
public class Wallet {
    private String name;
    private BigDecimal balance = BigDecimal.ZERO;
    private Map<BigDecimal, BigDecimal> interestPercentageAndMinSpendMap = new HashMap<>();
    private BigDecimal alreadySpent = BigDecimal.ZERO;
    private boolean isCompoundedDaily;
    private BigDecimal maxCashback = BigDecimal.ZERO;
    private BigDecimal cashbackPercentage = BigDecimal.ZERO;
    private BigDecimal alreadyCashedBack = BigDecimal.ZERO;
    private BigDecimal spendingFee = BigDecimal.ZERO;
}
