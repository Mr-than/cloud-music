package com.example.redrock.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.redrock.areaconverters.*;
import com.example.redrock.bean.*;

/**
 *   description:room的DataBase
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/5
 */

@Database(version = 1,entities = {WonderfulRecommendPlaylistBean.class,CreatePlaylistBean.class,
        DayRecommendPlaylistsBean.class,DayRecommendSongsBean.class,LoginBean.class},exportSchema = false)
@TypeConverters({HomeFoundWonderfulAC1.class, HomeFoundWonderfulAC2.class,HomeFoundWonderfulAC3.class,
CreatePB1.class,CreatePB2.class,CreatePB3.class,DayRecommendPB1.class,DayRecommendPB2.class,DayRecommendS1.class,DayRecommendS2.class,DayRecommendS3.class
        ,DayRecommendS4.class,DayRecommendS5.class,DayRecommendS6.class,DayRecommendS7.class,DayRecommendS8.class,DayRecommendS9.class
        ,DayRecommendSX.class,DayRecommendSXI.class,Objects.class, LoginB1.class, LoginB2.class, LoginB3.class, LoginB4.class})
public abstract class HomePageDataBase extends RoomDatabase {

    public abstract PersonalPlaylistDao getPersonalPlaylistDao();


    public abstract WonderfulPlaylistDao wonderfulPlaylistDao();

    public abstract DayRecommendPlaylistsDao dayRecommendPlaylistsDao();

    public abstract DayRecommendSongsDao dayRecommendSongsDao();



    public abstract LoginBeanDao loginBeanDao();



    private static final String DATABASE_NAME = "homePage_db";

    private static HomePageDataBase databaseInstance;

    public static synchronized HomePageDataBase getInstance(Context context)
    {
        if(databaseInstance == null)
        {
            databaseInstance = Room
                    .databaseBuilder(context.getApplicationContext(), HomePageDataBase.class, DATABASE_NAME)
                    .build();
        }
        return databaseInstance;
    }
}