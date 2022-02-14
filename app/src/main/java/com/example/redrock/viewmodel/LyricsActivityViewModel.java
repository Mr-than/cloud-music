package com.example.redrock.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.redrock.R;
import com.example.redrock.bean.LyricsBean;
import com.example.redrock.bean.SongLyricsBean;
import com.example.redrock.model.InternetTool;
import com.example.redrock.service.PlayMusicService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class LyricsActivityViewModel extends ViewModel {

    private MutableLiveData<List<LyricsBean>> _lyrics=new MutableLiveData<>();
    private MutableLiveData<Integer> _playPause=new MutableLiveData<>();
    private MutableLiveData<String> _musicName=new MutableLiveData<>();

    public LiveData<List<LyricsBean>> lyrics=_lyrics;
    public LiveData<Integer> playPause=_playPause;
    public LiveData<String> musicName=_musicName;
    private MutableLiveData<String> _songId=new MutableLiveData<>();
    public LiveData<String> songId=_songId;

    private MutableLiveData<String> _musicPhoto=new MutableLiveData<>();
    public LiveData<String> musicPhoto=_musicPhoto;

    private boolean isPlay=false;

    private InternetTool tool=new InternetTool();

    private MutableLiveData<PlayMusicService.PlaySongBinder> _serviceConnect=new MutableLiveData<>();
    public final LiveData<PlayMusicService.PlaySongBinder> serviceConnect=_serviceConnect;

    public void getLyrics(String id){

        tool.setBaseUrl("http://redrock.udday.cn:2022")
                .setRequestType(InternetTool.GET)
                .setPortPath("/lyric")
                .setRequestData("id",id)
                .startRequest(new InternetTool.Back() {
                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onFinish(String data) {
                            getData(data);
                    }
                });
    }

    private void getData(String data){

        Gson gson=new Gson();
        SongLyricsBean bean=gson.fromJson(data,new TypeToken<SongLyricsBean>(){}.getType());


        if(bean.getLrc()!=null) {

            String[] ly1 = bean.getLrc().getLyric().split("\n");


            List<LyricsBean> list=new ArrayList<>();

            List<String> t1=new ArrayList<>();
            List<String> t2=new ArrayList<>();

            StringBuilder time1 = new StringBuilder();
            StringBuilder time2=new StringBuilder();


            if(bean.getTlyric()!=null&&bean.getTlyric().getLyric().length()>0){

                String[] ly2=bean.getTlyric().getLyric().split("\n");

                for (int i = 0; i < ly1.length; i++) {
                    if ((int) ly1[i].toCharArray()[1] <= 57 && (int) ly1[i].toCharArray()[1] >= 48) {
                        for (int length = 1; length < ly1[i].toCharArray().length; length++) {
                            if (ly1[i].toCharArray()[length] == ']') {
                                ly1[i] = ly1[i].substring(length + 1);
                                break;
                            }
                            time1.append(ly1[i].toCharArray()[length]);

                        }
                    }

                    t1.add(time1.toString());
                    time1 = new StringBuilder();
                }

                for (int i = 0; i < ly2.length; i++) {
                    if ((int) ly2[i].toCharArray()[1] <= 57 && (int) ly2[i].toCharArray()[1] >= 48) {
                        for (int length = 1; length < ly2[i].toCharArray().length; length++) {
                            if (ly2[i].toCharArray()[length] == ']') {
                                ly2[i] = ly2[i].substring(length + 1);
                                break;
                            }
                            time2.append(ly2[i].toCharArray()[length]);

                        }
                    }

                    t2.add(time2.toString());
                    time2 = new StringBuilder();
                }


                for (int i = 0; i < t1.size(); i++) {

                    for (int j = 0; j < t2.size(); j++) {


                            if (t2.get(j).equals(t1.get(i))) {

                                list.add(new LyricsBean(ly1[i], ly2[j], t1.get(i).substring(0,5)));
                            }


                    }

                }



            }else {

                for (int i = 0; i < ly1.length; i++) {
                    if ((int) ly1[i].toCharArray()[1] <= 57 && (int) ly1[i].toCharArray()[1] >= 48) {
                        for (int length = 1; length < ly1[i].toCharArray().length; length++) {
                            if (ly1[i].toCharArray()[length] == ']') {
                                ly1[i] = ly1[i].substring(length + 1);
                                break;
                            }
                            time1.append(ly1[i].toCharArray()[length]);

                        }
                    }

                    list.add(new LyricsBean(ly1[i],"",time1.toString().substring(0,5)));
                    time1 = new StringBuilder();
                }

            }

            _lyrics.postValue(list);
        }
    }

    public void setServiceBinder(PlayMusicService.PlaySongBinder mBinder){
        _serviceConnect.postValue(mBinder);
    }

    public void setPauseOrPlay(){

        if(isPlay){
            _playPause.postValue(R.drawable.start);
            isPlay=false;
        }else {
            _playPause.postValue(R.drawable.pause);
            isPlay=true;
        }

    }

    public void setPlay(){
        _playPause.postValue(R.drawable.start);
        isPlay=false;
    }

    public void setPause(){
        _playPause.postValue(R.drawable.pause);
        isPlay=true;
    }


    public void setName(String name){
        _musicName.postValue(name);
    }

    public void setMusicPhoto(String url) {
        this._musicPhoto.postValue(url);
    }

    public void setSongId(String id){
        _songId.postValue(id);
    }

}
