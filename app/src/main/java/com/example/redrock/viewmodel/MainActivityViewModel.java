package com.example.redrock.viewModel;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.redrock.activity.HomePageActivity;
import com.example.redrock.base.APP;
import com.example.redrock.bean.LoginBean;
import com.example.redrock.model.InternetTool;
import com.example.redrock.room.HomePageDataBase;
import com.example.redrock.room.LoginBeanDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;

public class MainActivityViewModel extends ViewModel {


    private String phone;
    private String password;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private SharedPreferences spFound;
    private SharedPreferences.Editor editor1;

    private SharedPreferences spMy;
    private SharedPreferences.Editor editor2;

    private SharedPreferences spDaySong;
    private SharedPreferences.Editor editor3;


    private MutableLiveData<String> _msg=new MutableLiveData<>();
    private MutableLiveData<LoginBeanDao> _jump=new MutableLiveData<>();

    private HomePageViewModel homePageViewModel;
    private HomePageActivity homePageActivity;

    public LiveData<LoginBeanDao> jump=_jump;


    public LiveData<String> msg=_msg;

    private HomePageDataBase dataBase= Room.databaseBuilder(APP.getContext(),HomePageDataBase.class,"HomeFoundWonderful").build();
    private LoginBeanDao loginBeanDao= dataBase.loginBeanDao();

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    InternetTool tool;
    public void forData(){
        tool=new InternetTool();

        tool.setBaseUrl("http://redrock.udday.cn:2022")
                .setPortPath("/login/cellphone")
                .setRequestType(InternetTool.GET)
                .setRequestData("phone",phone)
                .setRequestData("password",password)
                .setRequestData("timestamp",String.valueOf(System.currentTimeMillis()))
                .startRequest(new InternetTool.Back() {
                    @Override
                    public void onError() {
                        _msg.postValue("账号或密码错误");
                    }

                    @Override
                    public void onFinish(String data) {
                        getData(data);
                    }
                });
    }

    private void getData(String data){
        try {
        JSONObject jsonObject=new JSONObject(data);

        String code =jsonObject.getString("code");

        if(code.equals("200")){
            Gson gson=new Gson();
            LoginBean loginBean=gson.fromJson(data,new TypeToken<LoginBean>(){}.getType());

            if(sp.getString("is","")==null||sp.getString("is","").length()==0){
                loginBeanDao.addData(loginBean);
                editor.putString("is","NOT_EMPTY");
                editor.apply();
            }else {



                int a=0;
                while (a==0) {
                    loginBeanDao.update(loginBean);
                    a = loginBeanDao.upC(loginBean.getCookie());
                }
                editor1.putString("refresh_1","refresh");
                editor1.apply();
                editor2.putString("refresh_2","refresh");
                editor2.apply();
                editor3.putString("refresh_3","refresh");
                editor3.apply();
            }
            _jump.postValue(loginBeanDao);
        }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setSp(SharedPreferences sp){
        this.sp =sp;
        this.editor= this.sp.edit();
    }

    public void setSpFound(SharedPreferences spFound) {
        this.spFound = spFound;
        editor1= spFound.edit();
    }

    public void setSpMy(SharedPreferences spMy) {
        this.spMy = spMy;
        editor2=spMy.edit();
    }

    public void setSpDaySong(SharedPreferences spDaySong) {
        this.spDaySong = spDaySong;
        editor3=spDaySong.edit();
    }
}
