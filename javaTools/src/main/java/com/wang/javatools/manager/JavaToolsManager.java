package com.wang.javatools.manager;

import android.app.Application;

import com.tencent.mmkv.MMKV;
import com.wang.javatools.base.BaseConstant;

public class JavaToolsManager {

    private static boolean mIsDebug;

    public JavaToolsManager(JavaToolsManager.Build build) {
        JavaToolsManager.mIsDebug = build.mIsDebug;
        init(build);
    }

    private void init(Build build) {
        MMKV.initialize(build.mApplication);

        AppManager.getInstance().setFirstStart(false);
        AppManager.getInstance().setFirstStartDays();
        AppManager.getInstance().setDebug();

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
