package wts.calc.test.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wts.calc.util.StrUtil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StrUtilTest {

    @Test
    @DisplayName("String empty util test")
    public void EmptyStringUtilMethod_ShouldDo_AppopriateCheck() {
        assertTrue(StrUtil.isEmpty(""));
        assertTrue(StrUtil.isEmpty(null));
        assertFalse(StrUtil.isEmpty("ta dasdsad"));
    }

    @Test
    @DisplayName("String not empty util test")
    public void NotEmptyStringUtilMethod_ShouldDo_AppopriateCheck() {
        assertFalse(StrUtil.isNotEmpty(""));
        assertFalse(StrUtil.isNotEmpty(null));
        assertTrue(StrUtil.isNotEmpty("ta dasdsad"));
    }
}
