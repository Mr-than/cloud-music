package com.example.redrock.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.redrock.bean.*;

import java.util.List;

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
