package com.example.redrock.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redrock.R;
import com.example.redrock.activity.HomePageActivity;
import com.example.redrock.activity.LyricsActivity;
import com.example.redrock.activity.PlaylistSongActivity;
import com.example.redrock.bean.PlaylistSongs;
import com.example.redrock.service.PlayMusicService;
import com.example.redrock.viewModel.HomePageViewModel;
import com.example.redrock.viewModel.LyricsActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class PlaylistSongAdapter extends RecyclerView.Adapter<PlaylistSongAdapter.SongViewHolder> {

    private List<PlaylistSongs> list;
    private View view;
    private HomePageActivity homePageActivity;
    private HomePageViewModel homePageViewModel;
    private boolean isService=false;
    private boolean isBind=false;
    private LyricsActivity lyricsActivity;
    private LyricsActivityViewModel lyricsActivityViewModel;
    private PlayMusicService.PlaySongBinder playSongBinder;
    private ServiceConnection connection;



    public PlaylistSongAdapter(List<PlaylistSongs> list, HomePageActivity homePageActivity) {
        this.list = list;
        this.homePageActivity =homePageActivity;
        homePageViewModel= ViewModelProviders.of(homePageActivity).get(HomePageViewModel.class);



            connection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    if(homePageViewModel.serviceConnect.getValue()!=null){
                        playSongBinder=homePageViewModel.serviceConnect.getValue();
                    }else {
                        playSongBinder = (PlayMusicService.PlaySongBinder) iBinder;
                    }

                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {

                }
            };

    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_song_item,parent,false);
        SongViewHolder holder=new SongViewHolder(view);
        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                PlaylistSongs songs=list.get(position);

                homePageActivity.setPlay();
                List<String> list=new ArrayList<>();
                list.add(songs.getPhoto());
                list.add(songs.getName());
                list.add(songs.getAuthor());
                list.add(songs.getId());

                homePageViewModel.setSongData(list);

                homePageViewModel.setPlay();



                if(!isBind) {
                    homePageActivity.bindService(new Intent(homePageActivity, PlayMusicService.class), connection, Context.BIND_AUTO_CREATE);
                    isBind=true;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        playSongBinder.prepare(songs.getId());
                        homePageViewModel.setServiceBinder(playSongBinder);
                    }
                }).start();

                    Intent intent=new Intent(PlaylistSongActivity.PLAYLIST_ACTIVITY, LyricsActivity.class);
                    PlaylistSongActivity.PLAYLIST_ACTIVITY.startActivity(intent);


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                                    lyricsActivity = LyricsActivity.LYRICS_ACTIVITY;
                                    lyricsActivityViewModel = ViewModelProviders.of(lyricsActivity).get(LyricsActivityViewModel.class);
                                    lyricsActivityViewModel.getLyrics(songs.getId());

                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    lyricsActivityViewModel.setServiceBinder(playSongBinder);
                                    lyricsActivityViewModel.setName(songs.getName());
                                    lyricsActivityViewModel.setMusicPhoto(songs.getPhoto());
                                }

                    }).start();

           }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {

        PlaylistSongs songs=list.get(position);
        holder.name.setText(songs.getName());
        holder.album.setText(songs.getAlbum());
        holder.author.setText(songs.getAuthor());
        holder.num.setText(String.valueOf(songs.getNum()));



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class SongViewHolder extends RecyclerView.ViewHolder {
        private TextView num,name,author,album;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            num=itemView.findViewById(R.id.playlist_song_item_nu);
            name=itemView.findViewById(R.id.playlist_song_item_name);
            author=itemView.findViewById(R.id.playlist_song_item_au);
            album=itemView.findViewById(R.id.playlist_song_item_album);
        }
    }



}
