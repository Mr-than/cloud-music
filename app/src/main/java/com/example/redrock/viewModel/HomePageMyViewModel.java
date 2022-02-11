package com.example.redrock.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.redrock.activity.HomePageActivity;
import com.example.redrock.base.APP;
import com.example.redrock.bean.AccountInformationBean;
import com.example.redrock.bean.CreatePlaylist;
import com.example.redrock.bean.CreatePlaylistBean;
import com.example.redrock.model.InternetTool;
import com.example.redrock.room.AccountInformationDao;
import com.example.redrock.room.DayRecommendSongsDao;
import com.example.redrock.room.HomePageDataBase;
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


    private HomePageDataBase dataBase= Room.databaseBuilder(APP.getContext(),HomePageDataBase.class,"HomeFoundWonderful").build();
    private PersonalPlaylistDao personalPlaylistDao= dataBase.getPersonalPlaylistDao();
    private AccountInformationDao accountDao= dataBase.getAccountInformationDao();
    private DayRecommendSongsDao dayRecommendSongsDao= dataBase.dayRecommendSongsDao();


    public void setHomePageViewModel(HomePageActivity activity) {
        this.homePageViewModel = ViewModelProviders.of(activity).get(HomePageViewModel.class);
    }

    private InternetTool tool;

    public void getUserInformationData(){

        String photo=homePageViewModel.getUserData().get(0);
        String name=homePageViewModel.getUserData().get(1);
        new Thread(new Runnable() {
            @Override
            public void run() {

                _headPortrait.postValue(photo);
                _userName.postValue(name);
            }
        }).start();

    }

    public void getPersonalPlaylist(){


        new Thread(new Runnable() {
            @Override
            public void run() {

        boolean isEmpty=true;


                for (int i = 0; i < personalPlaylistDao.load().size(); i++) {
                    if(personalPlaylistDao.load().get(i).getPlaylist().size()!=0){
                        isEmpty=false;
                        break;
                    }
                }

        if(personalPlaylistDao.load().size()==0||isEmpty) {

            tool = new InternetTool();
            tool.setBaseUrl("http://redrock.udday.cn:2022")
                    .setRequestType(InternetTool.GET)
                    .setPortPath("/user/playlist")
                    .setHeader("Cookie","MUSIC_U=0b0eba60606860f12eb70629773aa72d544c5b92148e3ad156ef6839eb35c2abc84e8a4f4ba4f13ed78b6050a17a35e705925a4e6992f61d07c385928f88e8de; Max-Age=1296000; Expires=Sun, 20 Feb 2022 10:02:13 GMT; Path=/;;MUSIC_SNS=; Max-Age=0; Expires=Sat, 5 Feb 2022 10:02:13 GMT; Path=/;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/clientlog;;__csrf=d397cd57c3d1e6dd570fae3d17103a70; Max-Age=1296010; Expires=Sun, 20 Feb 2022 10:02:23 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/openapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/openapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/feedback;;__remember_me=true; Max-Age=1296000; Expires=Sun, 20 Feb 2022 10:02:13 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/feedback;MUSIC_U=0b0eba60606860f12eb70629773aa72d544c5b92148e3ad156ef6839eb35c2abc84e8a4f4ba4f13ed78b6050a17a35e705925a4e6992f61d07c385928f88e8de; Max-Age=1296000; Expires=Sun, 20 Feb 2022 10:02:13 GMT; Path=/;;MUSIC_SNS=; Max-Age=0; Expires=Sat, 5 Feb 2022 10:02:13 GMT; Path=/;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/clientlog;;__csrf=d397cd57c3d1e6dd570fae3d17103a70; Max-Age=1296010; Expires=Sun, 20 Feb 2022 10:02:23 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/openapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/openapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/feedback;;__remember_me=true; Max-Age=1296000; Expires=Sun, 20 Feb 2022 10:02:13 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/api/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/eapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/wapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Thu, 23 Feb 2090 13:16:20 GMT; Path=/neapi/feedback;")
                    .setRequestData("uid", "1721674871")
                    .startRequest(new InternetTool.Back() {
                        @Override
                        public void onFinish(String data) {
                            getData(data, 1, true);
                        }
                    });
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

                    personalPlaylistDao.addData(bean);
                    personalPlaylistDao.update(bean);
                }

                CreatePlaylistBean createPlaylistBean=new CreatePlaylistBean();
                List<CreatePlaylistBean> list=personalPlaylistDao.load();
                for (int i = 0; i<list.size(); i++) {
                    if(list.get(i).getPlaylist().size()>0){
                        createPlaylistBean=list.get(i);
                        break;
                    }
                }

            List<CreatePlaylist> l=new ArrayList<>();
                for (int i = 0; i <createPlaylistBean.getPlaylist().size() ; i++) {
                    l.add(new CreatePlaylist(createPlaylistBean.getPlaylist().get(i).getName(),createPlaylistBean.getPlaylist().get(i).getCoverImgUrl(),createPlaylistBean.getPlaylist().get(i).getId()));
                }

                _userData.postValue(l);

            }break;
        }
    }
}