package com.wang.javatools.utils;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

public class DrawableUtils {

    /**
     * @param resources 使用系统的getResources()
     * @param id        资源ID
     * @return Drawable类型图片
     */
    public Drawable createDrawable(Resources resources, @DrawableRes int id) {
        return resources.getDrawable(id);
    }

    /**
     * 使用Bitmap创建Drawable类型图片
     *
     * @param resources 使用系统的getResources()
     * @param bitmap    bitmap图片
     * @return Drawable类型图片
     */
    public Drawable createDrawable(Resources resources, Bitmap bitmap) {
        return new BitmapDrawable(resources, bitmap);
    }


}
