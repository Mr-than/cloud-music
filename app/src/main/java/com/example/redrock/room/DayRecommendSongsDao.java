package com.example.redrock.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.redrock.bean.*;

import java.util.List;

/**
 *   description:日推歌曲的Dao
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/5
 */

@Dao
public interface DayRecommendSongsDao {

    @Insert
    public void addData(DayRecommendSongsBean bean);

    @Query("SELECT * FROM DayRecommendSongsBean")
    public List<DayRecommendSongsBean> load();

    @Update
    public void update(DayRecommendSongsBean bean);

    @Query("DELETE FROM DayRecommendSongsBean")
    public void nukeTable();

}
