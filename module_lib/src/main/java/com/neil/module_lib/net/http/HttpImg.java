package com.neil.module_lib.net.http;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.neil.module_lib.util.config.LibConfig;
import com.neil.module_lib.util.util.LibUtil;

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
    public interface Options {
        int TRANSFORM_NONE = 0;
        int TRANSFORM_CIRCLE = 1 << 0;
        int TRANSFORM_ROUNDERCORNE = 1 << 1;
        int TRANSFORM_GRAYSCALE = 1 << 2;
        int TRANSFORM_BLUR = 1 << 3;
    }

    /**
     * @param res 数据源：String  >File,Uri,resId,asset    >byte,bitmap,drawable
     */
    public static void display(ImageView iv, Object res) {
        display(iv, res, Options.TRANSFORM_NONE);
    }

    /**
     * @param res       数据源：String  >File,Uri,resId,asset    >byte,bitmap,drawable
     * @param transform 图片处理效果（可组合）
     */
    public static void display(ImageView iv, Object res, int transform) {
        displayImp(iv, res, transform);
    }

    //--------------------------------------------------------
    /**
     * 实现：显示
     */
    private static void displayImp(ImageView iv, Object res, int transform) {
        try {
            Glide.with(iv.getContext())
                    .load(res)
                    .apply(getRequestOptions(iv, transform))        //变换效果
                    .transition(new DrawableTransitionOptions().crossFade(LibConfig.Net.glide_crossFadeTime))//过渡效果
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
        if ((transform & Options.TRANSFORM_CIRCLE) != 0) {
            list.add(new CircleCrop());
        }
        if ((transform & Options.TRANSFORM_ROUNDERCORNE) != 0) {
            list.add(new RoundedCornersTransformation(LibUtil.dp2px(iv.getContext(), 50f), 0, RoundedCornersTransformation.CornerType.ALL));
        }
        if ((transform & Options.TRANSFORM_GRAYSCALE) != 0) {
            list.add(new GrayscaleTransformation());
        }
        if ((transform & Options.TRANSFORM_BLUR) != 0) {
            list.add(new BlurTransformation());
        }
        return new RequestOptions()
                .placeholder(0)
                .error(0)
                .transforms(list.toArray(new Transformation[list.size()]));
    }
}
