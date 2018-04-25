package com.neil.module_lib.util.local;

import com.neil.module_lib.util.config.LibConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by chen on 2018/4/24.
 * <p>
 * 如同sp一样，方便的存储key，value；存储bean
 */
public class BeanCacheUtil {

    /**
     * bean缓存根目录
     */
    private static File root = new File(LibConfig.FilePath.sd_bean);

    /**
     * @param key
     * @param value bean
     */
    public static void put(String key, Serializable value) {
        if (value == null)
            return;
        setMemory(key, value);// 设置内存
        setCache(key, value);// 设置存储
    }

    /**
     * @param key
     */
    public static Serializable get(String key) {
        Serializable o;

        if ((o = getMemory(key)) != null)
            return o;// 返回内存对象

        if ((o = getCache(key)) != null) {
            setMemory(key, o);
            return o;// 返回内存对象
        }

        return o;// null
    }

    // --------------------------------pri--------------------------------
    /**
     * memory saved
     */
    private static Map<String, Serializable> map = new LinkedHashMap<String, Serializable>();
    private static void setMemory(String key, Serializable o) {
        map.put(key, o);
    }
    private static Serializable getMemory(String key) {
        return map.get(key);
    }

    /**
     * cache saved
     */
    private static void setCache(String key, Serializable o) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(root + "/" + key));
            oos.writeObject(o);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Serializable getCache(String key) {
        try {
            return (Serializable) new ObjectInputStream(new FileInputStream(root + "/" + key)).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
