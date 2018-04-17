package com.neil.module_lib.util.name;

/**
 * Created by chen on 2018/4/3.
 */

import com.neil.module_lib.util.encrypt.StringEncryptUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 内容byte统一命名标识事物：数字摘要(md5,sha1,sha256)
 */
public class DigestUtil {

    public static final String MD5 = "md5";//相对快速，碰撞概率大
    public static final String sha1 = "sha-1";
    public static final String sha256 = "sha-256";//相对慢，碰撞概率小

    public static String digest(String string, String algorithm) {
        try {
            return digest(string.getBytes("utf-8"), algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String digest(byte[] bytes, String algorithm) {
        return encrypt(bytes, algorithm);
    }

    public static String digest(File file, String algorithm) {
        return encrypt(file, algorithm);
    }

    /**
     * API实现    ：byte数字摘要
     */
    private static String encrypt(byte[] bytes, String algorithm) {
        try {
            return StringEncryptUtil.byte2hex(MessageDigest.getInstance(algorithm).digest(bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * API实现    :文件数字摘要
     */
    private static String encrypt(File file, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            FileInputStream fis = new FileInputStream(file);

            byte[] buffer = new byte[16 * 1024];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, length);
            }
            return StringEncryptUtil.byte2hex(digest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
