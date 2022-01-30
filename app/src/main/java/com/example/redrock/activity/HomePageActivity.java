package com.example.redrock.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TableLayout;
import com.example.redrock.R;
import com.example.redrock.adapter.HomePageAdapter;
import com.example.redrock.fragment.PageFound;
import com.example.redrock.fragment.PageMy;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private ViewPager2 homePage;
    private TabLayout homeTy;
    private List<String> tab;
    private List<Fragment> page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        init();


        homeTy.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index=tab.getPosition();
                if(index==0){
                    tab.setIcon(R.drawable.found_selector);
                }else if(index==1){
                    tab.setIcon(R.drawable.my_selector);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                int index=tab.getPosition();
                if(index==0){
                    tab.setIcon(R.drawable.found_unselector);
                }else if(index==1){
                    tab.setIcon(R.drawable.my_unselector);
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        new TabLayoutMediator(homeTy,homePage, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(HomePageActivity.this.tab.get(position));

                if(position==0){
                 tab.setIcon(R.drawable.found_unselector);
                }else if(position==1){
                    tab.setIcon(R.drawable.my_unselector);
                }

            }
        }).attach();








    }
    private void init(){
        homePage=findViewById(R.id.home_page_vp);

        homeTy=findViewById(R.id.home_page_ty);

        tab=new ArrayList<>();
        tab.add("发现");
        tab.add("我的");

        page=new ArrayList<>();
        page.add(new PageFound());
        page.add(new PageMy());
        homePage.setAdapter(new HomePageAdapter(this,page));

    }




}