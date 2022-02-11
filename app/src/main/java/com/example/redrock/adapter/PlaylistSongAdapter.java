package com.example.redrock.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redrock.R;
import com.example.redrock.activity.HomePageActivity;
import com.example.redrock.bean.PlaylistSongs;
import com.example.redrock.viewModel.HomePageViewModel;

import java.util.ArrayList;
import java.util.List;

public class PlaylistSongAdapter extends RecyclerView.Adapter<PlaylistSongAdapter.SongViewHolder> {

    private List<PlaylistSongs> list;
    private View view;
    private HomePageActivity homePageActivity;
    private HomePageViewModel homePageViewModel;

    public PlaylistSongAdapter(List<PlaylistSongs> list, HomePageActivity homePageActivity) {
        this.list = list;
        this.homePageActivity =homePageActivity;
        homePageViewModel= ViewModelProviders.of(homePageActivity).get(HomePageViewModel.class);
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
                homePageViewModel.setSongData(list);
                homePageViewModel.setPlay();
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
