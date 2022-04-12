package com.wang.javatools.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

import com.wang.javatools.base.LifecycleObserver;

/**
 * 自定义Dialog类，
 * 帮你简化Dialog创建，使用Build类快速创建。
 * 通过传入的布局、按钮、Text，快速获取到Dialog，不提供默认模板
 * 返回对象是Dialog，你依然可以拿到返回Dialog进行封装和修改
 * 无需关注Dialog的生命周期，会跟随传入的页面自动销毁，也可以自己调用dismiss
 * <p>
 * 使用指南
 * 1.如何定义Dialog中的属性
 * 如果是初始化属性直接去布局修改.
 * 示例:<TextView android:textColor="@android:color/holo_blue_bright"/>
 * <p>
 * 如果有在点击事件中修改Dialog修改按钮属性
 * 示例 onClick(v) 拿v来设置
 * <p>
 * 如果需要在点击事件修改Dialog其他属性
 * 先Build创建完Dialog，再使用@getView()获取View的点击事件，再拿Dialog对象设置
 * <p>
 * 2.大部分方法使用了注解来避免传入错误，使用的注解都是androidx.annotation里面的注解，没有自定义，注意导包。
 * <p>
 * 3.设置宽和高的属性
 * 使用ViewGroup.LayoutParams的字段设置填充效果，或者直接传入具体大小
 * 示例：
 * .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
 * .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
 * .setWidth(10)
 * <p>
 * 4.如果出现
 * 找不到***方法，原因这个view不是继承***
 * 请查看传入的View是否带有***的方法，如果有直接修改当前方法或者增加重载方法
 * <p>
 */

public class CommonDialog extends Dialog implements LifecycleObserver {
    private static final String TAG = "CommonDialog";
    private Build mBuild;

    /**
     * 提供给Activity创建的Dialog的构造方法
     *
     * @param build            Build对象
     * @param fragmentActivity Activity对象
     */
    private CommonDialog(@NonNull Build build, FragmentActivity fragmentActivity) {
        // 使用自定义Dialog样式
        super(fragmentActivity, build.mStyle);

        fragmentActivity.getLifecycle().addObserver(this);

        mBuild = build;
        build();
    }

    /**
     * 提供给Fragment对象创建的Dialog的构造方法
     *
     * @param build    Build对象
     * @param fragment fragment对象
     */
    private CommonDialog(@NonNull Build build, Fragment fragment) {
        // 使用自定义Dialog样式
        super(fragment.getContext(), build.mStyle);

        fragment.getLifecycle().addObserver(this);

        mBuild = build;
        build();
    }

    private void build() {
        setContentView(mBuild.mRoot);
        // 设置宽和高
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = this.mBuild.mHeight;
        params.width = this.mBuild.mWidth;
        getWindow().setAttributes(params);

        // 设置点击Dialog以外的区域时Dialog消失
        setCanceledOnTouchOutside(mBuild.mCancel);
    }


    public <T extends View> T getView(@IdRes int viewId) {
        return this.mBuild.mRoot.findViewById(viewId);
    }

    /**
     * 自动处理生命周期，当Activity销毁时销毁自己
     * 解决忘记调用dismiss 出现的异常
     * android.view.WindowLeaked: Activity xxxActivity has leaked window DecorView[xxxActivity] that was originally added here
     *
     * @param owner LifecycleOwner对象
     */
    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        // 经过测试多次调用dismiss()没有异常
        Log.e(TAG, "自动销毁Dialog");
        this.dismiss();
    }

    @Override
    public void dismiss() {
        Log.d(TAG, "dismiss");
        super.dismiss();
    }

    public static class Build {
        private final Context mContext;
        private FragmentActivity fragmentActivity;
        private Fragment fragment;
        private View mRoot;
        private int mWidth;
        private int mHeight;
        private boolean mCancel;
        private int mStyle;

        public Build(FragmentActivity fragmentActivity) {
            this.mContext = fragmentActivity;
            this.fragmentActivity = fragmentActivity;
        }

        public Build(Fragment fragment) {
            this.mContext = fragment.getContext();
            this.fragment = fragment;
        }

        public Build setLayout(@LayoutRes int mLayout) {
            mRoot = LayoutInflater.from(mContext).inflate(mLayout, null, false);
            return this;
        }

        public Build setWidth(int width) {
            mWidth = width;
            return this;
        }

        public Build setHeight(int height) {
            mHeight = height;
            return this;
        }

        public Build setText(@IdRes int viewId, @NonNull String text) {
            View view = mRoot.findViewById(viewId);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setText(text);
            } else {
                throw new NullPointerException("找不到setText方法，原因这个view不是继承TextView");
            }
            return this;
        }

        public Build setImageResource(@IdRes int viewId, @DrawableRes int drawableResId) {
            View view = mRoot.findViewById(viewId);
            if (view instanceof ImageView) {
                ImageView imageView = (ImageView) view;
                imageView.setImageResource(drawableResId);
            } else {
                throw new NullPointerException("找不到setImageResource方法，原因这个view不是继承ImageView");
            }
            return this;
        }

        public Build setOnClickListener(@IdRes int viewId, View.OnClickListener onClickListener) {
            View view = mRoot.findViewById(viewId);
            view.setOnClickListener(v -> onClickListener.onClick(v));
            return this;
        }

        public Build startAnimation(@IdRes int viewId, Animation animation) {
            View view = mRoot.findViewById(viewId);
            view.startAnimation(animation);
            animation.start();
            return this;
        }

        public Build setCanceledOnTouchOutside(boolean cancel) {
            mCancel = cancel;
            return this;
        }

        public Build setStyle(@StyleRes int style) {
            mStyle = style;
            return this;
        }

        public CommonDialog build() {
            if (fragmentActivity != null) {
                return new CommonDialog(this, fragmentActivity);
            } else {
                return new CommonDialog(this, fragment);
            }
        }

    }
}
