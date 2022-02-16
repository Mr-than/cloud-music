package com.example.redrock.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.redrock.R;
import com.example.redrock.activity.HomePageActivity;
import com.example.redrock.base.BaseActivity;
import com.example.redrock.bean.SongDetail;
import com.example.redrock.model.InternetTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

/**
 *   description:播放音乐的服务
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/5
 */

public class PlayMusicService extends Service {

    private MediaPlayer songPlayer;
    private InternetTool tool;
    private String id;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String pa;
    private NotificationManager manager;
    private SongDetail song;

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
                                public void onError() {

                                }

                                @Override
                                public void onFinish(String data) {

                                    Gson gson = new Gson();
                                    song = gson.fromJson(data, new TypeToken<SongDetail>() {}.getType());


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

                    createNotificationChannel();





                    Intent intent = new Intent(PlayMusicService.this,HomePageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);





                    PendingIntent pi=PendingIntent.getActivity(PlayMusicService.this,0,intent,PendingIntent.FLAG_MUTABLE);
                    NotificationCompat.Builder builder=new NotificationCompat.Builder(PlayMusicService.this,"001")
                            .setContentTitle("正在播放音乐")
                            .setSmallIcon(R.drawable.ico)
                            .setContentIntent(pi)
                            .setContentText("");
                            startForeground(1, builder.build());
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

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel=new NotificationChannel("001","name", NotificationManager.IMPORTANCE_HIGH);
            channel.setSound(null,null);
            channel.setImportance(NotificationManager.IMPORTANCE_LOW);
            manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

}