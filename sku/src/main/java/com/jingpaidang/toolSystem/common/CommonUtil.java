package com.jingpaidang.toolSystem.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtil {
	/**
     * md5加密
     */
    public static String md5(String data) {
        StringBuffer buffer = new StringBuffer();
        byte[] b1 = data.getBytes();
        char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] b2 = digest.digest(b1);
            for (byte b : b2) {
                buffer.append(c[new Integer(b >> 4 & 0x0F)]);
                buffer.append(c[new Integer(b & 0x0F)]);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
