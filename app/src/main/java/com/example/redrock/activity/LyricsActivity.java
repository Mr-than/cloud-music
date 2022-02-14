package com.example.redrock.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.redrock.R;
import com.example.redrock.adapter.LyricsAdapter;
import com.example.redrock.bean.LyricsBean;
import com.example.redrock.model.CenterLayoutManager;
import com.example.redrock.service.PlayMusicService;
import com.example.redrock.viewModel.HomePageViewModel;
import com.example.redrock.viewModel.LyricsActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class LyricsActivity extends AppCompatActivity {

    private TextView progress1,progress2,progress3,progress4,musicName;

    private ImageView playPause,musicPhoto;

    private SeekBar seekBar;

    private Toolbar toolbar;

    private LyricsActivityViewModel lyricsActivityViewModel;
    private HomePageActivity homePageActivity;
    private HomePageViewModel homePageViewModel;

    private RecyclerView recyclerView;
    private CenterLayoutManager manager;

    public static LyricsActivity LYRICS_ACTIVITY;

    private PlayMusicService.PlaySongBinder mBinder;

    private List<LyricsBean> lyricsList;

    private boolean touch=true;
    private int firstItemPosition;

    private String ft;
    private String tf;

    private String sTime;

    private int p;

    private int firstLy;

    private float ag=0;

    private boolean isAg=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);


        intView();



        lyricsActivityViewModel.lyrics.observe(this, new Observer<List<LyricsBean>>() {
            @Override
         public void onChanged(List<LyricsBean> lyricsBeans) {




             lyricsList=new ArrayList<>();

             manager=new CenterLayoutManager(LyricsActivity.this);
             recyclerView.setLayoutManager(manager);

             recyclerView.post(new Runnable() {
                 @Override
                 public void run() {

              int rvHeight=recyclerView.getHeight()/2;
              int rvItemHeight=dpToPx(LyricsActivity.this,70);

              int emptyItemCount=rvHeight/rvItemHeight;

              firstLy=emptyItemCount;

              for (int i = 0; i < emptyItemCount; i++) {
                  lyricsList.add(new LyricsBean("","",""));
              }
              lyricsList.addAll(lyricsBeans);
              for (int i = 0; i < emptyItemCount; i++) {
                  lyricsList.add(new LyricsBean("","",""));
              }


              recyclerView.setAdapter(new LyricsAdapter(lyricsList));


                 }
             });
            }
        });




        lyricsActivityViewModel.playPause.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                playPause.setImageResource(integer);
                if(integer==R.drawable.start){

                    mBinder.start();
                    homePageViewModel.setPlay();

                    if(!isAg) {
                        setAg();
                    }

                }else {

                    mBinder.pause();
                    homePageViewModel.setPause();

                    isAg=false;

                    if(tf!=null&&ft!=null){
                        progress1.setText(tf);
                        progress2.setText(ft);
                    }


                }
            }
        });

        lyricsActivityViewModel.musicName.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                musicName.setText(s);
            }
        });

        lyricsActivityViewModel.musicPhoto.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Glide.with(LyricsActivity.this).load(s).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(musicPhoto);

                if(!isAg){
                    setAg();
                }

            }
        });

    }

    private void intView() {

        lyricsActivityViewModel= ViewModelProviders.of(this).get(LyricsActivityViewModel.class);

        musicPhoto=findViewById(R.id.lyrics_toolbar_imageview);

        homePageActivity=HomePageActivity.HOME_PAGE_ACTIVITY;
        homePageViewModel=ViewModelProviders.of(homePageActivity).get(HomePageViewModel.class);

        playPause=findViewById(R.id.lyrics_play_pause);


        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyricsActivityViewModel.setPauseOrPlay();
                setSeekBar();
            }
        });



        recyclerView=findViewById(R.id.Lyrics_rv);

        LYRICS_ACTIVITY=this;


        lyricsActivityViewModel.serviceConnect.observe(this, new Observer<PlayMusicService.PlaySongBinder>() {
            @Override
            public void onChanged(PlayMusicService.PlaySongBinder playSongBinder) {
                mBinder=playSongBinder;
                setSeekBar();

            }
        });


        toolbar=findViewById(R.id.Lyrics_page_toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



        seekBar=findViewById(R.id.music_seekbar);

        progress1 = findViewById(R.id.music_seekbar_time_1);

        progress2 = findViewById(R.id.music_seekbar_time_2);

        progress3 = findViewById(R.id.music_seekbar_time_3);

        progress4 = findViewById(R.id.music_seekbar_time_4);



        musicName=findViewById(R.id.lyrics_toolbar_textView);





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




    private void setSeekBar(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int max=0;

                if(mBinder.sumTime()>0){
                    max=mBinder.sumTime()/1000;
                }else {

                    while (mBinder.sumTime() <= 0) {
                        max = mBinder.sumTime() / 1000;

                    }
                }
                    seekBar.setMax(max);
                    int ma=max;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(ma>60){

                            int m=ma/60;

                            if(m<10) {
                                progress3.setText("0"+m);
                            }else {
                                progress4.setText(String.valueOf(m));
                            }

                            if(ma-m*60>=10) {
                                progress4.setText(String.valueOf(ma - m * 60));
                            }else {
                                progress4.setText("0"+(ma-m*60));
                            }
                        }else {
                            progress3.setText("00");
                            if(seekBar.getProgress()>=10) {
                                progress4.setText(String.valueOf(ma));
                            }else {
                                progress4.setText(String.valueOf(ma));
                            }
                        }

                        sTime=progress3.getText().toString()+":"+progress4.getText().toString();

                    }
                });

                int pr=0;

                while (pr<=mBinder.sumTime()){

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int pr=mBinder.pr()/1000;


                            if(mBinder.isPlay())
                            seekBar.setProgress(pr);



                        }
                    });
                    pr=mBinder.pr();
                }
            }
        }).start();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(seekBar.getProgress()>60){

                    int p=seekBar.getProgress();
                    int m=p/60;

                    if(m<10) {
                        progress1.setText("0"+m);
                    }else {
                        progress1.setText(String.valueOf(m));
                    }

                    if(p-m*60>=10) {
                        progress2.setText(String.valueOf(p - m * 60));
                    }else {
                        progress2.setText("0"+(p-m*60));
                    }
                }else {
                    progress1.setText("00");
                    if(seekBar.getProgress()>=10) {
                        progress2.setText(String.valueOf(seekBar.getProgress()));
                    }else {
                        progress2.setText("0"+seekBar.getProgress());
                    }
                }



                String time=progress1.getText().toString()+":"+progress2.getText().toString();
                tf=progress1.getText().toString();
                ft=progress2.getText().toString();



                aaa(time);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                touch=false;
                LyricsAdapter.ViewHolder holder1=(LyricsAdapter.ViewHolder)recyclerView.findViewHolderForLayoutPosition(p);
                if(holder1!=null){
                    holder1.lyrics1.setTextColor(Color.parseColor("#878787"));
                    holder1.lyrics2.setTextColor(Color.parseColor("#707070"));
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        touch=true;
                    }
                }).start();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    int pr=seekBar.getProgress()*1000;
                    mBinder.setPr(pr);
            }
        });
    }

    public void aaa(String time){

        for (int i=0; i < lyricsList.size(); i++) {
            if(lyricsList.get(i).getTime().equals(time)&&i!=firstLy){
                setLyrics(i);
            }

            if(time.equals(sTime)){
               lyricsActivityViewModel.setPlay();
               lyricsActivityViewModel.setPause();
            }
        }
    }


    public void setLyrics(int position){

        this.p=position;

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {


                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    @Override
                    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                        touch=false;

                        LyricsAdapter.ViewHolder holder1=(LyricsAdapter.ViewHolder)recyclerView.findViewHolderForLayoutPosition(position);
                        if(holder1!=null){
                            holder1.lyrics1.setTextColor(Color.parseColor("#878787"));
                            holder1.lyrics2.setTextColor(Color.parseColor("#707070"));
                        }

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException interruptedException) {
                                    interruptedException.printStackTrace();
                                }
                                touch=true;
                            }
                        }).start();
                        return false;
                    }

                    @Override
                    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });


                LyricsAdapter.ViewHolder holder1=(LyricsAdapter.ViewHolder)recyclerView.findViewHolderForLayoutPosition(position);
                LyricsAdapter.ViewHolder holder2=(LyricsAdapter.ViewHolder)recyclerView.findViewHolderForLayoutPosition(position-1);
                firstItemPosition=manager.findFirstCompletelyVisibleItemPosition();



                 if(touch) {

                     if(firstItemPosition-position>3*firstLy||firstItemPosition-position<-3*firstLy){
                         recyclerView.scrollToPosition(position);
                         recyclerView.scrollBy(0,-50);
                     }else {
                         recyclerView.smoothScrollToPosition(position);
                     }
                     if (holder1 != null) {
                         if (holder1.lyrics1.getText().toString().length() <= 0) {
                             holder1.lyrics1.setTextColor(Color.parseColor("#878787"));
                             holder1.lyrics2.setTextColor(Color.parseColor("#707070"));
                         } else {
                             holder1.lyrics1.setTextColor(Color.parseColor("#FFFFFFFF"));
                             holder1.lyrics2.setTextColor(Color.parseColor("#FFFFFFFF"));
                         }
                     }
                     if (holder2 != null) {
                         holder2.lyrics1.setTextColor(Color.parseColor("#878787"));
                         holder2.lyrics2.setTextColor(Color.parseColor("#707070"));
                     }
                 }


            }
        },250);

    }


    public static int dpToPx(Context ctx, float dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, ctx.getResources().getDisplayMetrics());
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

                            musicPhoto.setPivotX(musicPhoto.getWidth() / 2);
                            musicPhoto.setPivotY(musicPhoto.getHeight() / 2);//支点在图片中心
                            musicPhoto.setRotation(ag);

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