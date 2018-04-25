package com.neil.module_lib.xm.net;

import com.neil.module_lib.net.http.HttpFile;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chen on 2018/4/17.
 * xm
 */
public class XmFile {

    /**
     * get下载文件
     */
    public static void downloadByGet(String url,String token,String objId,String parent, String child, HttpFile.DownloadCallback callback){
        HttpFile.download(getUrlByDocId(url,token,objId),parent,child,callback);
    }

    /**
     * post下载文件
     */
    public static void downloadByPost(String url,String token,String objId,String parent, String child, HttpFile.DownloadCallback callback){
        Map map = new HashMap<String,String>();
        map.put("params","{\"token\":\""+token+"\",\"content\":{\"objId\":\""+objId+"\"}}");
        HttpFile.download(url,map,parent,child,callback);
    }

    /**
     *  上传获得objId
     */
    public static void uploadbyXm(String url, File file, String type,HttpFile.UploadCallback callback){
        Map<String ,String> params = new HashMap<String ,String>();
        params.put("type",type);

        String fileName = "file";

        HttpFile.upload(url, params,fileName, file,callback);
    }

    /**
     * @return  objId > url
     */
    public static String getUrlByDocId(String url,String token,String objId){
        try {
            return url + "?params="+ URLEncoder.encode("{\"token\":\""+token+"\",\"content\":{\"objId\":\""+objId+"\"}}","utf-8");
        } catch (Exception e) {
            return "";
        }
    }
}
