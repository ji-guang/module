package com.neil.module_lib.xm.net;

import android.widget.ImageView;

import com.neil.module_lib.net.http.HttpImg;

/**
 * Created by chen on 2018/4/25.
 *
 * xm图片
 */
public class XMImg {

    /**
     * xmt图片加载
     */
    public static void display(String url,String token,String objId,ImageView iv){
        HttpImg.display(iv,XmFile.getUrlByDocId(url,token,objId));
    }

    /**
     * xmt图片加载
     */
    public static void display(String url,String token,String objId,ImageView iv, int transform){
        HttpImg.display(iv,XmFile.getUrlByDocId(url,token,objId),transform);
    }

}
