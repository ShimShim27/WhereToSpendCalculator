package wts.calc.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StrUtil {
    public static boolean isEmpty(final String string) {
        return string == null || string.isEmpty();
    }
}
