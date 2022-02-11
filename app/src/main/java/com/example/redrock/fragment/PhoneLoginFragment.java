package com.example.redrock.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.redrock.R;
import com.example.redrock.activity.HomePageActivity;
import com.example.redrock.activity.MainActivity;
import com.example.redrock.base.APP;
import com.example.redrock.dialog.LoginPhoneDialogFragment;
import com.example.redrock.viewModel.LoginPhoneViewModel;

public class PhoneLoginFragment extends Fragment implements View.OnClickListener {
    //下面这个变量获取了装载国家码和旁边三角图片的父布局
    private LinearLayout areaSelectionButton;
    //这个view是加载fragment的布局
    private View view;
    //这个是活动
    private MainActivity mainActivity;
    //国家码的textview
    private TextView countryCode;
    //下一步按钮
    private Button nextStep;
    //是否已经打开了弹窗
    private boolean isOpen;
    //这是mvvm这个fragment的ViewModel
    private LoginPhoneViewModel loginPhoneViewModel;
    //
    private EditText phoneNumber;

    private ImageView topLeft;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_loginphone,container,false);
       mainActivity=(MainActivity)getActivity();
        if (mainActivity != null) {
            mainActivity.isBack=true;
        }

        init();


        return view;
    }


    private void init() {
        isOpen=false;
        areaSelectionButton=view.findViewById(R.id.but_areaSelection);
        areaSelectionButton.setOnClickListener(this);
        countryCode=view.findViewById(R.id.CountryCode_in_home_page);

        loginPhoneViewModel=ViewModelProviders.of(this).get(LoginPhoneViewModel.class);
        loginPhoneViewModel.getLoginPhoneLiveData().observe(mainActivity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                countryCode.setText(s);
            }
        });

        nextStep=view.findViewById(R.id.next_step);
        nextStep.setOnClickListener(this);

        phoneNumber=view.findViewById(R.id.phone_number_edit);

        topLeft=view.findViewById(R.id.ima_topLeft);
        topLeft.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but_areaSelection:{
                if(!isOpen) {
                    isOpen=true;
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    LoginPhoneDialogFragment editNameDialog = new LoginPhoneDialogFragment(loginPhoneViewModel);
                    editNameDialog.show(fm, "fragment_dialog");
                    //1秒中不能打开第二次
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                isOpen=false;
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }break;

            case R.id.next_step:{
                if(phoneNumber.getText().toString().length()==0||phoneNumber.getText().toString()==null){
                    Toast.makeText(getActivity(), "请输入手机号", Toast.LENGTH_SHORT).show();
                }else {
                    if(phoneNumber.getText().toString().length()>11||phoneNumber.getText().toString().length()<11){
                        Toast.makeText(getActivity(), "你输个格式正确的手机号会咋地？", Toast.LENGTH_SHORT).show();
                    }else {
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        PhonePassword fragment = new PhonePassword();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.fra_global, fragment);
                        transaction.commit();
                    }
                }
            }break;

            case R.id.ima_topLeft:{
                Intent intent=new Intent(APP.getContext(), HomePageActivity.class);
                startActivity(intent);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(100);
                            mainActivity.finish();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }break;
            default:break;
        }
    }
}