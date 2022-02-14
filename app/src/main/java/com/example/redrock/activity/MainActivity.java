package com.example.redrock.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.redrock.R;
import com.example.redrock.base.BaseActivity;
import com.example.redrock.fragment.LoginFragment;
import com.example.redrock.viewModel.MainActivityViewModel;

/**
 *   description:App启动的启动页面，这个活动装载多个fragment，便于在其他地方调用需要的fragment
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/1/21
 */
public class MainActivity extends BaseActivity {

    //这个变量是为了模拟fragment的返回栈效果
   public boolean isBack;

   private MainActivityViewModel mainActivityViewModel;

   private SharedPreferences sp;
   private SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp=getSharedPreferences("Automatic_login",MODE_PRIVATE);
        editor=sp.edit();

        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.setSp(getSharedPreferences("roomIsEmpty",MODE_PRIVATE));
        mainActivityViewModel.setSpFound(getSharedPreferences("isFoundRefresh",MODE_PRIVATE));
        mainActivityViewModel.setSpMy(getSharedPreferences("isMyRefresh",MODE_PRIVATE));
        mainActivityViewModel.setSpDaySong(getSharedPreferences("isSongRefresh",MODE_PRIVATE));

        //替换登录页面
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if(sp.getString("isLogin","")==null||sp.getString("isLogin","").length()==0){

                                replaceFragment(new LoginFragment());
                                isBack=false;

                            }else {

                                Intent intent=new Intent(MainActivity.this,HomePageActivity.class);
                                startActivity(intent);
                            }

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