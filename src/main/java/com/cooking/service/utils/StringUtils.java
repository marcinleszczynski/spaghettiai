package com.cooking.service.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StringUtils {

    public static String unwrapJSONFromWindowMarkdown(String text) {
        var beginIndex = text.indexOf('{');
        var endIndex = text.lastIndexOf('}');
        return text.substring(beginIndex, endIndex+1);
    }

    public static String unwrapListFromWindowMarkdown(String text) {
        var beginIndex = text.indexOf('[');
        var endIndex = text.lastIndexOf(']');
        return text.substring(beginIndex, endIndex+1);
    }
}
