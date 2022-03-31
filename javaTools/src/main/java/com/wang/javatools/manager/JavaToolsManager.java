package com.wang.javatools.manager;

public class JavaToolsManager {

    private static boolean mIsDebug;

    public JavaToolsManager(JavaToolsManager.Build build) {
        JavaToolsManager.mIsDebug = build.mIsDebug;
    }


    public static boolean isIsDebug() {
        return mIsDebug;
    }


    public static class Build {
        private boolean mIsDebug;

        public Build setIsDebug(boolean isDebug) {
            this.mIsDebug = isDebug;
            return this;
        }

        public Build log() {

            return this;
        }

        public JavaToolsManager build() {
            return new JavaToolsManager(this);
        }


    }


}
