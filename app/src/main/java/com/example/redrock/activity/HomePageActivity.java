package com.example.redrock.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.redrock.R;
import com.example.redrock.adapter.HomePageAdapter;
import com.example.redrock.base.APP;
import com.example.redrock.base.BaseActivity;
import com.example.redrock.fragment.LoginFragment;
import com.example.redrock.fragment.PageFound;
import com.example.redrock.fragment.PageMy;
import com.example.redrock.service.PlayMusicService;
import com.example.redrock.viewModel.HomePageMyViewModel;
import com.example.redrock.viewModel.HomePageViewModel;
import com.example.redrock.viewModel.LyricsActivityViewModel;
import com.example.redrock.viewModel.PlaylistSongViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomePageActivity extends BaseActivity implements View.OnClickListener{

    private ViewPager2 homePage;
    private TabLayout homeTy;
    private List<String> tab;
    private List<Fragment> page;
    private Toolbar homePageToolbar;
    private DrawerLayout homePageDrawer;
    private ImageView headPortrait,songPhoto,play;
    private TextView userName,songName,songAu;
    private String photoUrl;
    private EditText search;

    private Button logOut;

    public static HomePageActivity HOME_PAGE_ACTIVITY=null;

    private PlayMusicService.PlaySongBinder mBinder;

    private LinearLayout songLyrics;
    private LyricsActivityViewModel lyricsActivityViewModel;
    private LyricsActivity lyricsActivity;

    private String songId;


    private int angle=0;

    private HomePageViewModel homePageViewModel;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private boolean isAg=false;
    private float ag=0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        init();

        DrawerLayout.SimpleDrawerListener mSimpleDrawerListener = new DrawerLayout.SimpleDrawerListener(){
            @Override
            public void onDrawerOpened(View drawerView) {
                //档DrawerLayout打开时，让整体DrawerLayout布局可以响应点击事件
                drawerView.setClickable(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        homePageDrawer.addDrawerListener(mSimpleDrawerListener);


        HOME_PAGE_ACTIVITY=this;
        play.setVisibility(View.GONE);

        homePageViewModel.getData();
        homePageViewModel.headPortrait.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Glide.with(APP.getContext()).load(s).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(headPortrait);
            }
        });
        homePageViewModel.userName.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                userName.setText(s);
            }
        });

        homePageViewModel.songPhoto.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Glide.with(HomePageActivity.this).load(s).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(songPhoto);
                photoUrl=s;
                if(!isAg){
                    setAg();
                }
            }
        });
        homePageViewModel.songName.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                songName.setText(s);
            }
        });
        homePageViewModel.songAu.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                songAu.setText(s);
            }
        });
        homePageViewModel.songId.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                songId=s;
            }
        });



        homePageViewModel.pausePlay.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {


                play.setImageResource(integer);

                if(mBinder!=null) {
                    if (integer == R.drawable.pause) {

                        mBinder.pause();
                        isAg=false;

                    } else {
                        mBinder.start();
                        if(!isAg){
                            setAg();
                        }
                    }
                }

            }
        });
        homePageViewModel.serviceConnect.observe(this, new Observer<PlayMusicService.PlaySongBinder>() {
            @Override
            public void onChanged(PlayMusicService.PlaySongBinder playSongBinder) {
                mBinder=playSongBinder;
            }
        });




        homeTy.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index=tab.getPosition();
                if(index==0){
                    tab.setIcon(R.drawable.found_selector);
                }else if(index==1){
                    tab.setIcon(R.drawable.my_selector);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                int index=tab.getPosition();
                if(index==0){
                    tab.setIcon(R.drawable.found_unselector);
                }else if(index==1){
                    tab.setIcon(R.drawable.my_unselector);
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        new TabLayoutMediator(homeTy,homePage, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(HomePageActivity.this.tab.get(position));

                if(position==0){
                 tab.setIcon(R.drawable.found_unselector);
                }else if(position==1){
                    tab.setIcon(R.drawable.my_unselector);
                }

            }
        }).attach();

    }



    private void init(){

        search=findViewById(R.id.home_page_search_edit);

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {


                            if(search.getText().toString()!=null&&search.getText().toString().length()>0) {
                                startActivity(new Intent(HomePageActivity.this,PlaylistSongActivity.class));


                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                PlaylistSongViewModel playlistSongViewModel=ViewModelProviders.of(PlaylistSongActivity.PLAYLIST_ACTIVITY).get(PlaylistSongViewModel.class);
                                playlistSongViewModel.getSearchSong(search.getText().toString());
                            }else {

                            }
                        }
                    }).start();

                }
                return false;
            }
        });





        sp=getSharedPreferences("Automatic_login",MODE_PRIVATE);
        editor=sp.edit();

        logOut=findViewById(R.id.Log_out);
        logOut.setOnClickListener(this);

        homePage=findViewById(R.id.home_page_vp);
        homePage.setUserInputEnabled(false);
        homePage.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        songLyrics=findViewById(R.id.home_page_song_lyrics);
        songLyrics.setOnClickListener(this);

        homeTy=findViewById(R.id.home_page_ty);

        tab=new ArrayList<>();
        tab.add("发现");
        tab.add("我的");

        page=new ArrayList<>();
        page.add(new PageFound());
        page.add(new PageMy());
        homePage.setAdapter(new HomePageAdapter(this,page));


        homePageDrawer=findViewById(R.id.home_page_drawerLayout);
        homePageToolbar=findViewById(R.id.home_page_toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, homePageDrawer, homePageToolbar, 0, 0);

        homePageDrawer.addDrawerListener(toggle);
        toggle.syncState();


        homePageViewModel= ViewModelProviders.of(this).get(HomePageViewModel.class);
        homePageViewModel.setHomePageMyViewModel(ViewModelProviders.of(this).get(HomePageMyViewModel.class));


        headPortrait=findViewById(R.id.home_page_start_head_portrait);
        userName=findViewById(R.id.home_page_start_user_name);

        songPhoto=findViewById(R.id.Home_page_song_po);
        songName=findViewById(R.id.home_page_song_na);
        songAu=findViewById(R.id.home_page_song_au);

        play=findViewById(R.id.home_page_play);
        play.setOnClickListener(this);


    }

    public void setPlay(){
        play.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_page_play:{

                homePageViewModel.setPauseOrPlay();

            }break;
            case R.id.home_page_song_lyrics:{

                if(!songName.getText().toString().equals("")){
                    Intent intent=new Intent(this,LyricsActivity.class);
                    startActivity(intent);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            lyricsActivity=LyricsActivity.LYRICS_ACTIVITY;
                            lyricsActivityViewModel=ViewModelProviders.of(lyricsActivity).get(LyricsActivityViewModel.class);
                            lyricsActivityViewModel.getLyrics(songId);
                            lyricsActivityViewModel.setServiceBinder(mBinder);


                            if(mBinder.isPlay()){
                                lyricsActivityViewModel.setPlay();
                            }else {
                                lyricsActivityViewModel.setPause();
                            }
                            lyricsActivityViewModel.setName(songName.getText().toString());
                            lyricsActivityViewModel.setMusicPhoto(photoUrl);

                        }
                    }).start();
                }


            }break;
            case R.id.Log_out:{
                editor.putString("isLogin","");
                editor.apply();
                startActivity(new Intent(HomePageActivity.this,MainActivity.class));
                break;
            }
            default:break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    private void setAg(){
        isAg=true;
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                while (true) {

                    if(!mBinder.isPlay()){
                        break;
                    }

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            songPhoto.setPivotX(songPhoto.getWidth() / 2);
                            songPhoto.setPivotY(songPhoto.getHeight() / 2);//支点在图片中心
                            songPhoto.setRotation(ag);

                            ag+=0.1;

                            if(ag>360){
                                ag=0;
                            }

                        }
                    });

                }

            }
        }).start();
    }

}