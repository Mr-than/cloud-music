package com.example.redrock.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redrock.R;
import com.example.redrock.bean.LyricsBean;

import java.util.List;

public class LyricsAdapter extends RecyclerView.Adapter<LyricsAdapter.ViewHolder>{

    private List<LyricsBean> list;
    private View view;
    public LyricsAdapter(List<LyricsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lyrics_item,parent,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LyricsBean bean=list.get(position);
        holder.lyrics1.setText(bean.getLyrics1());
        holder.lyrics2.setText(bean.getLyrics2());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lyrics1,lyrics2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lyrics1=itemView.findViewById(R.id.lyrics_item_1);
            lyrics2=itemView.findViewById(R.id.lyrics_item_2);
        }
    }

}
