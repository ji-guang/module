package com.neil.module_lib.config;

import com.neil.module_lib.R;

/**
 * Created by chen on 2018/3/28.
 * 库静态资源配置
 */
public interface LibConfig {
    /**
     * net
     */
    //glide config
    int glide_placeholder = R.drawable.ic_launcher;  //glide加载中占位图
    int glide_error = 0;                             //glide加载失败图
    int glide_crossFadeTime = 300;                   //glide图片过渡时间
    //okhttp
    long CONN_TIMEOUT = 10 * 1000; //连接超时时间
    long READ_TIMEOUT = 45 * 1000; //读取数据超时时间
    long Write_TIMEOUT = 45 * 1000; //读取数据超时时间

    /**
     * other
     */
    //other
}
