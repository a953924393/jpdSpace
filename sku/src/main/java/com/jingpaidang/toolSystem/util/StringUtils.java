package com.jingpaidang.toolSystem.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 8/8/13
 * Time: 2:14 PM
 */
public class StringUtils {
    private StringUtils() {
    }

    public static List<String> split(String string) {
        String[] split = string.split("\r\n");

        List<String> strings = new ArrayList<String>();

        for(String s : split) {
            strings.add(s);
        }

        return strings;
    }

}
