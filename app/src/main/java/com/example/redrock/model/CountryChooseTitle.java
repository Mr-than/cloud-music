package com.example.redrock.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 *   description:给手机登录选择国家码的rv设置一个悬浮标题
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/1/28
 */

public class CountryChooseTitle extends RecyclerView.ItemDecoration {
    //初始化画笔
    private Paint paint;
    //设置要预留给标题的高的值
    private int height;

    public CountryChooseTitle(){
        height=110;
        paint=new Paint();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //得到左右坐标
        int left =parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();

        for (int i=0;i<parent.getChildCount();i++){

            View view=parent.getChildAt(i);
            int index=parent.getChildAdapterPosition(view);
            if(index==0){
                int top=view.getTop()-16;
                int bottom=view.getTop();
                //背景色
                paint.setColor(Color.WHITE);
                c.drawRect(left,top,right,bottom,paint);
                //组名
                paint.setColor(Color.BLACK);
                paint.setTextSize(40);
                paint.setTextAlign(Paint.Align.LEFT);
                paint.setTypeface(Typeface.DEFAULT_BOLD);
                paint.setAntiAlias(true);
                c.drawText("常用",left+60,bottom-80,paint);
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        //这个方法用来执行标题悬浮置顶的效果，但是这里item很少，就不写了（独立写可能有点够呛，但是大概明白原理）
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position=parent.getChildAdapterPosition(view);
        if(position==0){
            outRect.top=height;
        }
    }

}
