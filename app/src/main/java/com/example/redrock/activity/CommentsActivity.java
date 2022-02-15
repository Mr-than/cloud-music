package com.example.redrock.activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.redrock.R;
import com.example.redrock.adapter.CommendAdapter;
import com.example.redrock.base.BaseActivity;
import com.example.redrock.bean.CommendBean;
import com.example.redrock.viewmodel.CommendActivityViewModel;

import java.util.List;

/**
 *   description:显示歌曲热评和发送评论的活动
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/14
 */

public class CommentsActivity extends BaseActivity {

    //歌曲的头像
    private ImageView photo;
    //歌曲的名字，后者是发送评论的文字
    private TextView name,sendCommend;
    //编辑评论的edit
    private EditText editCommend;
    //评论的RV
    private RecyclerView commend;
    //这个活动的ViewModel
    private CommendActivityViewModel commendActivityViewModel;
    //这个活动本身，用于外界获取这个活动的引用
    public static CommentsActivity COMMEND_ACTIVITY;
    //歌曲的id
    private String songId;
    //评论资源的评论类型
    public String t;
    //回复评论的id
    public String commendId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        initView();

        //用于获取评论然后用下面的livedata观察发给RV
        commendActivityViewModel.commendContent.observe(this, new Observer<List<CommendBean>>() {
            @Override
            public void onChanged(List<CommendBean> commendBeans) {

                LinearLayoutManager manager=new LinearLayoutManager(CommentsActivity.this);
                CommendAdapter adapter=new CommendAdapter(commendBeans,CommentsActivity.this,editCommend);
                commend.setLayoutManager(manager);
                commend.setAdapter(adapter);

            }
        });
        //歌曲的头像观察者，用于设置右上角图片
        commendActivityViewModel.songPhoto.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Glide.with(CommentsActivity.this).load(s).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(photo);
            }
        });
        //歌曲名字观察者，用于设置右上角歌曲名字
        commendActivityViewModel.songName.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                name.setText(s);
            }
        });
        //歌曲id观察者，用于评论（评论需要资源id）
        commendActivityViewModel.songId.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                songId=s;
            }
        });
        //请求返回码观察者（用于发送评论是否成功判断）
        commendActivityViewModel.code.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!s.equals("200")) {
                    Toast.makeText(CommentsActivity.this, "评论失败", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(CommentsActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                    editCommend.setText("");
                    sendCommend.requestFocus();
                    sendCommend.setFocusableInTouchMode(true);
                    InputMethodManager imm = (InputMethodManager) CommentsActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(sendCommend, 0);
                }
            }
        });
    }
    //初始化控件和变量的方法
    private void initView() {
        COMMEND_ACTIVITY=this;
        commendId="";

        commendActivityViewModel= ViewModelProviders.of(this).get(CommendActivityViewModel.class);
        sendCommend=findViewById(R.id.send_commend_te);
        editCommend=findViewById(R.id.send_commend_edit);

        //这里给text监听是为了控制“发送”两个字的颜色
        editCommend.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                    if(editCommend.getText().toString().length()>0){
                        sendCommend.setTextColor(Color.parseColor("#FF000000"));
                    }else {
                        sendCommend.setTextColor(Color.parseColor("#CFCFCF"));
                    }
            }
        });
        //按下发送键监听
        sendCommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commend=editCommend.getText().toString();
                if(commend.length()>0){
                    if(editCommend.getHint().toString().equals("随乐而起，有感而发")){
                        commendId="";
                        t="1";
                    }

                    commendActivityViewModel.commend(commend,t,songId,commendId);
                }
            }
        });


        photo=findViewById(R.id.activity_commend_song_photo);
        name=findViewById(R.id.activity_commend_song_name);
        commend=findViewById(R.id.activity_commend_commend_rv);


    }



}