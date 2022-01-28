package com.example.redrock.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.redrock.R;
import com.example.redrock.base.BaseActivity;
import com.example.redrock.fragment.LoginFragment;
import com.example.redrock.fragment.PhoneLoginFragment;

//这个活动用来做为应用启动的登录页面
public class MainActivity extends BaseActivity {

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