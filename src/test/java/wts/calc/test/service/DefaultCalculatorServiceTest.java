package wts.calc.test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import wts.calc.data.Wallet;
import wts.calc.data.WalletResult;
import wts.calc.exception.WalletException;
import wts.calc.params.TestWalletListParameter;
import wts.calc.service.DefaultCalculatorService;
import wts.calc.util.CalculationUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class DefaultCalculatorServiceTest {

    private DefaultCalculatorService service;

    @BeforeEach
    public void beforeEach() {
        service = new DefaultCalculatorService();
    }

    @ParameterizedTest
    @MethodSource("testWallets")
    @DisplayName("Get cheapest wallet")
    public void getCheapestWallet_ShouldReturn_CheapestWallet(final TestWalletListParameter params) {
        assertEquals(service.getCheapestWallet(params.getSpendAmount(),
                        params.getWallets()),
                params.getProcessedWalletsOrderByCheapest().get(0));
    }

    @ParameterizedTest
    @MethodSource("testWallets")
    @DisplayName("Get most expensive wallet")
    public void getMostExpensiveWallet_ShouldReturn_MostWallet(final TestWalletListParameter params) {
        final List<WalletResult> results =  params.getProcessedWalletsOrderByCheapest();
        assertEquals(service.getCheapestWallet(params.getSpendAmount(),
                        params.getWallets()), results.get(results.size()-1)
               );
    }

    @ParameterizedTest
    @MethodSource("testWallets")
    @DisplayName("Get wallet in order by expensiveness")
    public void getWalletsOrderedByExpensiveness_ShouldReturn_WalletsOrderedByExpensiveness(final TestWalletListParameter params){
        final List<WalletResult> results =  params.getProcessedWalletsOrderByCheapest();
        assertEquals(service.getWalletsOrderedByExpensiveness(params.getSpendAmount(),
                params.getWallets(), true), results, "Ascending order");

        Collections.reverse(results);
        assertEquals(service.getWalletsOrderedByExpensiveness(params.getSpendAmount(),
                params.getWallets(), false), results, "Descending order");
    }

    @Test
    @DisplayName("Process no name wallets")
    public void ProcessWalletWithNoName_ShouldThrow_Exception() {
        final BigDecimal spendAmount = new BigDecimal("11");

        final Wallet wallet1 = new Wallet();
        wallet1.setName("Wallet 1");


        final Wallet wallet3 = new Wallet();
        wallet3.setName("Wallet 3");


        final Wallet nullWalletName = new Wallet();
        nullWalletName.setName(null);


        final Wallet emptyWalletName = new Wallet();
        nullWalletName.setName("");

        assertThrowsExactly(WalletException.class,
                ()->service.getCheapestWallet(spendAmount,  Arrays.asList(wallet1, wallet3, nullWalletName)),
                WalletException.NO_WALLET_NAME);

        assertThrowsExactly(WalletException.class,
                ()->service.getMostExpensiveWallet(spendAmount,  Arrays.asList(wallet1, wallet3, new Wallet())),
                WalletException.NO_WALLET_NAME);

        assertThrowsExactly(WalletException.class,
                ()->service.getWalletsOrderedByExpensiveness(spendAmount,  Arrays.asList(wallet1, wallet3, new Wallet()), true),
                WalletException.NO_WALLET_NAME);

        assertThrowsExactly(WalletException.class,
                ()->service.getWalletsOrderedByExpensiveness(spendAmount,  Arrays.asList(wallet1, wallet3, emptyWalletName), false),
                WalletException.NO_WALLET_NAME);
    }

    @Test
    @DisplayName("Process empty wallet list")
    public void ProcessEmptyWalletList_ShouldThrow_Exception() {
        final BigDecimal spendAmount = new BigDecimal("11");

        assertThrowsExactly(WalletException.class,
                ()->service.getCheapestWallet(spendAmount, Collections.emptyList()),
                WalletException.NO_WALLETS);

        assertThrowsExactly(WalletException.class,
                ()->service.getMostExpensiveWallet(spendAmount, Collections.emptyList()),
                WalletException.NO_WALLETS);

        assertThrowsExactly(WalletException.class,
                ()->service.getWalletsOrderedByExpensiveness(spendAmount, Collections.emptyList(), true),
                WalletException.NO_WALLETS);

        assertThrowsExactly(WalletException.class,
                ()->service.getWalletsOrderedByExpensiveness(spendAmount, Collections.emptyList(), false),
                WalletException.NO_WALLETS);
    }

    @Test
    @DisplayName("Process duplicate wallet name")
    public void DuplicateWalletName_ShouldThrow_Exception(){
        final BigDecimal spendAmount = new BigDecimal("11");

        final Wallet wallet1 = new Wallet();
        wallet1.setName("Wallet 1");


        final Wallet wallet3 = new Wallet();
        wallet3.setName("Wallet 1");

        final List<Wallet> wallets = Arrays.asList(wallet1, wallet3);

        assertThrowsExactly(WalletException.class,
                ()->service.getCheapestWallet(spendAmount, wallets),
                WalletException.DUPLICATE_WALLET_NAME);

        assertThrowsExactly(WalletException.class,
                ()->service.getMostExpensiveWallet(spendAmount, wallets),
                WalletException.DUPLICATE_WALLET_NAME);

        assertThrowsExactly(WalletException.class,
                ()->service.getWalletsOrderedByExpensiveness(spendAmount, wallets, true),
                WalletException.DUPLICATE_WALLET_NAME);

        assertThrowsExactly(WalletException.class,
                ()->service.getWalletsOrderedByExpensiveness(spendAmount, wallets, false),
                WalletException.DUPLICATE_WALLET_NAME);
    }


    private static Stream<TestWalletListParameter> testWallets() {
        final List<Wallet> w1 = wallets1();
        final TestWalletListParameter p1 = new TestWalletListParameter(
                w1,
                Arrays.asList(),
                CalculationUtil.stringToNumerical("1000")
        );
        return Stream.of(p1);
    }

    private static List<Wallet> wallets1(){
        //fiat
        final Wallet wallet1 = new Wallet();
        wallet1.setName("wallet 1");
        wallet1.setBalance(CalculationUtil.stringToNumerical("50000"));


        // daily interest wallet, with increasing interest rates, with base interest rate
        final Wallet wallet2 = new Wallet();
        wallet2.setName("wallet 2");
        wallet2.setBalance(CalculationUtil.stringToNumerical("50033"));
        wallet2.setAlreadySpent(CalculationUtil.stringToNumerical("746"));
        wallet2.setCompoundedDaily(true);
        final Map<BigDecimal, BigDecimal> interestRates2 = new HashMap<>();
        interestRates2.put(CalculationUtil.stringToNumerical("3"), CalculationUtil.stringToNumerical("0"));
        interestRates2.put(CalculationUtil.stringToNumerical("4"), CalculationUtil.stringToNumerical("300"));
        interestRates2.put(CalculationUtil.stringToNumerical("5.25"), CalculationUtil.stringToNumerical("1000"));
        interestRates2.put(CalculationUtil.stringToNumerical("6.75"), CalculationUtil.stringToNumerical("39000"));
        wallet2.setInterestPercentageAndMinSpendMap(interestRates2);



        // daily interest wallet, with increasing interest rates, with spending fee
        final Wallet wallet3 = new Wallet();
        wallet3.setName("wallet 3");
        wallet3.setBalance(CalculationUtil.stringToNumerical("39500"));
        wallet3.setAlreadySpent(CalculationUtil.stringToNumerical("140"));
        wallet3.setCompoundedDaily(true);
        final Map<BigDecimal, BigDecimal> interestRates3 = new HashMap<>();
        interestRates3.put(CalculationUtil.stringToNumerical("8"), CalculationUtil.stringToNumerical("1000"));
        interestRates3.put(CalculationUtil.stringToNumerical("15.25"), CalculationUtil.stringToNumerical("1200"));
        interestRates3.put(CalculationUtil.stringToNumerical("17.75"), CalculationUtil.stringToNumerical("1300"));
        wallet3.setInterestPercentageAndMinSpendMap(interestRates3);
        wallet3.setSpendingFee(CalculationUtil.stringToNumerical("75.22"));


        // monthly interest wallet, with cashback
        final Wallet wallet4 = new Wallet();
        wallet4.setName("wallet 4");
        wallet4.setBalance(CalculationUtil.stringToNumerical("37188.38"));
        final Map<BigDecimal, BigDecimal> interestRates4 = new HashMap<>();
        interestRates4.put(CalculationUtil.stringToNumerical("6"), CalculationUtil.stringToNumerical("0"));
        wallet4.setInterestPercentageAndMinSpendMap(interestRates4);
        wallet4.setCashbackPercentage(CalculationUtil.stringToNumerical("10"));

        return Arrays.asList(wallet1, wallet2, wallet3, wallet4);
    }
}
