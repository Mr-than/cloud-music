package com.example.redrock.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.redrock.R;
import com.example.redrock.activity.HomePageActivity;
import com.example.redrock.activity.PlaylistSongActivity;
import com.example.redrock.base.APP;
import com.example.redrock.bean.DayRecommendBean;
import com.example.redrock.viewmodel.PlaylistSongViewModel;

import java.util.List;

/**
 *   description:日推歌单
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/7
 */

public class DayRecommendPlaylistAdapter extends RecyclerView.Adapter<DayRecommendPlaylistAdapter.PlaylistViewHolder> {

    private List<DayRecommendBean> list;
    private PlaylistSongActivity playlistSongActivity;
    private PlaylistSongViewModel playlistSongViewModel;
    private View view;

    public DayRecommendPlaylistAdapter(List<DayRecommendBean> list){
        this.list=list;
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_playlist_item,parent,false);
        PlaylistViewHolder viewHolder=new PlaylistViewHolder(view);
        viewHolder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePageActivity.HOME_PAGE_ACTIVITY,PlaylistSongActivity.class);
                HomePageActivity.HOME_PAGE_ACTIVITY.startActivity(intent);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int position=viewHolder.getAdapterPosition();
                        playlistSongActivity=PlaylistSongActivity.PLAYLIST_ACTIVITY;
                        playlistSongViewModel= ViewModelProviders.of(playlistSongActivity).get(PlaylistSongViewModel.class);
                        playlistSongViewModel.getPlaylistSong(list.get(position).getId());
                    }
                }).start();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        DayRecommendBean dayRecommendBean=list.get(position);
        holder.name.setText(dayRecommendBean.getName());
        Glide.with(APP.getContext()).load(dayRecommendBean.getPhoto()).transform(new RoundedCorners(25)).into(holder.photo);
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class PlaylistViewHolder extends RecyclerView.ViewHolder {
        private ImageView photo;
        private TextView name;

        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            photo=itemView.findViewById(R.id.home_page_found_recommend_photo);
            name=itemView.findViewById(R.id.home_page_found_recommend_name);
        }
    }


}
