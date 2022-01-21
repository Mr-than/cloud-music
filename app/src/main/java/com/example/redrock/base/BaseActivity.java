package com.example.redrock.base;

import android.os.Bundle;

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

    }
}
