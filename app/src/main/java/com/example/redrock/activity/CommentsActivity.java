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

public class CommentsActivity extends BaseActivity {

    private ImageView photo;
    private TextView name,sendCommend;
    private EditText editCommend;
    private RecyclerView commend;
    private CommendActivityViewModel commendActivityViewModel;
    public static CommentsActivity COMMEND_ACTIVITY;
    private String songId;
    public String t;
    public String commendId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        initView();


        commendActivityViewModel.commendContent.observe(this, new Observer<List<CommendBean>>() {
            @Override
            public void onChanged(List<CommendBean> commendBeans) {

                LinearLayoutManager manager=new LinearLayoutManager(CommentsActivity.this);
                CommendAdapter adapter=new CommendAdapter(commendBeans,CommentsActivity.this,editCommend);
                commend.setLayoutManager(manager);
                commend.setAdapter(adapter);

            }
        });

        commendActivityViewModel.songPhoto.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Glide.with(CommentsActivity.this).load(s).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(photo);
            }
        });

        commendActivityViewModel.songName.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                name.setText(s);
            }
        });

        commendActivityViewModel.songId.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                songId=s;
            }
        });

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

    private void initView() {
        COMMEND_ACTIVITY=this;
        commendId="";

        commendActivityViewModel= ViewModelProviders.of(this).get(CommendActivityViewModel.class);
        sendCommend=findViewById(R.id.send_commend_te);
        editCommend=findViewById(R.id.send_commend_edit);

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