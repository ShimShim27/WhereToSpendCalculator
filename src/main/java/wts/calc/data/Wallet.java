package wts.calc.data;

import lombok.Data;
import wts.calc.util.WalletUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class Wallet {
    private String name;
    private BigDecimal balance = WalletUtil.getZeroBalance();
    private Map<BigDecimal, BigDecimal> monthlyInterestRatesAndMinimumSpendMap = new HashMap<>();
    private BigDecimal alreadySpent = WalletUtil.getZeroBalance();
    private boolean isCompoundedDaily;
    private BigDecimal minAmtForCashback = WalletUtil.getZeroBalance();
    private BigDecimal maxCashback = WalletUtil.getZeroBalance();
    private BigDecimal cashbackPercentage = WalletUtil.getZeroBalance();
    private BigDecimal alreadyCashedBack = WalletUtil.getZeroBalance();
    private BigDecimal discountUponSpend = WalletUtil.getZeroBalance();
    private BigDecimal spendingFee = WalletUtil.getZeroBalance();
}
