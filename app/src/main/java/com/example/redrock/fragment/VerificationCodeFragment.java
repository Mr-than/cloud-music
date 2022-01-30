package com.example.redrock.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.redrock.R;
import com.example.redrock.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class VerificationCodeFragment extends Fragment {
    private View view;
    private EditText e1,e2,e3,e4,e5,e6;
    private String code;
    private boolean is;
    private Button phonePassword;
    //这个是活动
    private MainActivity mainActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.verification_fragment,container,false);
        mainActivity=(MainActivity)getActivity();


        init();





        return view;
    }

    private void init() {

        phonePassword=view.findViewById(R.id.phone_password_login);
        phonePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fra_global,new PhonePassword());

                transaction.commit();
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++
        is=true;
        //+++++++++++++++++++++++++++++++++++++++++++++++
        e1=view.findViewById(R.id.e1);
        e2=view.findViewById(R.id.e2);
        e3=view.findViewById(R.id.e3);
        e4=view.findViewById(R.id.e4);
        e5=view.findViewById(R.id.e5);
        e6=view.findViewById(R.id.e6);
        //+++++++++++++++++++++++++++++++++++++++++++++++
        e1.setCursorVisible(false);
        e2.setCursorVisible(false);
        e3.setCursorVisible(false);
        e4.setCursorVisible(false);
        e5.setCursorVisible(false);
        e6.setCursorVisible(false);
        //+++++++++++++++++++++++++++++++++++++++++++++++
        List<EditText> list=new ArrayList<>();
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        //+++++++++++++++++++++++++++++++++++++++++++++++
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int e = 0;
                code=e1.getText().toString();
                char[] a;
                if(code.length()>2) {
                    a = code.substring(1, code.length()).toCharArray();
                    for (; e < list.size() && e < a.length; e++) {
                        list.get(e).setText(String.valueOf(a[e]));
                    }
                }
                if(code.length()==1&&is){
                    if(is){
                        is=false;
                        e1.setText(code);
                        changeIs();
                    }
                    if(e!=list.size()) {
                        e2.requestFocus();
                        e2.setFocusableInTouchMode(true);
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(e2, 0);
                    }else {
                        e6.requestFocus();
                        e6.setSelection(1);
                        e6.setFocusableInTouchMode(true);
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(e6, 0);
                    }
                }
                else if(is&&e1.getText().toString().length()>1){
                    if(is){
                        is=false;
                        e1.setText(String.valueOf(code.toCharArray()[0]));
                        changeIs();
                    }
                    if(e!=list.size()) {
                        e2.requestFocus();
                        e2.setSelection(1);
                        e2.setFocusableInTouchMode(true);
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(e2, 0);
                    }else {
                        e6.requestFocus();
                        e6.setSelection(1);
                        e6.setFocusableInTouchMode(true);
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(e6, 0);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                code=e1.getText().toString();

            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                e3.requestFocus();
                e3.setFocusableInTouchMode(true);

                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(e3, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(e2.getText().toString().length()<=0||e2.getText().toString()==null){
                    e1.requestFocus();
                    e1.setFocusableInTouchMode(true);
                    e1.setSelection(1);
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(e1, 0);
                }

            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        e3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                e4.requestFocus();
                e4.setFocusableInTouchMode(true);
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(e4, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(e3.getText().toString().length()<=0||e3.getText().toString()==null){
                    e2.requestFocus();
                    e2.setSelection(1);
                    e2.setFocusableInTouchMode(true);
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(e2, 0);
                }

            }
        });
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        e4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                e5.requestFocus();
                e5.setFocusableInTouchMode(true);
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(e5, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(e4.getText().toString().length()<=0||e4.getText().toString()==null){
                    e3.requestFocus();
                    e3.setSelection(1);
                    e3.setFocusableInTouchMode(true);
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(e3, 0);
                }
            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        e5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                e6.requestFocus();
                e6.setFocusableInTouchMode(true);
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(e6, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(e5.getText().toString().length()<=0||e5.getText().toString()==null){
                    e4.requestFocus();
                    e4.setSelection(1);
                    e4.setFocusableInTouchMode(true);
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(e4, 0);
                }

            }
        });

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        e6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(e6.getText().toString().length()<=0||e6.getText().toString()==null){
                    e5.requestFocus();
                    e5.setSelection(1);
                    e5.setFocusableInTouchMode(true);
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(e5, 0);
                }else if(e6.getText().toString().length()==1){
                    Toast.makeText(getActivity(), "成功！！", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void changeIs(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                    is=true;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
