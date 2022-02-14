package com.example.redrock.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.example.redrock.base.BaseActivity;
import com.example.redrock.room.LoginBeanDao;
import com.example.redrock.viewmodel.HomePageViewModel;
import com.example.redrock.viewmodel.MainActivityViewModel;

public class PhonePassword extends Fragment {

    private Button login;
    private View view;
    private MainActivity mainActivity;
    private MainActivityViewModel mainActivityViewModel;
    private EditText password;
    private Toast toast;
    private ProgressBar progressBar;
    private HomePageActivity homePageActivity;
    private HomePageViewModel homePageViewModel;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Button button;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.phone_password, container, false);
        init();


        mainActivityViewModel.msg.observe(mainActivity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressBar.setVisibility(View.GONE);
               toast=Toast.makeText(APP.getContext(),s,Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,0,0);
               toast.show();
            }

        });

        mainActivityViewModel.jump.observe(mainActivity, new Observer<LoginBeanDao>() {
            @Override
            public void onChanged(LoginBeanDao l) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mainActivity.startActivity(new Intent(mainActivity, HomePageActivity.class));

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    homePageActivity=HomePageActivity.HOME_PAGE_ACTIVITY;
                    homePageViewModel= ViewModelProviders.of(homePageActivity).get(HomePageViewModel.class);
                    homePageViewModel.setLoginDao(l);
                    editor.putString("isLogin","login");
                    editor.apply();
                }
            }).start();
            }
        });

        return view;
    }

    private void init() {

        button=view.findViewById(R.id.forget_possword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager manager= getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fra_global,new NewPasswordFragment());
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });




        sp=APP.getContext().getSharedPreferences("Automatic_login", BaseActivity.MODE_PRIVATE);
        editor=sp.edit();

        progressBar=view.findViewById(R.id.login_proBar);
        progressBar.setVisibility(View.GONE);

        password = view.findViewById(R.id.phone_password_edit);

        mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivityViewModel = ViewModelProviders.of(mainActivity).get(MainActivityViewModel.class);



            login = view.findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                            login.requestFocus();
                            login.setFocusableInTouchMode(true);
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(login, 0);

                            progressBar.setVisibility(View.VISIBLE);

                            if (password.getText().toString().length() == 0) {
                                progressBar.setVisibility(View.GONE);
                                Toast toast=Toast.makeText(APP.getContext(),"请输入密码",Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.TOP,0,0);
                                toast.show();
                            } else {
                                mainActivityViewModel.setPassword(password.getText().toString());
                                mainActivityViewModel.forData();
                            }

                }
            });
        }


    }

}