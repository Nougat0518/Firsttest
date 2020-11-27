package com.Nougat.mxep.utils;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    // 获取Application
    public static Context getMyApplication() {
        return instance;
    }
}