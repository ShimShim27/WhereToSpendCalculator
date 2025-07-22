package wts.calc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wts.calc.service.DefaultCalculatorService;

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
       assertTrue(false);
    }

    @Test
    @DisplayName("Get most expensive wallet")
    public void getMostExpensiveWallet_ShouldReturn_MostWallet() {
        assertTrue(false);
    }


    @Test
    @DisplayName("Get most cheapest wallet combination")
    public void getCheapestWalletCombination_ShouldReturn_CheapestWalletCombination() {
        assertTrue(false);
    }


    @Test
    @DisplayName("Get wallet in ascending order by expensiveness")
    public void getWalletsOrderedByExpensivenessInAsc_ShouldReturn_WalletsOrderedByExpensivenessInAsc(){
        assertTrue(false);
    }


    @Test
    @DisplayName("Get wallet in descending order by expensiveness")
    public void getWalletsOrderedByExpensivenessInDesc_ShouldReturn_WalletsOrderedByExpensivenessInDesc(){
        assertTrue(false, "testicle");
    }
}
