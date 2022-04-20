package com.wang.javatools.manager;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;

import com.wang.javatools.data.DataManage;

import java.util.Locale;

/**
 * 本地化Manage
 * 目前支持
 * 1.中英文语言切换，暂时不支持跟随系统切换
 */
public class LocalManage {
    private static final String TAG = LocalManage.class.getSimpleName();
    private static final String LANGUAGE = "language";

    private LocalManage() {

    }

    private static class LocalManageHolder {
        private static final LocalManage instance = new LocalManage();
    }

    public static LocalManage getInstance() {
        return LocalManageHolder.instance;
    }

    public Context setLocal(Context context) {
        return updateResources(context, getSetLanguageLocale());
    }

    private Context updateResources(Context context, Locale locale) {
        Locale.setDefault(locale);

        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        context = context.createConfigurationContext(config);
        return context;
    }

    /**
     * 获取当前选择的语言设置
     *
     * @return
     */
    public Locale getSetLanguageLocale() {
        switch (getSelectLanguage()) {
            case 1:
                return Locale.CHINA;
            case 2:
                return Locale.ENGLISH;
            default:
                return getSystemLocale();
        }
    }

    private int getSelectLanguage() {
        return DataManage.getInstance().get(LANGUAGE);
    }

    /**
     * 获取系统的locale
     *
     * @return Locale对象
     */
    public Locale getSystemLocale() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }

    /**
     * 设置为中文
     */
    public void setChina() {
        DataManage.getInstance().put(LANGUAGE, 1);
    }

    /**
     * 设置为英文
     */
    public void setEnglish() {
        DataManage.getInstance().put(LANGUAGE, 2);
    }


}
