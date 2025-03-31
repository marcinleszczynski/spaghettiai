package com.cooking.service.utils;

import org.junit.jupiter.api.Test;

import static com.cooking.service.utils.StringUtils.unwrapFromWindowMarkdown;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testUnwrappingJson() {
        var string = "'''json\n{\n\"mockAttribute\": \"mockValue\"\n}\n'''";
        var unwrapped = unwrapFromWindowMarkdown(string);

        assertEquals("{\n\"mockAttribute\": \"mockValue\"\n}", unwrapped);
    }
}