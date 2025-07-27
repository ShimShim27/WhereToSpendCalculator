package wts.calc.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StrUtil {
    public static boolean isNotEmpty(final String string) {
        return !isEmpty(string);
    }


    public static boolean isEmpty(final String string) {
        return string == null || string.isEmpty();
    }
}
