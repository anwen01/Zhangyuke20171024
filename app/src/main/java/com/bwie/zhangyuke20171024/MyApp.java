package com.bwie.zhangyuke20171024;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * 作者：张玉轲
 * 时间：2017/10/24
 */

public class MyApp extends Application {
    public static MyApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
       // CrashHandler.getInstance(). init(this);
        mInstance = this;
    }

    public static MyApp getInstance() {
        return mInstance;
    }
}
