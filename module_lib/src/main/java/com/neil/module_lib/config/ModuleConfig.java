package com.neil.module_lib.config;

import android.content.Context;

import com.neil.module_lib.config.okhttp.OkhttpClientManager;

/**
 * Created by chen on 2018/4/17.
 * 动态资源配置
 */

public class ModuleConfig {

    /**
     *  lib：初始化
     */
    public static void initConfig(Context context){
        OkhttpClientManager.getInstance().init();//okhttp

        //other init
    }


    /**
     * 系统同意配置
     */
    public interface SystemConfig{
        boolean isDegug = true;     //日志控制
    }
}
