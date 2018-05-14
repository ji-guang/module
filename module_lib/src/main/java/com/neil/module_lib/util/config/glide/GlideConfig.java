package com.neil.module_lib.util.config.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.neil.module_lib.util.config.LibConfig;

import java.io.InputStream;

/**
 * Created by chen on 2018/3/28.
 * <p>
 * 显式配置参数，有利于glide版本的统一系统设置
 */

@GlideModule
public class GlideConfig extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDefaultRequestOptions(new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(false)
                .placeholder(LibConfig.Net.glide_placeholder)
                .error(LibConfig.Net.glide_error)
                .format(DecodeFormat.PREFER_ARGB_8888)  //gif缩放背景问题 565
                .fitCenter()
        );
    }
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());//okhttp通讯
    }
}
