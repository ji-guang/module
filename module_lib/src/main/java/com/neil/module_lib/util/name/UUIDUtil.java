package com.neil.module_lib.util.name;

import java.util.UUID;

/**
 * Created by chen on 2018/4/3.
 */

/**
 * 命名唯一标识:  当前时空环境uuid
 */
public class UUIDUtil {
    /**
     *     （32位 uuid）
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
