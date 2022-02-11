package com.example.redrock.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.redrock.R;
import com.example.redrock.activity.HomePageActivity;
import com.example.redrock.activity.PlaylistSongActivity;
import com.example.redrock.adapter.DayRecommendPlaylistAdapter;
import com.example.redrock.base.APP;
import com.example.redrock.bean.DayRecommendBean;
import com.example.redrock.viewModel.HomePageFoundViewModel;
import com.example.redrock.viewModel.HomePageViewModel;
import com.example.redrock.viewModel.PlaylistSongViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PageFound extends Fragment implements View.OnClickListener{

    private View view;
    private HomePageFoundViewModel homePageFoundViewModel;
    private HomePageViewModel homePageViewModel;
    private TextView wonderfulName1,wonderfulName2,wonderfulName3,recommendSongName1,recommendSongName2,recommendSongName3;
    private ImageView wonderfulPhoto1,wonderfulPhoto2,wonderfulPhoto3,recommendSongPhoto1,recommendSongPhoto2,recommendSongPhoto3,dayMusic;
    private int number;
    private RecyclerView recommendPlaylist;
    private List<String> wonderfulPlaylistIds;
    private PlaylistSongActivity playlistSongActivity;
    private String photo1,photo2,photo3;
    private String name1,name2,name3;
    private String au1,au2,au3;
    private LinearLayout recommendSong1,recommendSong2,recommendSong3;

    public static final String DAY_RECOMMEND_SONGS="DAY_RECOMMEND";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.page_found_fragment,container,false);
        initView();

        Random random=new Random();
        number=random.nextInt(47);


        homePageFoundViewModel= ViewModelProviders.of(this).get(HomePageFoundViewModel.class);
        homePageFoundViewModel.wonderfulPlaylistName.observe(requireActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> s) {
                    wonderfulName1.setText(s.get(number));
                    wonderfulName2.setText(s.get(number+1));
                    wonderfulName3.setText(s.get(number+2));
            }
        });
        homePageFoundViewModel.getWonderfulPlaylist();
        homePageFoundViewModel.wonderfulPlaylistPhoto.observe(requireActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> photo) {
                Glide.with(PageFound.this).load(photo.get(number)).transform(new RoundedCorners(25)).into(wonderfulPhoto1);
                Glide.with(PageFound.this).load(photo.get(number+1)).transform(new RoundedCorners(25)).into(wonderfulPhoto2);
                Glide.with(PageFound.this).load(photo.get(number+2)).transform(new RoundedCorners(25)).into(wonderfulPhoto3);
            }
        });
        homePageFoundViewModel.wonderfulPlaylistId.observe(requireActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                if(wonderfulPlaylistIds.size()>0){
                    wonderfulPlaylistIds.clear();
                }

                wonderfulPlaylistIds.add(strings.get(number));
                wonderfulPlaylistIds.add(strings.get(number+1));
                wonderfulPlaylistIds.add(strings.get(number+2));
            }
        });



        homePageFoundViewModel.getRecommendPlaylists();
        homePageFoundViewModel.recommendPlaylist.observe(requireActivity(), new Observer<List<DayRecommendBean>>() {
            @Override
            public void onChanged(List<DayRecommendBean> dayRecommendBeans) {
                LinearLayoutManager manager=new LinearLayoutManager(APP.getContext());
                manager.setOrientation(RecyclerView.HORIZONTAL);
                recommendPlaylist.setLayoutManager(manager);
                recommendPlaylist.setAdapter(new DayRecommendPlaylistAdapter(dayRecommendBeans));

            }
        });

        homePageFoundViewModel.getDayRecommendSongData();
        homePageFoundViewModel.recommendSongName.observe(requireActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                recommendSongName1.setText(strings.get(0));
                name1=strings.get(0);
                recommendSongName2.setText(strings.get(1));
                name2=strings.get(1);
                recommendSongName3.setText(strings.get(2));
                name3=strings.get(2);
            }
        });
        homePageFoundViewModel.recommendSongPhoto.observe(requireActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                Glide.with(PageFound.this).load(strings.get(0)).transform(new RoundedCorners(25)).into(recommendSongPhoto1);
                photo1=strings.get(0);
                Glide.with(PageFound.this).load(strings.get(1)).transform(new RoundedCorners(25)).into(recommendSongPhoto2);
                photo2=strings.get(1);
                Glide.with(PageFound.this).load(strings.get(2)).transform(new RoundedCorners(25)).into(recommendSongPhoto3);
                photo3=strings.get(2);
            }
        });
        homePageFoundViewModel.recommendSongAu.observe(requireActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                au1=strings.get(0);
                au2=strings.get(1);
                au3=strings.get(2);
            }
        });


        return view;
    }

    private void initView(){
        wonderfulName1=view.findViewById(R.id.home_page_found_wonderful_name_1);
        wonderfulName2=view.findViewById(R.id.home_page_found_wonderful_name_2);
        wonderfulName3=view.findViewById(R.id.home_page_found_wonderful_name_3);

        wonderfulPhoto1=view.findViewById(R.id.wonderful_playlist_1);
        wonderfulPhoto1.setOnClickListener(this);
        wonderfulPhoto2=view.findViewById(R.id.wonderful_playlist_2);
        wonderfulPhoto2.setOnClickListener(this);
        wonderfulPhoto3=view.findViewById(R.id.wonderful_playlist_3);
        wonderfulPhoto3.setOnClickListener(this);

        wonderfulPlaylistIds=new ArrayList<>();


        recommendPlaylist=view.findViewById(R.id.rv_recommend_playlist);

        recommendSongName1=view.findViewById(R.id.found_recommend_song_name_1);
        recommendSongName2=view.findViewById(R.id.found_recommend_song_name_2);
        recommendSongName3=view.findViewById(R.id.found_recommend_song_name_3);

        recommendSongPhoto1=view.findViewById(R.id.found_recommend_song_photo_1);
        recommendSongPhoto2=view.findViewById(R.id.found_recommend_song_photo_2);
        recommendSongPhoto3=view.findViewById(R.id.found_recommend_song_photo_3);

        recommendSong1=view.findViewById(R.id.home_page_found_song_1);
        recommendSong1.setOnClickListener(this);
        recommendSong2=view.findViewById(R.id.home_page_found_song_2);
        recommendSong2.setOnClickListener(this);
        recommendSong3=view.findViewById(R.id.home_page_found_song_3);
        recommendSong3.setOnClickListener(this);

        dayMusic=view.findViewById(R.id.day_music);
        dayMusic.setOnClickListener(this);
        if(getActivity()!=null) {
            homePageViewModel = ViewModelProviders.of((HomePageActivity) getActivity()).get(HomePageViewModel.class);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.day_music:{

                Intent intent=new Intent(getActivity(), PlaylistSongActivity.class);
                startActivity(intent);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        playlistSongActivity=PlaylistSongActivity.PLAYLIST_ACTIVITY;
                        PlaylistSongViewModel playlistSongViewModel=ViewModelProviders.of(playlistSongActivity).get(PlaylistSongViewModel.class);
                        playlistSongViewModel.getPlaylistSong(PageFound.DAY_RECOMMEND_SONGS);
                    }
                }).start();

            }break;
            case R.id.wonderful_playlist_1:{
                Intent intent=new Intent(getActivity(), PlaylistSongActivity.class);
                startActivity(intent);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        playlistSongActivity=PlaylistSongActivity.PLAYLIST_ACTIVITY;
                        PlaylistSongViewModel playlistSongViewModel=ViewModelProviders.of(playlistSongActivity).get(PlaylistSongViewModel.class);
                        playlistSongViewModel.getPlaylistSong(wonderfulPlaylistIds.get(0));
                    }
                }).start();
            }break;
            case R.id.wonderful_playlist_2:{
                Intent intent=new Intent(getActivity(), PlaylistSongActivity.class);
                startActivity(intent);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        playlistSongActivity=PlaylistSongActivity.PLAYLIST_ACTIVITY;
                        PlaylistSongViewModel playlistSongViewModel=ViewModelProviders.of(playlistSongActivity).get(PlaylistSongViewModel.class);
                        playlistSongViewModel.getPlaylistSong(wonderfulPlaylistIds.get(1));
                    }
                }).start();

            }break;
            case R.id.wonderful_playlist_3:{

                Intent intent=new Intent(getActivity(), PlaylistSongActivity.class);
                startActivity(intent);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        playlistSongActivity=PlaylistSongActivity.PLAYLIST_ACTIVITY;
                        PlaylistSongViewModel playlistSongViewModel=ViewModelProviders.of(playlistSongActivity).get(PlaylistSongViewModel.class);
                        playlistSongViewModel.getPlaylistSong(wonderfulPlaylistIds.get(2));
                    }
                }).start();

            }break;
            case R.id.home_page_found_song_1:{

                List<String> list=new ArrayList<>();
                list.add(photo1);
                list.add(name1);
                list.add(au1);
                homePageViewModel.setSongData(list);

            }break;
            case R.id.home_page_found_song_2:{
                List<String> list=new ArrayList<>();
                list.add(photo2);
                list.add(name2);
                list.add(au2);
                homePageViewModel.setSongData(list);

            }break;
            case R.id.home_page_found_song_3:{
                List<String> list=new ArrayList<>();
                list.add(photo3);
                list.add(name3);
                list.add(au3);
                homePageViewModel.setSongData(list);

            }break;
            default:break;
        }
    }
}