package com.example.redrock.viewmodel;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import com.example.redrock.base.APP;
import com.example.redrock.bean.DayRecommendSongsBean;
import com.example.redrock.bean.PlaylistSongs;
import com.example.redrock.bean.PlaylistSongsBean;
import com.example.redrock.bean.SearchBean;
import com.example.redrock.fragment.PageFound;
import com.example.redrock.model.InternetTool;
import com.example.redrock.room.DayRecommendSongsDao;
import com.example.redrock.room.HomePageDataBase;

import com.example.redrock.room.LoginBeanDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/**
 *   description:歌单详细页面的ViewModel
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/5
 */

public class PlaylistSongViewModel extends ViewModel {

    private MutableLiveData<List<PlaylistSongs>> _playlistSong=new MutableLiveData<>();

    public LiveData<List<PlaylistSongs>> playlistSong=_playlistSong;

    private HomePageDataBase dataBase= Room.databaseBuilder(APP.getContext(),HomePageDataBase.class,"HomeFoundWonderful").build();

    private DayRecommendSongsDao dayRecommendSongsDao=dataBase.dayRecommendSongsDao();

    private LoginBeanDao loginBeanDao= dataBase.loginBeanDao();

    private String cookie;

    private InternetTool tool;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private MutableLiveData<List<PlaylistSongs>> _searchSong=new MutableLiveData<>();
    public LiveData<List<PlaylistSongs>> searchSong=_searchSong;

    private MutableLiveData<String> _msg=new MutableLiveData<>();
    public LiveData<String> msg=_msg;






    public void getPlaylistSong(String id){

            cookie= loginBeanDao.getCookie();



            if(id.equals(PageFound.DAY_RECOMMEND_SONGS)){

                boolean isEmpty=true;
                for (int i = 0; i < dayRecommendSongsDao.load().size(); i++) {

                    if(dayRecommendSongsDao.load().get(i).getData()!=null){
                        isEmpty=false;
                        break;
                    }
                }

                if(dayRecommendSongsDao.load().size()==0||isEmpty) {
                    getDayRecommendFromIn();
                }else {
                    getDayRecommendData(null,false);
                }


            }else {
                cookie=loginBeanDao.getCookie();
                tool = new InternetTool();
                tool.setBaseUrl("http://redrock.udday.cn:2022")
                        .setRequestType(InternetTool.GET)
                        .setHeader("Cookie",cookie)
                        .setPortPath("/playlist/detail")
                        .setRequestData("id", id)
                        .setRequestData("timestamp",String.valueOf(System.currentTimeMillis()))
                        .startRequest(new InternetTool.Back() {
                            @Override
                            public void onError() {
                                _msg.postValue("未登录或歌单歌曲为零");
                            }

                            @Override
                            public void onFinish(String data) {
                                getData(data);
                            }
                        });
            }
        }

    private void getData(String data){

            Gson gson = new Gson();
            PlaylistSongsBean playlistSongsBean = gson.fromJson(data, new TypeToken<PlaylistSongsBean>() {
            }.getType());

            List<PlaylistSongs> list=new ArrayList<>();

            for (int i = 0; i < playlistSongsBean.getPlaylist().getTracks().size(); i++) {

                    list.add(new PlaylistSongs(i + 1, playlistSongsBean.getPlaylist().getTracks().get(i).getName(),
                            playlistSongsBean.getPlaylist().getTracks().get(i).getAl().getName(),
                            playlistSongsBean.getPlaylist().getTracks().get(i).getAr().get(0).getName(),
                            playlistSongsBean.getPlaylist().getTracks().get(i).getAl().getPicUrl(),playlistSongsBean.getPlaylist().getCoverImgUrl(),
                            playlistSongsBean.getPlaylist().getName(),playlistSongsBean.getPlaylist().getTracks().get(i).getId()));
            }
            _playlistSong.postValue(list);
    }

    private void getDayRecommendData(String data,boolean isEmpty){
        if(isEmpty){
            Gson gson=new Gson();
            DayRecommendSongsBean bean=gson.fromJson(data,new TypeToken<DayRecommendSongsBean>(){}.getType());

            if(sp.getString("drsb","")==null||sp.getString("drsb","").length()==0) {
                dayRecommendSongsDao.addData(bean);
                editor.putString("drsb","NOT_EMPTY");
                editor.commit();
            }else {


                    dayRecommendSongsDao.update(bean);


            }


        }

        List<PlaylistSongs> recommendSongs=new ArrayList<>();

        DayRecommendSongsBean dayRecommendSongsBean=new DayRecommendSongsBean();
        List<DayRecommendSongsBean> list=dayRecommendSongsDao.load();

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getData()!=null){
                dayRecommendSongsBean=list.get(i);
                break;
            }
        }

        if(dayRecommendSongsBean.getData()!=null) {
            for (int i = 0; i < dayRecommendSongsBean.getData().getDailySongs().size(); i++) {
                recommendSongs.add(new PlaylistSongs(i + 1, dayRecommendSongsBean.getData().getDailySongs().get(i).getName(),
                        dayRecommendSongsBean.getData().getDailySongs().get(i).getAl().getName(),
                        dayRecommendSongsBean.getData().getDailySongs().get(i).getAr().get(0).getName(),
                        dayRecommendSongsBean.getData().getDailySongs().get(i).getAl().getPicUrl(), "DAY", " ",
                        dayRecommendSongsBean.getData().getDailySongs().get(i).getId()));
            }
        }
        _playlistSong.postValue(recommendSongs);
    }


    public void setSp(SharedPreferences sp) {
        this.sp = sp;
        editor=sp.edit();
    }


    public void getDayRecommendFromIn(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                cookie=loginBeanDao.getCookie();

                if(cookie==null){
                    _msg.postValue("未登录");
                    return;
                }

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
                                getDayRecommendData(data, true);
                            }
                        });
            }
        }).start();

    }

    public void getSearchSong(String searchThing){

        tool=new InternetTool();
        tool.setBaseUrl("http://redrock.udday.cn:2022")
                .setRequestType(InternetTool.GET)
                .setPortPath("/cloudsearch")
                .setRequestData("keywords",searchThing)
                .startRequest(new InternetTool.Back() {
                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onFinish(String data) {
                        makeSearchData(data);
                    }
                });

    }

    private void makeSearchData(String data) {

        Gson gson = new Gson();
        SearchBean bean = gson.fromJson(data, new TypeToken<SearchBean>() {
        }.getType());

        List<PlaylistSongs> list = new ArrayList<>();

        if (bean.getResult() != null) {
            if (bean.getResult().getSongs().size() > 0) {

                for (int i = 0; i < bean.getResult().getSongs().size(); i++) {
                    list.add(new PlaylistSongs(i + 1, bean.getResult().getSongs().get(i).getName(), bean.getResult().getSongs().get(i).getAl().getName(),
                            bean.getResult().getSongs().get(i).getAr().get(0).getName(), bean.getResult().getSongs().get(i).getAl().getPicUrl(), null,
                            bean.getResult().getSongs().get(i).getAl().getName(), bean.getResult().getSongs().get(i).getId()));

                }

                _searchSong.postValue(list);

            }
        }else {
            _msg.postValue("没搜索到歌曲");
        }
    }

}
