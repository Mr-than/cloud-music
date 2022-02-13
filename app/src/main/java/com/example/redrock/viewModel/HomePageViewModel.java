package com.example.redrock.viewModel;

import android.content.ServiceConnection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.redrock.R;
import com.example.redrock.base.APP;
import com.example.redrock.bean.AccountInformationBean;
import com.example.redrock.model.InternetTool;
import com.example.redrock.room.AccountInformationDao;
import com.example.redrock.room.DayRecommendSongsDao;
import com.example.redrock.room.HomePageDataBase;
import com.example.redrock.service.PlayMusicService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

    public final LiveData<PlayMusicService.PlaySongBinder> serviceConnect=_serviceConnect;


    public final LiveData<String> songAu=_songAu;

    public final LiveData<Integer> pausePlay=_pausePlay;

    private boolean isPlay=false;

    private HomePageDataBase dataBase= Room.databaseBuilder(APP.getContext(),HomePageDataBase.class,"HomeFoundWonderful").build();
    private AccountInformationDao myDao= dataBase.getAccountInformationDao();
    private InternetTool tool=new InternetTool();

    private HomePageMyViewModel homePageMyViewModel;

    public void setHomePageMyViewModel(HomePageMyViewModel homePageMyViewModel) {
        this.homePageMyViewModel = homePageMyViewModel;
    }

    private List<String> userData=new ArrayList<>();

    public void getUserInformationData(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                boolean isEmpty;

                if(myDao.load().size()==0) {
                    isEmpty=true;
                    tool = new InternetTool();
                    tool.setBaseUrl("http://redrock.udday.cn:2022")
                            .setRequestType(InternetTool.GET)
                            .setPortPath("/user/account")
                            .setHeader("Cookie", "MUSIC_U=0b0eba60606860f12eb70629773aa72d544c5b92148e3ad156ef6839eb35c2abc84e8a4f4ba4f13ed78b6050a17a35e705925a4e6992f61d07c385928f88e8de; Max-Age=1296000; Expires=Sun, 20 Feb 2022 10:02:13 GMT; Path=/;;MUSIC_SNS=; Max-Age=0; Expires=Sat, 5 Feb 2022 10:02:13 GMT; Path=/;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/clientlog;;__csrf=d397cd57c3d1e6dd570fae3d17103a70; Max-Age=1296010; Expires=Sun, 20 Feb 2022 10:02:23 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/openapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/openapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/feedback;;__remember_me=true; Max-Age=1296000; Expires=Sun, 20 Feb 2022 10:02:13 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/feedback;")
                            .startRequest(new InternetTool.Back() {
                                @Override
                                public void onFinish(String data) {
                                    getData(data, 0, isEmpty);
                                }
                            });
                }else{
                    isEmpty=false;
                    getData(null,0,isEmpty);
                }
            }
        }).start();

    }


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


    public void getData(String data,int code,boolean isEmpty){
        if(isEmpty) {
            Gson gson = new Gson();
            AccountInformationBean bean = gson.fromJson(data, new TypeToken<AccountInformationBean>() {
            }.getType());

            myDao.addData(bean);
            myDao.update(bean);
        }

        AccountInformationBean accountInformationBean=new AccountInformationBean();
        List<AccountInformationBean> list=myDao.load();

        for (int i = 0; list.size()>0&&i<list.size(); i++) {
            if(list.get(i).getAccount()!=null){
                accountInformationBean=list.get(i);
                break;
            }
        }

        _headPortrait.postValue(accountInformationBean.getProfile().getAvatarUrl());
        _userName.postValue(accountInformationBean.getProfile().getNickname());


        this.userData.add(accountInformationBean.getProfile().getAvatarUrl());
        this.userData.add(accountInformationBean.getProfile().getNickname());
    }

    public List<String> getUserData(){
        return this.userData;
    }

    public void setServiceBinder(PlayMusicService.PlaySongBinder mBinder){
        _serviceConnect.postValue(mBinder);
    }


}