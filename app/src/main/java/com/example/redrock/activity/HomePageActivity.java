package com.example.redrock.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.redrock.R;
import com.example.redrock.adapter.HomePageAdapter;
import com.example.redrock.base.APP;
import com.example.redrock.base.BaseActivity;
import com.example.redrock.fragment.PageFound;
import com.example.redrock.fragment.PageMy;
import com.example.redrock.room.DayRecommendPlaylistsDao;
import com.example.redrock.room.DayRecommendSongsDao;
import com.example.redrock.room.HomePageDataBase;
import com.example.redrock.room.LoginBeanDao;
import com.example.redrock.room.PersonalPlaylistDao;
import com.example.redrock.service.PlayMusicService;
import com.example.redrock.viewmodel.HomePageMyViewModel;
import com.example.redrock.viewmodel.HomePageViewModel;
import com.example.redrock.viewmodel.LyricsActivityViewModel;
import com.example.redrock.viewmodel.PlaylistSongViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;


/**
 *   description:这是主界面的活动，装载了一个VP2，VP2装载了两个fragment
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/5
 */

public class HomePageActivity extends BaseActivity implements View.OnClickListener{
    //主界面的VP2
    private ViewPager2 homePage;
    //
    private TabLayout homeTy;
    //装tablayout标签的list
    private List<String> tab;
    //装VP2碎片的list
    private List<Fragment> page;
    //toolbar
    private Toolbar homePageToolbar;
    //
    private DrawerLayout homePageDrawer;
    //侧滑栏上头像和下方正在播放歌曲的播放状态（头像和名字和“我的”页面里的是同一个资源）
    private ImageView headPortrait,songPhoto,play;
    //正在播放歌曲的名字和作者，第一个userName是侧滑栏上的名字
    private TextView userName,songName,songAu;
    //歌曲头像的url
    private String photoUrl;
    //搜索框
    private EditText search;
    //数据库
    private HomePageDataBase dataBase;
    //登出按钮
    private Button logOut;
    //活动本身，用于外界获取此活动
    public static HomePageActivity HOME_PAGE_ACTIVITY=null;
    //服务的binder
    private PlayMusicService.PlaySongBinder mBinder;
    //下方正在播放歌曲的父布局,用于跳转到歌词页面
    private LinearLayout songLyrics;
    //歌词界面的ViewModel
    private LyricsActivityViewModel lyricsActivityViewModel;
    //歌词活动的引用
    private LyricsActivity lyricsActivity;
    //音乐的id
    private String songId;
    //对应界面的sp，用于记录界面是否更新
    private SharedPreferences spFound,spMy,spDaySong;
    //主界面的ViewModel
    private HomePageViewModel homePageViewModel;
    //记录是否已经登录
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    //用于记录三个界面的数据刷新时间
    private SharedPreferences.Editor editor1,editor2,editor3;
    //是否再旋转
    private boolean isAg=false;
    //播放歌曲的时候的图片旋转角度
    private float ag=0f;

    @Override
    protected void onStart() {
        super.onStart();
        homePageViewModel.setIsLogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        init();

        spFound=getSharedPreferences("isFoundRefresh",MODE_PRIVATE);
        editor1=spFound.edit();
        spMy=getSharedPreferences("isMyRefresh",MODE_PRIVATE);
        editor2=spMy.edit();
        spDaySong=getSharedPreferences("isSongRefresh",MODE_PRIVATE);
        editor3=spDaySong.edit();



        HOME_PAGE_ACTIVITY=this;
        play.setVisibility(View.GONE);
        //获取用户信息的观察者
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
        //音乐信息的观察者
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


        //控制音乐的开关
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
        //获取服务的binder
        homePageViewModel.serviceConnect.observe(this, new Observer<PlayMusicService.PlaySongBinder>() {
            @Override
            public void onChanged(PlayMusicService.PlaySongBinder playSongBinder) {
                mBinder=playSongBinder;
            }
        });


        homePageViewModel.isLogin.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                logOut.setText(s);
            }
        });


        //设置tabLayout的样式，对应旋转时图片和没选择时的图片
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

    //调用这个方法让播放键显示
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
                    Intent intent = new Intent(this,LyricsActivity.class);
                    startActivity(intent);

                    //延时，为了确保LyricsActivity.LYRICS_ACTIVITY不为空
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
                            lyricsActivityViewModel.setSongId(songId);
                        }
                    }).start();
                }


            }break;
            case R.id.Log_out:{
                editor.putString("isLogin","");
                editor.apply();
                startActivity(new Intent(HomePageActivity.this,MainActivity.class));
                dataBase= Room.databaseBuilder(APP.getContext(),HomePageDataBase.class,"HomeFoundWonderful").build();
                LoginBeanDao loginBeanDao=dataBase.loginBeanDao();
                DayRecommendPlaylistsDao dayRecommendPlaylistsDao=dataBase.dayRecommendPlaylistsDao();
                DayRecommendSongsDao dayRecommendSongsDao=dataBase.dayRecommendSongsDao();
                PersonalPlaylistDao personalPlaylistDao=dataBase.getPersonalPlaylistDao();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loginBeanDao.nukeTable();
                        dayRecommendPlaylistsDao.nukeTable();
                        dayRecommendSongsDao.nukeTable();
                        personalPlaylistDao.nukeTable();
                        SharedPreferences.Editor editor=getSharedPreferences("roomIsEmpty",MODE_PRIVATE).edit();
                        SharedPreferences.Editor editor1=getSharedPreferences("found", BaseActivity.MODE_PRIVATE).edit();
                        SharedPreferences.Editor editor2=getSharedPreferences("my", BaseActivity.MODE_PRIVATE).edit();
                        SharedPreferences.Editor editor3=getSharedPreferences("re",MODE_PRIVATE).edit();


                        editor1.putString("drpl","");
                        editor1.putString("wrpl","");
                        editor1.apply();
                        editor2.putString("cplb","");
                        editor2.apply();
                        editor3.putString("drsb","");
                        editor3.apply();

                        editor.putString("is","");
                        editor.apply();

                        if(mBinder!=null)
                        if(mBinder.isPlay()){
                            mBinder.pause();
                            stopService(new Intent(HomePageActivity.this,PlayMusicService.class));
                        }
                        finish();

                    }
                }).start();



                break;
            }
            default:break;
        }
    }

    //重写返回键使其拥有home键效果，为了保证在后台运行，界面不出错
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    //图片旋转的方法
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