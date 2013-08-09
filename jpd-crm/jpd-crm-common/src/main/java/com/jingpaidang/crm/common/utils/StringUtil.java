package com.jingpaidang.crm.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class StringUtil {
	private StringUtil() {};
	/**
	 * 按规则(tag)拆分字符串为数组
	 * @param str
	 * @return
	 */
	public static String[] str2Arr(String str, String tag) {
		if(ValidateUtil.isValidated(str)) {
			return str.split(tag);
		}
		return null;
	}
	/**
	 * 将输入流转换为字符串
	 * @param in
	 * @return {  "access_token": "e237ef3f-37c0-4f8e-a84b-3b83a428b5c5",  "code": 0,  "expires_in": 31103999,  "refresh_token": "dff06ac7-c3f3-408d-8e1c-3906eca7feb7",  "scope": "null",  "time": "1364809991652",  "token_type": "bearer"}
	 */
	public static String inputStream2String(InputStream in) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int count = -1;
        try {
            while((count = in.read(data,0,1024)) != -1)
                outStream.write(data, 0, count);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            return new String(outStream.toByteArray(),"GBK");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
