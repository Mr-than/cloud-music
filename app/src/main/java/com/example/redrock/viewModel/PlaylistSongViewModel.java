package com.example.redrock.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import com.example.redrock.base.APP;
import com.example.redrock.bean.DayRecommendSongsBean;
import com.example.redrock.bean.PlaylistSongs;
import com.example.redrock.bean.PlaylistSongsBean;
import com.example.redrock.fragment.PageFound;
import com.example.redrock.model.InternetTool;
import com.example.redrock.room.DayRecommendSongsDao;
import com.example.redrock.room.HomePageDataBase;
import com.example.redrock.room.PlaylistSongDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class PlaylistSongViewModel extends ViewModel {

    private MutableLiveData<List<PlaylistSongs>> _playlistSong=new MutableLiveData<>();

    public LiveData<List<PlaylistSongs>> playlistSong=_playlistSong;

    private HomePageDataBase dataBase= Room.databaseBuilder(APP.getContext(),HomePageDataBase.class,"HomeFoundWonderful").build();
    private PlaylistSongDao playlistSongDao= dataBase.playlistSongDao();
    private DayRecommendSongsDao dayRecommendSongsDao=dataBase.dayRecommendSongsDao();

    private InternetTool tool;

    public void getPlaylistSong(String id){

            if(id.equals(PageFound.DAY_RECOMMEND_SONGS)){

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
                                    getDayRecommendData(data, true);
                                }
                            });
                }else {
                    getDayRecommendData(null,false);
                }


            }else {
                tool = new InternetTool();
                tool.setBaseUrl("http://redrock.udday.cn:2022")
                        .setRequestType(InternetTool.GET)
                        .setHeader("Cookie","MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/eapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/eapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/neapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/openapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/eapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/weapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/eapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/neapi/feedback;;__remember_me=true; Max-Age=1296000; Expires=Wed, 23 Feb 2022 13:50:24 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/wapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/api/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/api/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/neapi/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/weapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/wapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/api/clientlog;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/wapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/neapi/feedback;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1546269261158; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/weapi/feedback;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/weapi/clientlog;;MUSIC_U=0b0eba60606860f12eb70629773aa72d96dcfc026b2bca4e21ac1691160dac62c84e8a4f4ba4f13ed78b6050a17a35e705925a4e6992f61d07c385928f88e8de; Max-Age=1296000; Expires=Wed, 23 Feb 2022 13:50:24 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/api/feedback;;__csrf=24bb0828e2626c532368c8454e203e57; Max-Age=1296010; Expires=Wed, 23 Feb 2022 13:50:34 GMT; Path=/;;MUSIC_A_T=1546266359140; Max-Age=2147483647; Expires=Sun, 26 Feb 2090 17:04:31 GMT; Path=/openapi/clientlog;;MUSIC_SNS=; Max-Age=0; Expires=Tue, 8 Feb 2022 13:50:24 GMT; Path=/")
                        .setPortPath("/playlist/detail")
                        .setRequestData("id", id)
                        .startRequest(new InternetTool.Back() {
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
            dayRecommendSongsDao.addData(bean);
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

        for (int i = 0; i < dayRecommendSongsBean.getData().getDailySongs().size(); i++) {
            recommendSongs.add(new PlaylistSongs(i+1,dayRecommendSongsBean.getData().getDailySongs().get(i).getName(),
                    dayRecommendSongsBean.getData().getDailySongs().get(i).getAl().getName(),
                    dayRecommendSongsBean.getData().getDailySongs().get(i).getAr().get(0).getName(),
                    dayRecommendSongsBean.getData().getDailySongs().get(i).getAl().getPicUrl(),"DAY"," ",
                    dayRecommendSongsBean.getData().getDailySongs().get(i).getId()));
        }
        _playlistSong.postValue(recommendSongs);


    }
}
