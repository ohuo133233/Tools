package com.wang.javatools.widget.popupwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.LayoutRes;

public class CommonPopupWindow extends PopupWindow {

    public CommonPopupWindow(Build build) {
        super(build.mContext);
        View contentView = getContentView();

        // 需要先测量，PopupWindow还未弹出时，宽高为0
//        contentView.measure(makeDropDownMeasureSpec(getWidth()), makeDropDownMeasureSpec(getHeight()));

        setHeight(build.height);
        setWidth(build.width);

        setOutsideTouchable(build.touchable);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(build.mRoot);

    }


    private  int makeDropDownMeasureSpec(int measureSpec) {
        int mode;
        if (measureSpec == ViewGroup.LayoutParams.WRAP_CONTENT) {
            mode = View.MeasureSpec.UNSPECIFIED;
        } else {
            mode = View.MeasureSpec.EXACTLY;
        }
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(measureSpec), mode);
    }


//    public void showBottomTOLeft(View view) {
//        int offsetX = -getContentView().getMeasuredWidth();
//        int offsetY = 0;
//        showAsDropDown(getContentView(), mButton, offsetX, offsetY, Gravity.START);
//    }

    public static class Build {
        private View mRoot;
        private Context mContext;
        private int width;
        private int height;
        private boolean touchable;

        public CommonPopupWindow.Build setContext(Context mContext) {
            this.mContext = mContext;
            return this;
        }

        public CommonPopupWindow.Build setLayout(@LayoutRes int mLayout) {
            this.mRoot = LayoutInflater.from(mContext).inflate(mLayout, null, false);
            return this;
        }

        public CommonPopupWindow.Build setWidth(int width) {
            this.width = width;
            return this;
        }

        public CommonPopupWindow.Build setHeight(int height) {
            this.height = height;
            return this;
        }

        public CommonPopupWindow.Build setOutsideTouchable(boolean touchable) {
            this.touchable = touchable;
            return this;
        }

        public CommonPopupWindow Build() {
            return new CommonPopupWindow(this);
        }
    }
}
