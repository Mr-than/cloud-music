package com.example.redrock.base;

import android.app.Application;
import android.content.Context;

/**
 * description:让全局都能获取到当前Context
 * author:冉跃
 * email:2058109198@qq.com
 * date:2022/1/21
 */

public class APP extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
    //使在全局都能获取到context
    public static Context getContext(){
        return context;
    }
}
