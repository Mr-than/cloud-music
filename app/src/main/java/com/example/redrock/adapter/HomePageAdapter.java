package com.example.redrock.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class HomePageAdapter extends FragmentStateAdapter {

    private List<Fragment> list;


    public HomePageAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragments) {
        super(fragmentActivity);
        this.list=fragments;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
