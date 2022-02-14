package com.example.redrock.fragment;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.redrock.R;
import com.example.redrock.activity.HomePageActivity;
import com.example.redrock.adapter.CreatePlaylistAdapter;
import com.example.redrock.base.BaseActivity;
import com.example.redrock.bean.CreatePlaylist;
import com.example.redrock.viewModel.HomePageFoundViewModel;
import com.example.redrock.viewModel.HomePageMyViewModel;

import java.util.ArrayList;
import java.util.List;

public class PageMy extends Fragment {

    private View view;
    private RecyclerView createPlaylistRv;
    private HomePageMyViewModel homePageMyViewModel;
    private ImageView headPortrait;
    private TextView userName;
    private CardView cardView;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.page_my_fragment,container,false);
        init();

        if(sp.getString("refresh_2","").equals("refresh")){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    homePageMyViewModel.getPersonalPlaylistFromIn();
                    editor.putString("refresh","");
                    editor.apply();
                }
            }).start();

        }


        homePageMyViewModel.getUserInformationData();
        homePageMyViewModel.headPortrait.observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Glide.with(PageMy.this).load(s).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(headPortrait);
            }
        });
        homePageMyViewModel.userName.observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                userName.setText(s);
            }
        });

        homePageMyViewModel.getPersonalPlaylist();
        homePageMyViewModel.userData.observe(requireActivity(), new Observer<List<CreatePlaylist>>() {
            @Override
            public void onChanged(List<CreatePlaylist> list) {

                LinearLayoutManager manager=new LinearLayoutManager(getActivity());
                CreatePlaylistAdapter adapter=new CreatePlaylistAdapter(list);
                createPlaylistRv.setLayoutManager(manager);
                createPlaylistRv.setAdapter(adapter);

            }
        });

        return view;
    }

    private void init(){

        sp=getActivity().getSharedPreferences("isMyRefresh",BaseActivity.MODE_PRIVATE);
        editor=sp.edit();

        createPlaylistRv=view.findViewById(R.id.create_playlist_rv);
        homePageMyViewModel= ViewModelProviders.of(this).get(HomePageMyViewModel.class);
        homePageMyViewModel.setSp(getActivity().getSharedPreferences("my", BaseActivity.MODE_PRIVATE));

        homePageMyViewModel.setHomePageViewModel((HomePageActivity) getActivity());

        headPortrait=view.findViewById(R.id.my_user_head_portrait);
        userName=view.findViewById(R.id.my_user_name);

        cardView=view.findViewById(R.id.Head_portrait_cardView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardView.setClipToOutline(false);
        }




    }
}