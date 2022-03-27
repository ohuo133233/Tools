package com.wang.javatools.utils;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.View;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;

public class AnimationUtils {
    private ObjectAnimator animator;
    private AnimationDrawable animationDrawable;

    /**
     * 平移动画
     *
     * @param view       要移动的物体
     * @param toPosition 出发的位置
     * @param doPosition 到达的位置
     * @param duration   平移的时间
     */
    public ObjectAnimator translation(View view, float toPosition, float doPosition, long duration) {
        animator = null;
        animator = ObjectAnimator.ofFloat(view, "translationX", toPosition, doPosition);
        animator.setDuration(duration);
        return animator;
    }


    /**
     * 帧动画
     *
     * @param resources              Resources对象
     * @param frameAnimationList     帧动画List
     * @param oneShot                是否重复
     * @param frameAnimationDuration 单张图的播放时间
     * @param iAnimationCall         动画回调接口
     */
    public AnimationDrawable frameAnimation(Resources resources,
                                            @DrawableRes ArrayList<Integer> frameAnimationList,
                                            boolean oneShot,
                                            int frameAnimationDuration,
                                            IAnimationCall iAnimationCall) {

        if (resources == null || frameAnimationList == null || frameAnimationList.size() == 0) {
            return null;
        }
        animationDrawable = null;
        animationDrawable = new AnimationDrawable();
        for (int i = 0; i < frameAnimationList.size(); i++) {
            animationDrawable.addFrame(resources.getDrawable(frameAnimationList.get(i)), frameAnimationDuration);
        }
        animationDrawable.setOneShot(oneShot);

        if (iAnimationCall != null) {
            // 调用回调函数onStart
            iAnimationCall.start();
        }

        // 计算动态图片所花费的事件
        int durationTime = 0;
        for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
            durationTime += animationDrawable.getDuration(i);
        }

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (iAnimationCall != null) {
                // 调用回调函数onEnd
                iAnimationCall.end();
            }
        }, durationTime);
        return animationDrawable;
    }

    /**
     * 帧动画
     *
     * @param view                   要移动的物体
     * @param toPosition             出发的位置
     * @param doPosition             到达的位置
     * @param translationDuration    平移的时间
     * @param resources              Resources对象
     * @param frameAnimationList     帧动画List
     * @param oneShot                是否重复
     * @param frameAnimationDuration 单张图的播放时间
     */
    public AnimationDrawable translationAddFrameAnimation(View view,
                                                          float toPosition,
                                                          float doPosition,
                                                          int translationDuration,
                                                          Resources resources,
                                                          @DrawableRes ArrayList<Integer> frameAnimationList,
                                                          boolean oneShot,
                                                          int frameAnimationDuration) {

        animator = translation(view, toPosition, doPosition, translationDuration);
        animationDrawable = frameAnimation(resources, frameAnimationList, oneShot, frameAnimationDuration, null);
        return animationDrawable;
    }

    public void start(IAnimationCall iAnimationCall) {

        if (animator != null) {
            animator.start();
        }
        if (animationDrawable != null) {
            animationDrawable.start();
        }
        if (iAnimationCall != null) {
            iAnimationCall.start();
        }
    }

    interface IAnimationCall {
        void start();

        void end();
    }

}
