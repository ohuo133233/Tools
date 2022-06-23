package com.wang.uilibrary.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class TwinkleTextView extends androidx.appcompat.widget.AppCompatTextView {
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;

    public TwinkleTextView(Context context) {
        super(context);
    }

    public TwinkleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TwinkleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //设置一个线性渲染器
        //参数1 渲染的起始x坐标 参数2 渲染的起始y坐标
        // 参数3 渲染的结束x坐标 参数4 渲染的结束y坐标
        //参数5 渲染颜色 大于2钟 参数7 渲染的模式
        mLinearGradient = new LinearGradient(0, 0, getMeasuredWidth(), 0, new int[]{Color.BLUE, Color.WHITE, Color.RED},
                null, Shader.TileMode.REPEAT);
        //得到画笔，设置渲染器
        getPaint().setShader(mLinearGradient);
        mGradientMatrix = new Matrix();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null) {
            //设置平移的距离
            mTranslate += getMeasuredWidth() / 5;
            //判断是否越界
            if (mTranslate >= 2 * getMeasuredWidth()) {
                //设置起点
                mTranslate = -getMeasuredWidth();
            }
            //设置移动的距离
            mGradientMatrix.setTranslate(mTranslate, 0);
            //渲染器加载matrix
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);//延迟100ms后重绘
        }
    }


}
