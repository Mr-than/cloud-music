package com.example.redrock.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.redrock.R;
import com.example.redrock.activity.MainActivity;
import com.example.redrock.base.APP;
import com.example.redrock.model.InternetTool;
import com.example.redrock.viewmodel.MainActivityViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *   description:验证码输入页面
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class VerificationCodeFragment extends Fragment {
    private View view;
    private EditText e1,e2,e3,e4;
    private String code;
    private boolean is;

    //这个是活动
    private MainActivity mainActivity;
    private InternetTool tool;
    private String phone;
    private String password;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.verification_fragment,container,false);
        mainActivity=(MainActivity)getActivity();

        init();




        MainActivityViewModel mainActivityViewModel= ViewModelProviders.of((MainActivity)getActivity()).get(MainActivityViewModel.class);
        mainActivityViewModel.phonePwd.observe((MainActivity) getActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

                phone=strings.get(0);
                password=strings.get(1);

                String p=phone.substring(0,4)+"*****"+phone.substring(phone.length()-4);

                textView.setText(p);

                tool.setBaseUrl("http://redrock.udday.cn:2022")
                        .setRequestType(InternetTool.GET)
                        .setPortPath("/captcha/sent")
                        .setRequestData("phone",phone)
                        .startRequest(new InternetTool.Back() {
                            @Override
                            public void onError() {
                            }

                            @Override
                            public void onFinish(String data) {

                            }
                        });

            }
        });


        return view;
    }

    private void init() {

        textView=view.findViewById(R.id.phone_number);
        tool=new InternetTool();
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        is=true;
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        e1=view.findViewById(R.id.e1);
        e2=view.findViewById(R.id.e2);
        e3=view.findViewById(R.id.e3);
        e4=view.findViewById(R.id.e4);

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        e1.setCursorVisible(false);
        e2.setCursorVisible(false);
        e3.setCursorVisible(false);
        e4.setCursorVisible(false);

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        List<EditText> list=new ArrayList<>();
        list.add(e2);
        list.add(e3);
        list.add(e4);

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
                        e4.requestFocus();
                        e4.setSelection(1);
                        e4.setFocusableInTouchMode(true);
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(e4, 0);
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
                        e4.requestFocus();
                        e4.setSelection(1);
                        e4.setFocusableInTouchMode(true);
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(e4, 0);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                code=e1.getText().toString();

            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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

                    if(e1.getText().toString().length()>0) {
                        e1.setSelection(1);
                    }else {
                        e1.setSelection(0);
                    }
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(e1, 0);
                }

            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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

                    if(e2.getText().toString().length()>0) {
                        e2.setSelection(1);
                    }else {
                        e2.setSelection(0);
                    }

                    e2.setFocusableInTouchMode(true);
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(e2, 0);
                }

            }
        });
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        e4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(e4.getText().toString().length()<=0||e4.getText().toString()==null){
                    e3.requestFocus();

                    if(e3.getText().toString().length()>0) {
                        e3.setSelection(1);
                    }else {
                        e3.setSelection(0);
                    }

                    e3.setFocusableInTouchMode(true);
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(e3, 0);
                }else if(e4.getText().toString().length()==1){

                    String v=e1.getText().toString()+e2.getText().toString()+
                            e3.getText().toString()+e4.getText().toString();

                    if(v.length()==4) {
                        tool = new InternetTool();
                        tool.setBaseUrl("http://redrock.udday.cn:2022")
                                .setRequestType(InternetTool.GET)
                                .setPortPath("/register/cellphone")
                                .setRequestData("phone", phone)
                                .setRequestData("password", password)
                                .setRequestData("captcha", v)
                                .startRequest(new InternetTool.Back() {
                                    @Override
                                    public void onError() {
                                    }

                                    @Override
                                    public void onFinish(String data) {

                                        try {
                                            JSONObject jsonObject=new JSONObject(data);
                                            String code=jsonObject.getString("code");

                                            if(code.equals("200")){
                                                getActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(APP.getContext(), "密码修改成功", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }else {

                                                JSONObject jsonObject1=new JSONObject(data);
                                                String msg=jsonObject1.getString("message");

                                                getActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {


                                                        Toast.makeText(APP.getContext(), msg, Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    }else {

                        Toast.makeText(APP.getContext(), "验证码错误", Toast.LENGTH_SHORT).show();

                    }

                }



            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

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