package com.wang.javatools.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class BitmapUtils {

    /**
     * 创建一个Bitmap
     *
     * @param absolutePath 图片的路径
     * @return 创建的Bitmap
     */
    public Bitmap createBitmap(String absolutePath) {
        BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
        return BitmapFactory.decodeFile(absolutePath, tmpOptions);
    }

    /**
     * 创建一个Bitmap
     *
     * @param drawable 传入Drawable图片
     * @return 创建的Bitmap
     */
    public Bitmap createBitmap(Drawable drawable) {
        return getBitmap(drawable);
    }


    /**
     * 把drawable转为Bitmap类型
     *
     * @param drawable 需要转换的drawable
     * @return bitmap
     */
    public Bitmap getBitmap(Drawable drawable) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        return bitmapDrawable.getBitmap();
    }


}