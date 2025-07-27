package wts.calc.factory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import wts.calc.data.Wallet;

import java.lang.reflect.Type;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultWalletFactory {

    public static List<Wallet> createWalletsFromJson(final String jsonString) {
        final Type typeToken = new TypeToken<List<Wallet>>(){}.getType();
        return new Gson().fromJson(jsonString,typeToken);
    }
}
