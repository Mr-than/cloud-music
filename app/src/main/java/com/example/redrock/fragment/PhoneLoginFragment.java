package com.example.redrock.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.redrock.R;
import com.example.redrock.activity.MainActivity;
import com.example.redrock.dialog.LoginPhoneDialogFragment;
import com.example.redrock.viewModel.LoginPhoneViewModel;

public class PhoneLoginFragment extends Fragment implements View.OnClickListener {
    private LinearLayout areaSelectionButton;
    private View view;
    private MainActivity mainActivity;
    private TextView countryCode;
    private Button nextStep;
    //是否已经打开了弹窗
    private boolean isOpen;



    private LoginPhoneViewModel loginPhoneViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_loginphone,container,false);
       mainActivity=(MainActivity)getActivity();
       mainActivity.isBack=true;

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
        }
    }





}
