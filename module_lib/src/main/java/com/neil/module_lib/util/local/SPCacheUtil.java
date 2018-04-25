package com.neil.module_lib.util.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chen on 2018/4/24.
 * <p>
 * 默认sp文件
 * 获取统一基本类型
 */
public class SPCacheUtil {

    /**
     * 默认file_name标识
     */
    private static final String FILE_NAME = SPCacheUtil.class.getSimpleName();

    /**
     * 存储基本类型
     */
    public static void putBoolean(Context c, String key, Boolean value) {
        getEditor(c).putBoolean(key, value).commit();
    }

    public static void putInt(Context c, String key, int value) {
        getEditor(c).putInt(key, value).commit();
    }

    public static void putLong(Context c, String key, long value) {
        getEditor(c).putLong(key, value).commit();
    }

    public static void putString(Context c, String key, String value) {
        getEditor(c).putString(key, value).commit();
    }

    /**
     * 获取基本类型
     */
    public static boolean getBoolean(Context c, String key) {
        return getSP(c).getBoolean(key, false);
    }

    public static int getInt(Context c, String key) {
        return getSP(c).getInt(key, 0);
    }

    public static long getLong(Context c, String key) {
        return getSP(c).getLong(key, 0);
    }

    public static String getString(Context c, String key) {
        return getSP(c).getString(key, "");
    }


    /**
     * 获取sp及editor
     */
    private static SharedPreferences getSP(Context c) {
        return c.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context c) {
        return getSP(c).edit();
    }
}
