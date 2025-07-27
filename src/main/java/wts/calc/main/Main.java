package wts.calc.main;

import wts.calc.base.service.CalculatorService;
import wts.calc.factory.DefaultWalletFactory;
import wts.calc.service.DefaultCalculatorService;
import wts.calc.util.FileUtil;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        final String jsonString = FileUtil.readFile("./src/main/resources/test/wallet.json");
        final CalculatorService service = new DefaultCalculatorService();
        final BigDecimal spendAmount = BigDecimal.valueOf(30000);

        service.getWalletsOrderedByExpensiveness(spendAmount,
                DefaultWalletFactory.createWalletsFromJson(jsonString), true).forEach(walletResult -> {
                    final String message = String.format("%s: spend ₱%s and have ₱%s at the end of the month\n",
                            walletResult.getWallet().getName(), walletResult.getSpendAmount(), walletResult.getFinalBalanceInATimePeriod());
                    System.out.println(message);
        });
    }

}