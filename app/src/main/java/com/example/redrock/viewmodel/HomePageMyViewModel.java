package com.example.redrock.viewmodel;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.redrock.activity.HomePageActivity;
import com.example.redrock.base.APP;

import com.example.redrock.bean.CreatePlaylist;
import com.example.redrock.bean.CreatePlaylistBean;
import com.example.redrock.model.InternetTool;

import com.example.redrock.room.HomePageDataBase;
import com.example.redrock.room.LoginBeanDao;
import com.example.redrock.room.PersonalPlaylistDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class HomePageMyViewModel extends ViewModel {

    private MutableLiveData<String> _headPortrait=new MutableLiveData<>();
    private MutableLiveData<String> _userName=new MutableLiveData<>();

    private MutableLiveData<List<CreatePlaylist>> _userData=new MutableLiveData<>();

    public final LiveData<String> headPortrait=_headPortrait;
    public final LiveData<String> userName=_userName;

    public final LiveData<List<CreatePlaylist>> userData=_userData;

    private HomePageViewModel homePageViewModel;

    private String cookie;
    private String id;


    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    private HomePageDataBase dataBase= Room.databaseBuilder(APP.getContext(),HomePageDataBase.class,"HomeFoundWonderful").build();
    private PersonalPlaylistDao personalPlaylistDao= dataBase.getPersonalPlaylistDao();
    private LoginBeanDao loginBeanDao=dataBase.loginBeanDao();



    public void setHomePageViewModel(HomePageActivity activity) {
        this.homePageViewModel = ViewModelProviders.of(activity).get(HomePageViewModel.class);
    }

    private InternetTool tool;

    public void getUserInformationData(){

        if(homePageViewModel.getUserData().size()>0) {

            String photo = homePageViewModel.getUserData().get(0);
            String name = homePageViewModel.getUserData().get(1);
            new Thread(new Runnable() {
                @Override
                public void run() {

                    _headPortrait.postValue(photo);
                    _userName.postValue(name);
                }
            }).start();
        }
    }

    public void getPersonalPlaylist(){


        new Thread(new Runnable() {
            @Override
            public void run() {

                if(loginBeanDao.load().size()>0) {
                    cookie = loginBeanDao.load().get(0).getCookie();
                    id = loginBeanDao.load().get(0).getAccount().getId();
                }
        boolean isEmpty=true;


                for (int i = 0; i < personalPlaylistDao.load().size(); i++) {
                    if(personalPlaylistDao.load().get(i).getPlaylist()!=null){
                        isEmpty=false;
                        break;
                    }
                }

        if(personalPlaylistDao.load().size()==0||isEmpty) {
            getPersonalPlaylistFromIn();

        }else {
            getData(null,1,false);
        }
            }
        }).start();
    }


    private void getData(String data,int code,boolean isEmpty) {
        switch (code){

            case 1:
            {
                if(isEmpty) {
                    Gson gson = new Gson();
                    CreatePlaylistBean bean = gson.fromJson(data, new TypeToken<CreatePlaylistBean>() {
                    }.getType());

                    if(sp.getString("cplb","")==null||sp.getString("cplb","").length()==0) {
                        personalPlaylistDao.addData(bean);
                        editor.putString("cplb","NOT_EMPTY");
                        editor.apply();
                    }else {
                            personalPlaylistDao.update(bean);
                        }
                  }

                CreatePlaylistBean createPlaylistBean=new CreatePlaylistBean();
                List<CreatePlaylistBean> list=personalPlaylistDao.load();
                for (int i = 0; i<list.size(); i++) {
                    if(list.get(i).getPlaylist()!=null){
                        createPlaylistBean=list.get(i);
                        break;
                    }
                }

            List<CreatePlaylist> l=new ArrayList<>();
                if(createPlaylistBean.getPlaylist()!=null)
                for (int i = 0; i <createPlaylistBean.getPlaylist().size() ; i++) {
                    l.add(new CreatePlaylist(createPlaylistBean.getPlaylist().get(i).getName(),createPlaylistBean.getPlaylist().get(i).getCoverImgUrl(),createPlaylistBean.getPlaylist().get(i).getId()));
                }

                _userData.postValue(l);

            }break;
        }
    }

    public void getPersonalPlaylistFromIn(){
        tool = new InternetTool();
        tool.setBaseUrl("http://redrock.udday.cn:2022")
                .setRequestType(InternetTool.GET)
                .setPortPath("/user/playlist")
                .setHeader("Cookie",cookie)
                .setRequestData("uid", id)
                .setRequestData("timestamp",String.valueOf(System.currentTimeMillis()))
                .startRequest(new InternetTool.Back() {
                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onFinish(String data) {
                        getData(data, 1, true);
                    }
                });
    }


    public void setSp(SharedPreferences sp) {
        this.sp = sp;
        editor=sp.edit();
    }


}