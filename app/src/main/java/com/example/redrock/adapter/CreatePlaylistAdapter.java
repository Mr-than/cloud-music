package com.example.redrock.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.redrock.R;
import com.example.redrock.activity.HomePageActivity;
import com.example.redrock.activity.PlaylistSongActivity;
import com.example.redrock.base.APP;
import com.example.redrock.bean.CreatePlaylist;
import com.example.redrock.viewModel.PlaylistSongViewModel;

import java.util.List;
import java.util.Objects;

public class CreatePlaylistAdapter extends RecyclerView.Adapter<CreatePlaylistAdapter.ItemHolder> {

    private List<CreatePlaylist> list;
    private View view;
    private PlaylistSongActivity playlistSongActivity;
    private PlaylistSongViewModel playlistSongViewModel;
    private HomePageActivity homePageActivity;

    public CreatePlaylistAdapter(List<CreatePlaylist> list){
        this.list=list;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=LayoutInflater.from(parent.getContext()).inflate(R.layout.create_playlists_item, parent, false);
        ItemHolder holder=new ItemHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                CreatePlaylist createPlaylist=list.get(position);
                homePageActivity=HomePageActivity.HOME_PAGE_ACTIVITY;

                Intent intent=new Intent(homePageActivity,PlaylistSongActivity.class);
                homePageActivity.startActivity(intent);

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        playlistSongActivity=PlaylistSongActivity.PLAYLIST_ACTIVITY;

                        playlistSongViewModel= ViewModelProviders.of(playlistSongActivity).get(PlaylistSongViewModel.class);
                        playlistSongViewModel.getPlaylistSong(createPlaylist.getId());
                    }
                }).start();

            }
        });



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        CreatePlaylist createPlaylist=list.get(position);

        Glide.with(APP.getContext()).load(createPlaylist.getPicture()).transform(new RoundedCorners(10)).into(holder.picture);

        holder.name.setText(createPlaylist.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            picture=itemView.findViewById(R.id.playlist_picture);
            name=itemView.findViewById(R.id.playlist_name);
        }
    }


    private class DiffCallback extends DiffUtil.Callback{
        private List<CreatePlaylist> newList,oldList;
        public DiffCallback(List<CreatePlaylist> newList,List<CreatePlaylist> oldList){
            this.newList=newList;
            this.oldList=oldList;
        }

        @Override
        public int getOldListSize() {
            return this.oldList.size();
        }

        @Override
        public int getNewListSize() {
            return this.newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            CreatePlaylist newData=newList.get(newItemPosition);
            CreatePlaylist oldData=oldList.get(oldItemPosition);
            return Objects.equals(newData,oldData);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getName().equals(newList.get(newItemPosition).getName())&&oldList.get(oldItemPosition).getPicture()==newList.get(newItemPosition).getPicture();
        }

    }


    public void updateData(List<CreatePlaylist> newData){
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallback(list, newData));
        list.clear();
        list.addAll(newData);
        result.dispatchUpdatesTo(this);
    }
}