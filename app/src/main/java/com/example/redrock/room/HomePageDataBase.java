package com.example.redrock.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.redrock.areaConverters.*;
import com.example.redrock.bean.*;

@Database(version = 1,entities = {WonderfulRecommendPlaylistBean.class,AccountInformationBean.class,CreatePlaylistBean.class,
        DayRecommendPlaylistsBean.class,DayRecommendSongsBean.class},exportSchema = false)
@TypeConverters({HomeFoundWonderfulAC1.class, HomeFoundWonderfulAC2.class,HomeFoundWonderfulAC3.class,AccountIB1.class,AccountIB2.class,AccountIB3.class,
CreatePB1.class,CreatePB2.class,CreatePB3.class,DayRecommendPB1.class,DayRecommendPB2.class,DayRecommendS1.class,DayRecommendS2.class,DayRecommendS3.class
        ,DayRecommendS4.class,DayRecommendS5.class,DayRecommendS6.class,DayRecommendS7.class,DayRecommendS8.class,DayRecommendS9.class
        ,DayRecommendSX.class,DayRecommendSXI.class,Objects.class})
public abstract class HomePageDataBase extends RoomDatabase {

    public abstract PersonalPlaylistDao getPersonalPlaylistDao();

    public abstract AccountInformationDao getAccountInformationDao();

    public abstract WonderfulPlaylistDao wonderfulPlaylistDao();

    public abstract DayRecommendPlaylistsDao dayRecommendPlaylistsDao();

    public abstract DayRecommendSongsDao dayRecommendSongsDao();

    public abstract PlaylistSongDao playlistSongDao();



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