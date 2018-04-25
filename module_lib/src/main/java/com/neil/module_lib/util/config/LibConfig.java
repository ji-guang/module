package com.neil.module_lib.util.config;

import android.content.Context;
import android.os.Environment;

import com.neil.module_lib.R;
import com.neil.module_lib.util.config.okhttp.OkhttpConfig;

import java.io.File;

/**
 * Created by chen on 2018/3/28.
 * 静态参数，与动态参数
 */
public class LibConfig {
    /**
     * @param context
     */
    public static void init(Context context) {
        initOkhttp();

    }


    /**
     * 模块:Net
     */
    public interface Net {
        //glide config
        int glide_placeholder = R.drawable.ic_launcher;  //glide加载中占位图
        int glide_error = 0;                             //glide加载失败图
        int glide_crossFadeTime = 300;                   //glide图片过渡时间
        //okhttp
        long CONN_TIMEOUT = 10 * 1000; //连接超时时间
        long READ_TIMEOUT = 45 * 1000; //读取数据超时时间
        long Write_TIMEOUT = 45 * 1000; //读取数据超时时间
    }

    /**
     * 模块:config
     */
    public interface Config {
        boolean isDegug = true;     //日志控制

    }

    /**
     * 模块:存储路径
     */
    public interface FilePath {
        String sd_root = createDirs(Environment.getExternalStorageDirectory() + "/module");
        String sd_bean = createDirs(sd_root + "/bean");
        String sd_file = createDirs(sd_root + "/file");
        String sd_img = createDirs(sd_root + "/img");
        String sd_other = createDirs(sd_root + "/other");
    }
    //---------------------私有方法区----------------------

    /**
     * 创建文件夹
     */
    private static String createDirs(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return filePath;
    }

    /**
     * Okhttp初始化
     */
    private static void initOkhttp() {
        OkhttpConfig.getInstance().init();//okhttp
    }
}
