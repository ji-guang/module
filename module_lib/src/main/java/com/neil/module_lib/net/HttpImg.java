package com.neil.module_lib.net;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.neil.module_lib.config.LibConfig;
import com.neil.module_lib.util.LibUtil;


import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * http图片加载
 * （支持本地文件系统）
 */
public class HttpImg {

    /**
     * 常见图片效果：
     * 裁剪圆角，圆形    >灰度化    >模糊
     */
    public static final int TRANSFORM_NONE = 0;
    public static final int TRANSFORM_CIRCLE = 1 << 0;
    public static final int TRANSFORM_ROUNDERCORNE = 1 << 1;
    public static final int TRANSFORM_GRAYSCALE = 1 << 2;
    public static final int TRANSFORM_BLUR = 1 << 3;

    /**
     * @param res 数据源：String  >File,Uri,resId,asset    >byte,bitmap,drawable
     */
    public static void display(ImageView iv, Object res) {
        display(iv, res, TRANSFORM_NONE);
    }

    /**
     * @param res       数据源：String  >File,Uri,resId,asset    >byte,bitmap,drawable
     * @param transform 图片处理效果（可组合）
     */
    public static void display(ImageView iv, Object res, int transform) {
        try {
            Glide.with(iv.getContext())
                    .load(res)
                    .apply(getRequestOptions(iv, transform))        //变换效果
                    .transition(new DrawableTransitionOptions().crossFade(LibConfig.glide_crossFadeTime))//过渡效果
                    .into(iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现：组装transform样式
     */
    private static RequestOptions getRequestOptions(ImageView iv, int transform) {
        if (transform == 0)
            return new RequestOptions();
        List<Transformation> list = new ArrayList<Transformation>();
        if ((transform & TRANSFORM_CIRCLE) != 0) {
            list.add(new CircleCrop());
        }
        if ((transform & TRANSFORM_ROUNDERCORNE) != 0) {
            list.add(new RoundedCornersTransformation(LibUtil.dp2px(iv.getContext(), 50f), 0, RoundedCornersTransformation.CornerType.ALL));
        }
        if ((transform & TRANSFORM_GRAYSCALE) != 0) {
            list.add(new GrayscaleTransformation());
        }
        if ((transform & TRANSFORM_BLUR) != 0) {
            list.add(new BlurTransformation());
        }
        return new RequestOptions()
                .placeholder(0)
                .error(0)
                .transforms(list.toArray(new Transformation[list.size()]));
    }
}
