package com.example.redrock.activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.ActionBar;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.redrock.R;
import com.example.redrock.adapter.PlaylistSongAdapter;
import com.example.redrock.base.BaseActivity;
import com.example.redrock.bean.PlaylistSongs;
import com.example.redrock.viewmodel.PlaylistSongViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

public class PlaylistSongActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ImageView playlistPhoto;
    private TextView playlistName;
    public static PlaylistSongActivity PLAYLIST_ACTIVITY =null;
    private PlaylistSongViewModel playlistSongViewModel;
    private CollapsingToolbarLayout toolbarLayout;
    private AppBarLayout appBarLayout;
    private ProgressDialog dialog;
    private Calendar calendars;

    private String month;
    private String day;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private SharedPreferences update;
    private SharedPreferences.Editor upe;
    private BigInteger bigInteger;
    private BigInteger updateT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_song);


        update=getSharedPreferences("PlaylistUpdateTime",BaseActivity.MODE_PRIVATE);
        upe=update.edit();

        bigInteger=new BigInteger(String.valueOf(System.currentTimeMillis()));

        if((update.getString("time","")!=null&&update.getString("time","").length()>0)){
            updateT=new BigInteger(update.getString("time",""));
            long time=Long.parseLong(bigInteger.subtract(updateT).toString());
            if(time>=3600000){
                playlistSongViewModel.getDayRecommendFromIn();
                upe.putString("time",String.valueOf(System.currentTimeMillis()));
                upe.apply();
            }
        }



        sp=getSharedPreferences("isSongRefresh",MODE_PRIVATE);
        editor=sp.edit();


        dialog=new ProgressDialog(PlaylistSongActivity.this);
        dialog.setTitle("正在加载歌单，请耐心等待");
        dialog.setMessage("由于歌单歌曲部分未缓存入数据库，所以加载较慢。。。");
        dialog.setCancelable(true);
        dialog.show();


            initView();

            ActionBar actionBar=getSupportActionBar();
                if(actionBar!=null){
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }


                playlistSongViewModel.searchSong.observe(this, new Observer<List<PlaylistSongs>>() {
                    @Override
                    public void onChanged(List<PlaylistSongs> playlistSongs) {
                        LinearLayoutManager manager = new LinearLayoutManager(PlaylistSongActivity.this);
                        PlaylistSongAdapter adapter = new PlaylistSongAdapter(playlistSongs, HomePageActivity.HOME_PAGE_ACTIVITY);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                        dialog.cancel();
                    }
                });

                playlistSongViewModel.msg.observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(PlaylistSongActivity.this, s, Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

            playlistSongViewModel.playlistSong.observe(this, new Observer<List<PlaylistSongs>>() {
                @Override
                public void onChanged(List<PlaylistSongs> playlistSongs) {
                    if (playlistSongs.size() > 0) {

                        LinearLayoutManager manager = new LinearLayoutManager(PlaylistSongActivity.this);
                        PlaylistSongAdapter adapter = new PlaylistSongAdapter(playlistSongs, HomePageActivity.HOME_PAGE_ACTIVITY);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);

                        if (playlistSongs.get(0).getPlaylistPhoto().equals("DAY")) {
                            Glide.with(PlaylistSongActivity.this).load(R.drawable.dayplaylistphoto).transform(new RoundedCorners(25)).into(playlistPhoto);
                            playlistName.setText(" ");
                            setToolbarTitle("每日推荐", day + "/" + month);
                        } else {
                            Glide.with(PlaylistSongActivity.this).load(playlistSongs.get(0).getPlaylistPhoto()).transform(new RoundedCorners(25)).into(playlistPhoto);
                            playlistName.setText(playlistSongs.get(0).getPlaylistName());
                            setToolbarTitle(playlistSongs.get(0).getPlaylistName(), " ");
                        }

                        dialog.cancel();
                    }else {
                        Toast.makeText(PlaylistSongActivity.this, "歌单为空", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                }
            });

            PLAYLIST_ACTIVITY =this;
    }

    private void initView() {

        toolbarLayout=findViewById(R.id.playlist_song_activity_ctl);
        playlistSongViewModel= ViewModelProviders.of(this).get(PlaylistSongViewModel.class);
        playlistSongViewModel.setSp(getSharedPreferences("re",MODE_PRIVATE));

        if(sp.getString("refresh_3","").equals("refresh")){
            playlistSongViewModel.getDayRecommendFromIn();
            editor.putString("refresh_3","");
            editor.apply();
            upe.putString("time",String.valueOf(System.currentTimeMillis()));
            upe.apply();
        }


        recyclerView=findViewById(R.id.playlist_song_rv);


        appBarLayout=findViewById(R.id.playlist_song_activity_ab);

        playlistPhoto=findViewById(R.id.playlist_song_playlist_photo);
        playlistName=findViewById(R.id.playlist_song_playlist_name);

        toolbar=findViewById(R.id.playlist_song_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");

        calendars=Calendar.getInstance();
        calendars.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        month = String.valueOf(calendars.get(Calendar.MONTH) + 1);
        day = String.valueOf(calendars.get(Calendar.DATE));
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
            }break;
            default:break;
        }

        return true;
    }

    private void setToolbarTitle(String titleShrink,String titleSpread){

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
                {
                    toolbarLayout.setTitle(titleShrink);

                }
                else
                {
                    toolbarLayout.setTitle(titleSpread);
                }
            }
        });

    }

}