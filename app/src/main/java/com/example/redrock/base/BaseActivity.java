package com.example.redrock.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * description:让全局的activity都继承自这个类，便于全局管理所有activity
 * author:冉跃
 * email:2058109198@qq.com
 * date:2022/1/21
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        Window window = getWindow();
        View decorView = window.getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { // fitsSystemWindows 过时替代方法--安卓11及以上才有 windowInsetsController
            // 取消状态栏
            window.setDecorFitsSystemWindows(false);
            // 状态栏字体颜色变黑
            decorView.getWindowInsetsController().setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        } else {
            // 取消状态栏，已经做了判断使用
            int option = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            decorView.setSystemUiVisibility(option);
            // 状态栏字体颜色变黑
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | option);
            }
        }
        window.setStatusBarColor(Color.TRANSPARENT); // 把状态栏颜色设置成透明

    }
}
