package com.neil.module_lib.config.okhttp;

import com.neil.module_lib.config.LibConfig;
import com.neil.module_lib.config.ModuleConfig;
import com.zhy.http.okhttp.OkHttpUtils;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by chen on 2018/4/7.
 */

public class OkhttpClientManager {
    /**
     * 单例模式:也维护一个ok客户端配置
     */
    private OkhttpClientManager() { }
    private static OkhttpClientManager instance;
    public static OkhttpClientManager getInstance() {
        if (instance == null) {
            synchronized (OkhttpClientManager.class) {
                if (instance == null) {
                    instance = new OkhttpClientManager();
                }
            }
        }
        return instance;
    }

    private OkHttpClient client;
    /**
     * 初始化client
     */
    public void init(){
        client = initClient();
        OkHttpUtils.initClient(client);
    }
    //-------------------------------------init-----------------------------
    /**
     * TODO 配置OKHttpClient 1:ssl 2:超时配置
     */
    @SuppressWarnings("deprecation")
    private OkHttpClient initClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder
                //ssl相关
                .hostnameVerifier(getHoseVerifier())
                .sslSocketFactory(getSSLContext(getTrustManager()).getSocketFactory())
                //配置超时时间
                .connectTimeout(LibConfig.CONN_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(LibConfig.READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(LibConfig.Write_TIMEOUT, TimeUnit.MILLISECONDS)
                //错误重连
                .retryOnConnectionFailure(true);
        //日志拦截
        if (ModuleConfig.SystemConfig.isDegug) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        return builder.build();
    }

    /**
     * @return 主机名验证
     */
    private HostnameVerifier getHoseVerifier() {
        return new HostnameVerifier() {// 主机验证
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }

    /**
     * @return ssl环境：设置ssl
     */
    private static SSLContext getSSLContext(X509TrustManager xtm) {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sslContext;
    }

    /**
     * @return 证书:准备ssl证书
     */
    private static X509TrustManager getTrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }
}
