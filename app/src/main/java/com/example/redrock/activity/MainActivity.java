package com.example.redrock.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.redrock.R;
import com.example.redrock.base.BaseActivity;
import com.example.redrock.fragment.LoginFragment;

//这个活动用来做为应用启动的登录页面
public class MainActivity extends BaseActivity {
    ReplaceHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //替换登录页面
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Message message=new Message();
                    message.obj="start";
                    handler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void init(){
        handler=new ReplaceHandler();
    }

    public void replaceFragment(){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fra_global,new LoginFragment());
        transaction.commit();
    }

    private class ReplaceHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            replaceFragment();
        }
    }
}