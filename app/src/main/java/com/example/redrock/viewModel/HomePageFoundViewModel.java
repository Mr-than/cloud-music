package com.example.redrock.viewModel;

import android.content.ServiceConnection;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.redrock.base.APP;
import com.example.redrock.bean.DayRecommendBean;
import com.example.redrock.bean.DayRecommendPlaylistsBean;
import com.example.redrock.bean.DayRecommendSongsBean;
import com.example.redrock.bean.WonderfulRecommendPlaylistBean;
import com.example.redrock.model.InternetTool;
import com.example.redrock.room.DayRecommendPlaylistsDao;
import com.example.redrock.room.DayRecommendSongsDao;
import com.example.redrock.room.HomePageDataBase;
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

    private HomePageDataBase dataBase= Room.databaseBuilder(APP.getContext(),HomePageDataBase.class,"HomeFoundWonderful").build();
    private WonderfulPlaylistDao HomeFoundWonderful=dataBase.wonderfulPlaylistDao();
    private DayRecommendPlaylistsDao dayRecommendPlaylistsDao=dataBase.dayRecommendPlaylistsDao();
    private DayRecommendSongsDao dayRecommendSongsDao= dataBase.dayRecommendSongsDao();

    public void getRecommendPlaylists(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isEmpty=true;


                for (int i = 0; i < dayRecommendPlaylistsDao.load().size(); i++) {
                    if(dayRecommendPlaylistsDao.load().get(i).getRecommend().size()!=0){
                        isEmpty=false;
                        break;
                    }
                }

                if (dayRecommendPlaylistsDao.load().size() == 0||isEmpty) {

                    tool = new InternetTool();
                    tool.setBaseUrl("http://redrock.udday.cn:2022")
                            .setRequestType(InternetTool.GET)
                            .setPortPath("/recommend/resource")
                            .setHeader("Cookie", "MUSIC_U=0b0eba60606860f12eb70629773aa72d544c5b92148e3ad156ef6839eb35c2abc84e8a4f4ba4f13ed78b6050a17a35e705925a4e6992f61d07c385928f88e8de; Max-Age=1296000; Expires=Sun, 20 Feb 2022 10:02:13 GMT; Path=/;;MUSIC_SNS=; Max-Age=0; Expires=Sat, 5 Feb 2022 10:02:13 GMT; Path=/;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/clientlog;;__csrf=d397cd57c3d1e6dd570fae3d17103a70; Max-Age=1296010; Expires=Sun, 20 Feb 2022 10:02:23 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/openapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/openapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/feedback;;__remember_me=true; Max-Age=1296000; Expires=Sun, 20 Feb 2022 10:02:13 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/feedback;")
                            .startRequest(new InternetTool.Back() {
                                @Override
                                public void onFinish(String data) {
                                    getData(data, 0, true);
                                }
                            });
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
        tool=new InternetTool();
        tool.setBaseUrl("http://redrock.udday.cn:2022")
                .setRequestType(InternetTool.GET)
                .setPortPath("/top/playlist/highquality")
                .startRequest(new InternetTool.Back() {
                    @Override
                    public void onFinish(String data) {
                        getData(data,1,isEmpty);
                    }
                });

        }else{
            isEmpty=false;
            getData(null,1,isEmpty);
                }
        }
        }).start();
    }

    public void getDayRecommendSongData(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                boolean isEmpty=true;
                for (int i = 0; i < dayRecommendSongsDao.load().size(); i++) {

                    if(dayRecommendSongsDao.load().get(i).getData()!=null){
                        isEmpty=false;
                        break;
                    }
                }

                if(dayRecommendSongsDao.load().size()==0||isEmpty) {

                    tool = new InternetTool();
                    tool.setBaseUrl("http://redrock.udday.cn:2022")
                            .setRequestType(InternetTool.GET)
                            .setPortPath("/recommend/songs")
                            .setHeader("Cookie","MUSIC_U=0b0eba60606860f12eb70629773aa72d544c5b92148e3ad156ef6839eb35c2abc84e8a4f4ba4f13ed78b6050a17a35e705925a4e6992f61d07c385928f88e8de; Max-Age=1296000; Expires=Sun, 20 Feb 2022 10:02:13 GMT; Path=/;;MUSIC_SNS=; Max-Age=0; Expires=Sat, 5 Feb 2022 10:02:13 GMT; Path=/;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/clientlog;;__csrf=d397cd57c3d1e6dd570fae3d17103a70; Max-Age=1296010; Expires=Sun, 20 Feb 2022 10:02:23 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/openapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/openapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/feedback;;__remember_me=true; Max-Age=1296000; Expires=Sun, 20 Feb 2022 10:02:13 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/feedback;MUSIC_U=0b0eba60606860f12eb70629773aa72d544c5b92148e3ad156ef6839eb35c2abc84e8a4f4ba4f13ed78b6050a17a35e705925a4e6992f61d07c385928f88e8de; Max-Age=1296000; Expires=Sun, 20 Feb 2022 10:02:13 GMT; Path=/;;MUSIC_SNS=; Max-Age=0; Expires=Sat, 5 Feb 2022 10:02:13 GMT; Path=/;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/clientlog;;__csrf=d397cd57c3d1e6dd570fae3d17103a70; Max-Age=1296010; Expires=Sun, 20 Feb 2022 10:02:23 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/openapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/openapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/feedback;;__remember_me=true; Max-Age=1296000; Expires=Sun, 20 Feb 2022 10:02:13 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/feedback;")
                            .startRequest(new InternetTool.Back() {
                                @Override
                                public void onFinish(String data) {
                                    getData(data, 2, true);
                                }
                            });
                }else {
                    getData(null,2,false);
                }

            }
        }).start();
    }



    public void getData(String data,int code,boolean isEmpty){

        switch (code){
            case 0:{

                if(isEmpty){
                    Gson gson=new Gson();
                    DayRecommendPlaylistsBean bean=gson.fromJson(data,new TypeToken<DayRecommendPlaylistsBean>(){}.getType());

                    dayRecommendPlaylistsDao.addData(bean);
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

                    StringBuilder nameTempData=new StringBuilder();
                    nameTempData.append(dayRecommendPlaylistsBean.getRecommend().get(i).getName());

                    if(nameTempData.length()>10){
                        nameTempData.insert(10,"\n");
                    }if(nameTempData.length()>19){
                        nameTempData.insert(19,"\n");
                    }

                recommendPlaylist.add(new DayRecommendBean(dayRecommendPlaylistsBean.getRecommend().get(i).getPicUrl(),nameTempData.toString(),dayRecommendPlaylistsBean.getRecommend().get(i).getId()));

                }

                _recommendPlaylist.postValue(recommendPlaylist);


            }break;
            case 1:{
                if(isEmpty) {
                    Gson gson = new Gson();
                    WonderfulRecommendPlaylistBean bean = gson.fromJson(data, new TypeToken<WonderfulRecommendPlaylistBean>() {
                    }.getType());

                    HomeFoundWonderful.addData(bean);
                    HomeFoundWonderful.update(bean);
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
                if(isEmpty){
                    Gson gson=new Gson();
                    DayRecommendSongsBean bean=gson.fromJson(data,new TypeToken<DayRecommendSongsBean>(){}.getType());
                    dayRecommendSongsDao.addData(bean);
                }

                List<String> name=new ArrayList<>();
                List<String> photo=new ArrayList<>();
                List<String> au=new ArrayList<>();
                List<String> id=new ArrayList<>();

                DayRecommendSongsBean dayRecommendSongsBean=new DayRecommendSongsBean();
                List<DayRecommendSongsBean> list=dayRecommendSongsDao.load();

                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getData()!=null){
                        dayRecommendSongsBean=list.get(i);
                        break;
                    }
                }

                for (int i = 0; i < dayRecommendSongsBean.getData().getDailySongs().size(); i++) {
                        name.add(dayRecommendSongsBean.getData().getDailySongs().get(i).getName());
                        photo.add(dayRecommendSongsBean.getData().getDailySongs().get(i).getAl().getPicUrl());
                        au.add(dayRecommendSongsBean.getData().getDailySongs().get(i).getAr().get(0).getName());
                        id.add(dayRecommendSongsBean.getData().getDailySongs().get(i).getId());
                }

                _recommendSongName.postValue(name);
                _recommendSongPhoto.postValue(photo);
                _recommendSongAu.postValue(au);
                _recommendSongId.postValue(id);

            }break;
        }
    }

    public void setServiceConnection(ServiceConnection s){
        _connection.postValue(s);
    }

}