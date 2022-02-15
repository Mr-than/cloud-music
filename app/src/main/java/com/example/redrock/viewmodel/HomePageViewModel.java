package com.example.redrock.viewmodel;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.redrock.R;
import com.example.redrock.base.APP;

import com.example.redrock.bean.LoginBean;
import com.example.redrock.model.InternetTool;

import com.example.redrock.room.HomePageDataBase;
import com.example.redrock.room.LoginBeanDao;
import com.example.redrock.service.PlayMusicService;

import java.util.ArrayList;
import java.util.List;

public class HomePageViewModel extends ViewModel {

    private MutableLiveData<String> _songPhoto=new MutableLiveData<>();
    private MutableLiveData<String> _songName=new MutableLiveData<>();
    private MutableLiveData<String> _songAu=new MutableLiveData<>();
    private MutableLiveData<String> _songId=new MutableLiveData<>();

    private MutableLiveData<PlayMusicService.PlaySongBinder> _serviceConnect=new MutableLiveData<>();


    private MutableLiveData<String> _headPortrait=new MutableLiveData<>();
    private MutableLiveData<String> _userName=new MutableLiveData<>();

    private MutableLiveData<Integer> _pausePlay=new MutableLiveData<>();

    public final LiveData<String> headPortrait=_headPortrait;
    public final LiveData<String> userName=_userName;

    public final LiveData<String> songPhoto=_songPhoto;
    public final LiveData<String> songName=_songName;
    public final LiveData<String> songId=_songId;


    private MutableLiveData<LoginBeanDao> _dao=new MutableLiveData<>();
    public LiveData<LoginBeanDao> dao=_dao;


    public final LiveData<PlayMusicService.PlaySongBinder> serviceConnect=_serviceConnect;


    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private MutableLiveData<String> _isLogin=new MutableLiveData<>();
    public LiveData<String> isLogin=_isLogin;


    public final LiveData<String> songAu=_songAu;

    public final LiveData<Integer> pausePlay=_pausePlay;

    private boolean isPlay=false;

    private HomePageDataBase dataBase= Room.databaseBuilder(APP.getContext(),HomePageDataBase.class,"HomeFoundWonderful").build();
    private LoginBeanDao myDao=dataBase.loginBeanDao();
    private InternetTool tool=new InternetTool();

    private HomePageMyViewModel homePageMyViewModel;

    public void setHomePageMyViewModel(HomePageMyViewModel homePageMyViewModel) {
        this.homePageMyViewModel = homePageMyViewModel;
    }

    private List<String> userData=new ArrayList<>();




    public void setSongData(List<String> data){
        _songPhoto.postValue(data.get(0));
        _songName.postValue(data.get(1));
        _songAu.postValue(" — "+data.get(2));
        _songId.postValue(data.get(3));
    }

    public void setPauseOrPlay(){

        if(!isPlay){
            _pausePlay.postValue(R.drawable.start);
            isPlay=true;
        }else {
            _pausePlay.postValue(R.drawable.pause);
            isPlay=false;
        }

    }

    public void setPlay(){
        _pausePlay.postValue(R.drawable.start);
        isPlay=true;
    }

    public void setPause(){
        _pausePlay.postValue(R.drawable.pause);
        isPlay=false;
    }




    public void getData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<LoginBean> list=myDao.load();

                LoginBean loginBean=new LoginBean();

                for (int i = 0; list.size()>0&&i<list.size(); i++) {
                    if(list.get(i).getAccount()!=null){
                        loginBean=list.get(i);
                        break;
                    }
                }

                if(loginBean.getProfile()!=null) {

                    _headPortrait.postValue(loginBean.getProfile().getAvatarUrl());
                    _userName.postValue(loginBean.getProfile().getNickname());
                    userData.add(loginBean.getProfile().getAvatarUrl());
                    userData.add(loginBean.getProfile().getNickname());
                }
            }
        }).start();


    }




    public List<String> getUserData(){
        return this.userData;
    }


    public void setServiceBinder(PlayMusicService.PlaySongBinder mBinder){
        _serviceConnect.postValue(mBinder);
    }

    public void setLoginDao(LoginBeanDao dao){
        _dao.postValue(dao);
    }


    public void setIsLogin(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                    if(myDao.getCookie()!=null) {
                        _isLogin.postValue("退出登录");
                        return;
                    }
                    _isLogin.postValue("登录");
            }
        }).start();

    }


}