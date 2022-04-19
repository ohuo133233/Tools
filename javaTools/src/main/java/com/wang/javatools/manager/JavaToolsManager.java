package com.wang.javatools.manager;

import android.app.Application;
import android.content.Intent;

import com.tencent.mmkv.MMKV;
import com.wang.javatools.BuildConfig;
import com.wang.javatools.base.BaseConstant;
import com.wang.javatools.base.MyService;

public class JavaToolsManager {

    private static boolean mIsDebug;

    private JavaToolsManager(JavaToolsManager.Build build) {
        JavaToolsManager.mIsDebug = build.mIsDebug;
        init(build);
    }

    private void init(Build build) {
        MMKV.initialize(build.mApplication);
        AppManager.getInstance().setApplicationContext(build.mApplication.getApplicationContext());

        // 启动一个服务，用于全局收集横竖屏事件、
        // TODO 赋予更多任务，但不要是耗时的
        Intent intent = new Intent(build.mApplication.getApplicationContext(), MyService.class);
        build.mApplication.startService(intent);

        if (BuildConfig.DEBUG) {
            AppManager.getInstance().setDebug();
        }
        BaseConstant.BASE_URL = build.mBaseUrl;
    }


    public static boolean isIsDebug() {
        return mIsDebug;
    }


    public static class Build {
        private boolean mIsDebug;
        private Application mApplication;
        private String mBaseUrl;


        public Build setIsDebug(boolean isDebug) {
            this.mIsDebug = isDebug;
            return this;
        }

        public Build setApplication(Application myApplication) {
            this.mApplication = myApplication;
            return this;
        }

        public Build log() {
            return this;
        }

        public Build baseUrl(String baseUrl) {
            this.mBaseUrl = baseUrl;
            return this;
        }

        public JavaToolsManager build() {
            return new JavaToolsManager(this);
        }

    }


}
