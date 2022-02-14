package com.example.redrock.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.redrock.R;
import com.example.redrock.activity.CommentsActivity;
import com.example.redrock.activity.LyricsActivity;
import com.example.redrock.bean.CommendBean;

import java.util.List;

public class CommendAdapter extends RecyclerView.Adapter<CommendAdapter.CommendHolder>{

    private List<CommendBean> list;
    private View view;
    private CommentsActivity commentsActivity;
    private EditText editText;


    public CommendAdapter(List<CommendBean> list, CommentsActivity commentsActivity, EditText editText) {
        this.list = list;
        this.editText=editText;
        this.commentsActivity = commentsActivity;
    }


    @NonNull
    @Override
    public CommendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.commend_item,parent,false);
        CommendHolder holder= new CommendHolder(view);

        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position=holder.getAdapterPosition();
                CommendBean commendBean=list.get(position);

                editText.requestFocus();
                editText.setFocusableInTouchMode(true);
                InputMethodManager imm = (InputMethodManager) commentsActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, 0);

                editText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    boolean isRep=true;
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();

                        editText.getWindowVisibleDisplayFrame(r);
                        int screenHeight = editText.getRootView().getHeight();
                        int heightDifference = screenHeight - (r.bottom);
                        if (heightDifference > 200) {
                            //软键盘显
                            if(isRep) {
                                editText.setHint("回复：" + commendBean.getName());
                                commentsActivity.t="2";
                                commentsActivity.commendId=commendBean.getCommendId();
                            }

                        } else {
                            //软键盘隐藏
                            editText.setHint("随乐而起，有感而发");
                            commentsActivity.t="1";
                            commentsActivity.commendId="";
                            isRep=false;
                        }
                    }
                });




            }
        });



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommendHolder holder, int position) {

        CommendBean commendBean=list.get(position);
        holder.name.setText(commendBean.getName());
        holder.like.setText(commendBean.getLike());
        holder.content.setText(commendBean.getContent());
        holder.data.setText(commendBean.getData());
        Glide.with(commentsActivity).load(commendBean.getPhoto()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CommendHolder extends RecyclerView.ViewHolder {

        private ImageView photo;
        private TextView name,data,content,like;


        public CommendHolder(@NonNull View itemView) {
            super(itemView);

            photo=itemView.findViewById(R.id.commend_item_pr_photo);
            name=itemView.findViewById(R.id.commend_item_pr_name);
            data=itemView.findViewById(R.id.commend_item_commend_data);
            content=itemView.findViewById(R.id.commend_item_commend);
            like=itemView.findViewById(R.id.commend_item_like);

        }
    }


}
