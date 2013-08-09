package com.jingpaidang.cshop.common.utils;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 5/24/13
 */
public class JSONUtilsTest {

    private String adminJsonStr = "{\"userName\":\"admin\",\"name\":\"thomsontang\",\"phone\":\"13321155257\"}";

    public void testWriteValue() {
        try {
            String adminJson = JSONUtils.toJson(new Admin("admin", "thomsontang", "13321155257"));
            assertEquals(adminJsonStr, adminJson);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
