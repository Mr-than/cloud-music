package com.example.redrock.viewModel;

import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.redrock.base.APP;
import com.example.redrock.bean.DayRecommendBean;
import com.example.redrock.bean.DayRecommendPlaylistsBean;
import com.example.redrock.bean.DayRecommendSongsBean;
import com.example.redrock.bean.LoginBean;
import com.example.redrock.bean.WonderfulRecommendPlaylistBean;
import com.example.redrock.model.InternetTool;
import com.example.redrock.room.DayRecommendPlaylistsDao;
import com.example.redrock.room.DayRecommendSongsDao;
import com.example.redrock.room.HomePageDataBase;
import com.example.redrock.room.LoginBeanDao;
import com.example.redrock.room.WonderfulPlaylistDao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePageFoundViewModel extends ViewModel {

    private MutableLiveData<List<DayRecommendBean>> _recommendPlaylist=new MutableLiveData<>();

    private MutableLiveData<List<String>> _wonderfulPlaylistPhoto=new MutableLiveData<>();
    private MutableLiveData<List<String>> _wonderfulPlaylistName=new MutableLiveData<>();
    private MutableLiveData<List<String>> _wonderfulPlaylistId=new MutableLiveData<>();

    private MutableLiveData<List<String>> _recommendSongPhoto=new MutableLiveData<>();
    private MutableLiveData<List<String>> _recommendSongName=new MutableLiveData<>();
    private MutableLiveData<List<String>> _recommendSongAu=new MutableLiveData<>();
    private MutableLiveData<List<String>> _recommendSongId=new MutableLiveData<>();
    private MutableLiveData<ServiceConnection> _connection=new MutableLiveData<>();


    public final LiveData<List<DayRecommendBean>> recommendPlaylist=_recommendPlaylist;

    public final LiveData<List<String>> wonderfulPlaylistPhoto=_wonderfulPlaylistPhoto;
    public final LiveData<List<String>> wonderfulPlaylistName=_wonderfulPlaylistName;
    public final LiveData<List<String>> wonderfulPlaylistId=_wonderfulPlaylistId;

    public final LiveData<List<String>> recommendSongPhoto=_recommendSongPhoto;
    public final LiveData<List<String>> recommendSongName=_recommendSongName;
    public final LiveData<List<String>> recommendSongAu=_recommendSongAu;
    public final LiveData<List<String>> recommendSongId=_recommendSongId;

    public final LiveData<ServiceConnection> connection=_connection;

    private InternetTool tool;

    private String cookie;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;



    private HomePageDataBase dataBase= Room.databaseBuilder(APP.getContext(),HomePageDataBase.class,"HomeFoundWonderful").build();
    private WonderfulPlaylistDao HomeFoundWonderful=dataBase.wonderfulPlaylistDao();
    private LoginBeanDao loginBeanDao= dataBase.loginBeanDao();
    private DayRecommendPlaylistsDao dayRecommendPlaylistsDao=dataBase.dayRecommendPlaylistsDao();


    public void getRecommendPlaylists(){

        new Thread(new Runnable() {
            @Override
            public void run() {


                boolean isEmpty=true;

                cookie=loginBeanDao.getCookie();


                for (int i = 0; i < dayRecommendPlaylistsDao.load().size(); i++) {
                    if(dayRecommendPlaylistsDao.load().get(i).getRecommend()!=null) {
                        if (dayRecommendPlaylistsDao.load().get(i).getRecommend().size() != 0) {
                            isEmpty = false;
                            break;
                        }
                    }
                }

                if (dayRecommendPlaylistsDao.load().size() == 0||isEmpty) {


                    getRecommendPlaylistFromIn();

                }else {
                    getData(null,0,false);
                }

            }
        }).start();
    }

    public void getWonderfulPlaylist(){


        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isEmpty;
        if(HomeFoundWonderful.load().size()==0) {
            isEmpty=true;

            getWonderfulPlaylistFromIn();

        }else{
            isEmpty=false;
            getData(null,1,isEmpty);
                }
        }
        }).start();
    }

    public void getDayRecommendSongData(){

                getRecommendSongFromIn();

    }



    public void getData(String data,int code,boolean isEmpty){

        switch (code){
            case 0:{

                if(isEmpty){
                    Gson gson=new Gson();
                    DayRecommendPlaylistsBean bean=gson.fromJson(data,new TypeToken<DayRecommendPlaylistsBean>(){}.getType());

                    if(sp.getString("drpl","")==null||sp.getString("drpl","").length()==0) {
                        dayRecommendPlaylistsDao.addData(bean);
                        editor.putString("drpl","NOT_EMPTY");
                        editor.commit();
                    }else {
                            dayRecommendPlaylistsDao.update(bean);
                            }

                }


                DayRecommendPlaylistsBean dayRecommendPlaylistsBean=new DayRecommendPlaylistsBean();
                List<DayRecommendPlaylistsBean> list=dayRecommendPlaylistsDao.load();

                for (int i = 0; list.size()>0&&i<list.size(); i++) {
                    if(list.get(i).getRecommend()!=null){
                        dayRecommendPlaylistsBean=list.get(i);
                        break;
                    }
                }


                List<DayRecommendBean> recommendPlaylist=new ArrayList<>();


                for (int i = 0; i < 5; i++) {

                    if (dayRecommendPlaylistsBean.getRecommend() != null) {
                        StringBuilder nameTempData = new StringBuilder();
                        nameTempData.append(dayRecommendPlaylistsBean.getRecommend().get(i).getName());

                        if (nameTempData.length() > 10) {
                            nameTempData.insert(10, "\n");
                        }
                        if (nameTempData.length() > 19) {
                            nameTempData.insert(19, "\n");
                        }

                        recommendPlaylist.add(new DayRecommendBean(dayRecommendPlaylistsBean.getRecommend().get(i).getPicUrl(), nameTempData.toString(), dayRecommendPlaylistsBean.getRecommend().get(i).getId()));

                    }

                    _recommendPlaylist.postValue(recommendPlaylist);
                }

            }break;
            case 1:{
                if(isEmpty) {
                    Gson gson = new Gson();
                    WonderfulRecommendPlaylistBean bean = gson.fromJson(data, new TypeToken<WonderfulRecommendPlaylistBean>() {
                    }.getType());
                if(sp.getString("wrpl","")==null||sp.getString("wrpl","").length()==0) {
                    HomeFoundWonderful.addData(bean);
                    editor.putString("wrpl","NOT_EMPTY");
                    editor.commit();
                }else {

                        HomeFoundWonderful.update(bean);


                }
                }

                List<String> name=new ArrayList<>();
                List<String> photo=new ArrayList<>();
                List<String> id=new ArrayList<>();
                WonderfulRecommendPlaylistBean wonderfulRecommendPlaylistBean=new WonderfulRecommendPlaylistBean();
                List<WonderfulRecommendPlaylistBean> w=HomeFoundWonderful.load();

                for (int i = 0; w.size() > 0; i++) {
                            if(w.get(i).getPlaylists()!=null){
                                wonderfulRecommendPlaylistBean=w.get(i);
                                break;
                            }
                        }

                for (int i = 0; i < 50; i++) {
                    StringBuilder nameTempData=new StringBuilder();
                    nameTempData.append(wonderfulRecommendPlaylistBean.getPlaylists().get(i).getName());

                    String photoUrl=wonderfulRecommendPlaylistBean.getPlaylists().get(i).getCoverImgUrl();

                    String playlistId=wonderfulRecommendPlaylistBean.getPlaylists().get(i).getId();

                    if(nameTempData.length()>10){
                        nameTempData.insert(10,"\n");
                    }if(nameTempData.length()>19){
                        nameTempData.insert(19,"\n");
                    }

                  photo.add(photoUrl);
                  name.add(nameTempData.toString());
                  id.add(playlistId);
                }

                 _wonderfulPlaylistPhoto.postValue(photo);
                 _wonderfulPlaylistName.postValue(name);
                 _wonderfulPlaylistId.postValue(id);


            }break;

            case 2:{

                    Gson gson=new Gson();
                    DayRecommendSongsBean bean=gson.fromJson(data,new TypeToken<DayRecommendSongsBean>(){}.getType());



                List<String> name=new ArrayList<>();
                List<String> photo=new ArrayList<>();
                List<String> au=new ArrayList<>();
                List<String> id=new ArrayList<>();

                DayRecommendSongsBean dayRecommendSongsBean=bean;


                if(dayRecommendSongsBean.getData()!=null) {

                    for (int i = 0; i < 3; i++) {
                        name.add(dayRecommendSongsBean.getData().getDailySongs().get(i).getName());
                        photo.add(dayRecommendSongsBean.getData().getDailySongs().get(i).getAl().getPicUrl());
                        au.add(dayRecommendSongsBean.getData().getDailySongs().get(i).getAr().get(0).getName());
                        id.add(dayRecommendSongsBean.getData().getDailySongs().get(i).getId());
                    }
                }

                _recommendSongName.postValue(name);
                _recommendSongPhoto.postValue(photo);
                _recommendSongAu.postValue(au);
                _recommendSongId.postValue(id);

            }break;
        }
    }


    public void getRecommendPlaylistFromIn(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                cookie=loginBeanDao.getCookie();
                tool = new InternetTool();
                tool.setBaseUrl("http://redrock.udday.cn:2022")
                        .setRequestType(InternetTool.GET)
                        .setPortPath("/recommend/resource")
                        .setHeader("Cookie", cookie)
                        .setRequestData("timestamp",String.valueOf(System.currentTimeMillis()))
                        .startRequest(new InternetTool.Back() {
                            @Override
                            public void onError() {

                            }

                            @Override
                            public void onFinish(String data) {
                                getData(data, 0, true);
                            }
                        });
            }
        }).start();

    }

    public void getWonderfulPlaylistFromIn(){
        tool=new InternetTool();
        tool.setBaseUrl("http://redrock.udday.cn:2022")
                .setRequestType(InternetTool.GET)
                .setPortPath("/top/playlist/highquality")
                .startRequest(new InternetTool.Back() {
                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onFinish(String data) {
                        getData(data,1,true);
                    }
                });
    }

    public void getRecommendSongFromIn(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                cookie=loginBeanDao.getCookie();
                List<LoginBean> list=loginBeanDao.load();
                tool = new InternetTool();
                tool.setBaseUrl("http://redrock.udday.cn:2022")
                        .setRequestType(InternetTool.GET)
                        .setPortPath("/recommend/songs")
                        .setHeader("Cookie",cookie)
                        .setRequestData("timestamp",String.valueOf(System.currentTimeMillis()))
                        .startRequest(new InternetTool.Back() {
                            @Override
                            public void onError() {

                            }

                            @Override
                            public void onFinish(String data) {
                                getData(data, 2, true);
                            }
                        });
            }
        }).start();


    }


    public void setSp(SharedPreferences sp) {
        this.sp = sp;
        editor=sp.edit();
    }

    public void setLoginBeanDao(LoginBeanDao loginBeanDao) {
        this.loginBeanDao = loginBeanDao;
    }
}