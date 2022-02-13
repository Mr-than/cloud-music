package com.example.redrock.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.redrock.bean.SongDetail;
import com.example.redrock.model.InternetTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

public class PlayMusicService extends Service {

    private MediaPlayer songPlayer;
    private InternetTool tool;
    private String id;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String pa;


    public PlayMusicService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        sp = getSharedPreferences("id", MODE_PRIVATE);
        editor = sp.edit();
        tool = new InternetTool();
        songPlayer = new MediaPlayer();

    }

    private PlaySongBinder mBinder = new PlaySongBinder();

    public class PlaySongBinder extends Binder {

        public void prepare(String path) {
            Log.e("TAG", "prepare: ");
            pa = path;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    tool.setBaseUrl("http://redrock.udday.cn:2022")
                            .setRequestType(InternetTool.GET)
                            .setPortPath("/song/url")
                            .setRequestData("id", pa)
                            .startRequest(new InternetTool.Back() {
                                @Override
                                public void onFinish(String data) {

                                    Gson gson = new Gson();
                                    SongDetail song = gson.fromJson(data, new TypeToken<SongDetail>() {
                                    }.getType());
                                    try {
                                        id = sp.getString("id", "");
                                        if (!pa.equals(id) && id != null && !id.equals("") && songPlayer != null) {
                                            songPlayer.stop();
                                            songPlayer.reset();
                                        }

                                        editor.putString("id", pa);
                                        editor.apply();


                                        pa = song.getData().get(0).getUrl();

                                        try {
                                            songPlayer.setAudioAttributes(
                                                    new AudioAttributes.Builder()
                                                            //设置播放的类型
                                                            //音频or视频or......
                                                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                                            .setUsage(AudioAttributes.USAGE_MEDIA)
                                                            .build()
                                            );
                                            songPlayer.setDataSource(pa);
                                            songPlayer.prepare();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        songPlayer.start();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                }
            }).start();
        }

        public void start() {
            Log.e("TAG", "start: ");
            songPlayer.start();
        }

        public void pause() {
            Log.e("TAG", "pause: ");
            songPlayer.pause();
        }



        public int pr() {
            int pr = 0;


                try {
                    if(songPlayer.isPlaying())
                    pr = songPlayer.getCurrentPosition();
                } catch (NullPointerException n) {
                    n.printStackTrace();
                }

            return pr;
        }

        public int sumTime() {

            int du = 0;


                try {
                    if (songPlayer.isPlaying()){
                        du = songPlayer.getDuration();
                    }
                } catch (IllegalStateException i) {
                   i.printStackTrace();
                }


            return du;
        }

        public void setPr(int pr) {

            songPlayer.seekTo(pr);

            songPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

        }


        public boolean isPlay() {
            return songPlayer.isPlaying();
        }

    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}