package com.example.redrock.model;

import android.content.Context;
import android.util.DisplayMetrics;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/**
 *   description:这是用来控制歌词滑动的类，重写了smoothScrollToPosition用于控制滑动的速度，calculateDtToFit方法用于控制滑动的位置
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class CenterLayoutManager extends LinearLayoutManager {

    public CenterLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        super.smoothScrollToPosition(recyclerView, state, position);

        LinearSmoothScroller smoothScroller = new CenterSmoothScroller(recyclerView.getContext()) {
            // 返回：滑过1px时经历的时间(ms)。
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 150f / displayMetrics.densityDpi;

            }
        };



        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);



    }

    private static class CenterSmoothScroller extends LinearSmoothScroller {

        CenterSmoothScroller(Context context) {
            super(context);
        }

        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
        }
    }




}
