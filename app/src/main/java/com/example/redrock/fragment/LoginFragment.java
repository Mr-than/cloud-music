package com.example.redrock.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.example.redrock.R;
import com.example.redrock.activity.MainActivity;
import com.example.redrock.base.APP;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private LottieAnimationView boxLottie;
    //控制lottie动画的循环，至于为什么要用lottie动画来代替checkbox，是因为checkbox的大小很难调整，而且颜色过于违和。
    private boolean isChecked;
    //下面的对象控制控件的抖动效果
    private LinearLayout belowLayout;
    private Animation shake;
    //下面是各种按钮
    private Button phoneLogin,wechatLogin;
    //下面是Toast
    private Toast toast;
    //下面是三个其他方式登录
    private ImageView leftImage,centerImage,rightImage;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login,container,false);
        init(view);



        return view;
    }

    private void init(View view) {



        //底部的checkbox形状的lottie动画
        boxLottie=view.findViewById(R.id.box_belowLeft);
        boxLottie.setOnClickListener(this);
        isChecked=false;
        //底部字体左右抖动效果
        shake= AnimationUtils.loadAnimation(APP.getContext(),R.anim.shake);
        belowLayout=view.findViewById(R.id.loginLayout_below);
        //按钮
        phoneLogin=view.findViewById(R.id.phone_login);
        wechatLogin=view.findViewById(R.id.wechat_login);
        phoneLogin.setOnClickListener(this);
        wechatLogin.setOnClickListener(this);
        //Toast
        toast=Toast.makeText(APP.getContext(),"请勾选同意！",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        //其他登录方式
        leftImage=view.findViewById(R.id.ima_belowLeft);
        leftImage.setOnClickListener(this);
        centerImage=view.findViewById(R.id.ima_belowCenter);
        centerImage.setOnClickListener(this);
        rightImage=view.findViewById(R.id.ima_belowRight);
        rightImage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.box_belowLeft:{
                if(this.isChecked) {
                    boxLottie.setProgress(0f);
                    this.isChecked=false;
                }else{
                    boxLottie.setProgress(0.5f);
                    this.isChecked=true;
                }
            }break;
            case R.id.phone_login:{
                if(isChecked){
                    replace(new PhoneLoginFragment());
                }else{
                    Shake();
                }
            }break;
            case R.id.wechat_login:{
                if(isChecked){

                }else{
                    Shake();
                }
            }break;
            case R.id.ima_belowLeft:{
                if(isChecked){

                }else{
                    Shake();
                }
            }break;
            case R.id.ima_belowCenter:{
                if(isChecked){

                }else{
                    Shake();
                }
            }break;
            case R.id.ima_belowRight:{
                if(isChecked){

                }else{
                    Shake();
                }
            }break;
            default:break;
        }
    }

    private void Shake(){
        toast.show();
        belowLayout.startAnimation(shake);
    }

    private void replace(Fragment fragment){
        FragmentManager manager= getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fra_global,fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        transaction.addToBackStack(null);
        transaction.commit();

    }






}
