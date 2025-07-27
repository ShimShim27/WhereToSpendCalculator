package wts.calc.test.factory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wts.calc.data.Wallet;
import wts.calc.factory.DefaultWalletFactory;
import wts.calc.util.FileUtil;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultWalletFactoryTest {

    @Test
    @DisplayName("Create wallets from json")
    public void CreateWalletsFromJson_ShouldCreate_NewWallet() {
        final String jsonString = FileUtil.readFile("./src/main/resources/test/wallet.json");
        final List<Wallet> wallets = DefaultWalletFactory.createWalletsFromJson(jsonString);

        final Type typeToken = new TypeToken<List<Wallet>>(){}.getType();
        final List<Wallet> expected = new Gson().fromJson(jsonString,typeToken);

        assertEquals(expected, wallets);
    }
}
