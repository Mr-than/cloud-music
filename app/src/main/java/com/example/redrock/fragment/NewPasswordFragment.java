package com.example.redrock.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.redrock.R;
import com.example.redrock.base.APP;
import com.example.redrock.viewmodel.MainActivityViewModel;

/**
 *   description:设置新密码的界面
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class NewPasswordFragment extends Fragment {

    private View view;
    private EditText editText;
    private Button button;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_new_password,container,false);
        editText=view.findViewById(R.id.new_password);
        button=view.findViewById(R.id.login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((editText.getText().toString()==null||editText.getText().toString().length()==0)||editText.getText().toString().length()<8){
                    Toast.makeText(APP.getContext(), "密码长度必须大于8位", Toast.LENGTH_SHORT).show();
                }else {
                    MainActivityViewModel mainActivityViewModel= ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);

                    FragmentManager manager= getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction=manager.beginTransaction();
                    transaction.replace(R.id.fra_global,new VerificationCodeFragment());
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    transaction.commit();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            mainActivityViewModel.setPhonePwd(editText.getText().toString());
                        }
                    }).start();

                }


            }
        });


        return view;
    }
}
