package wts.calc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wts.calc.data.Wallet;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultCalculatorServiceTest {

    private DefaultCalculatorService service;

    @BeforeEach
    public void beforeEach() {
        service = new DefaultCalculatorService();
    }

    @Test
    @DisplayName("Get cheapest wallet")
    public void getCheapestWallet_ShouldReturn_CheapestWallet() {
        final BigDecimal spendAmount = new BigDecimal("1000");

        final Wallet wallet1 = new Wallet();
//        wallet1.setBalance();
    }

    @Test
    @DisplayName("Get most expensive wallet")
    public void getMostExpensiveWallet_ShouldReturn_MostWallet() {
        assertTrue(false);
    }

    @Test
    @DisplayName("Get wallet in order by expensiveness")
    public void getWalletsOrderedByExpensiveness_ShouldReturn_WalletsOrderedByExpensiveness(){
        assertTrue(false);
    }

    @Test
    @DisplayName("Process no name wallets")
    public void ProcessWalletWithNoName_ShouldThrow_Exception() {
        assertTrue(false);
    }

    @Test
    @DisplayName("Process empty wallet list")
    public void ProcessEmptyWalletList_ShouldThrow_Exception() {
        assertTrue(false);
    }
}
