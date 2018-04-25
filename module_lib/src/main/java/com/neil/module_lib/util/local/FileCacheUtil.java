package com.neil.module_lib.util.local;

import java.io.File;

/**
 * Created by chen on 2018/4/24.
 *
 * 文件存取常见操作
 */

public class FileCacheUtil {




    /**
     * TODO 删除file及子目录
     */
    public static void deleteFile(File file) {
        if (file.isDirectory())
            for (File f : file.listFiles())
                deleteFile(f);
        file.delete();
    }
}
