package wts.calc.test.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wts.calc.util.StrUtil;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StrUtilTest {

    @Test
    @DisplayName("String empty util test")
    public void EmptyStringUtilMethod_ShouldReturn_AppopriateCheck() {
        assertTrue(StrUtil.isEmpty(""));
        assertTrue(StrUtil.isEmpty(null));
        assertTrue(StrUtil.isEmpty("ta dasdsad"));
    }
}
