package com.example.redrock.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.redrock.R;
import com.example.redrock.base.BaseActivity;
import com.example.redrock.fragment.LoginFragment;
import com.example.redrock.fragment.PhoneLoginFragment;

/**
 *   description:App启动的启动页面，这个活动装载多个fragment，便于在其他地方调用需要的fragment
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/1/21
 */
public class MainActivity extends BaseActivity {

    //这个变量是为了模拟fragment的返回栈效果
   public boolean isBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //替换登录页面
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            replaceFragment(new LoginFragment());
                            isBack=false;
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(isBack) {
            replaceFragment(new LoginFragment());
       }else {

            finish();
        }
    }


    public void replaceFragment(Fragment fragment){
        isBack=false;
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fra_global,fragment);
        transaction.commit();
    }


}