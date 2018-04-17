package com.neil.module_lib.util.encrypt;

import android.util.Base64;

/**
 * Created by chen on 2018/4/3.
 */

/**
 * 无秘钥的转换
 */
public class StringEncryptUtil {
    /**
     * 数据：byte[] 转 hex进制字符串
     */
    public static String byte2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte bb : bytes) {
            String hex = Integer.toHexString(bb & 0xff);
            if (hex.length() == 1)
                sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * hex 转byte[]
     */
    public static byte[] hex2byte(String hex) {
        byte[] ret = new byte[hex.length() / 2];

        for (int i = 0, len = hex.length() / 2; i < len; i++) {
            ret[i] = Integer.valueOf(hex.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return ret;
    }

    /**
     * @TODO byte[]转base64String
     */
    public static String byte2base64(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    /**
     * @TODO base64串转byte[]
     */
    public static byte[] base642byte(String data) {
        return Base64.decode(data, Base64.NO_WRAP);
    }

}
