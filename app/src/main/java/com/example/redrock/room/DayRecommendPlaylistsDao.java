package com.example.redrock.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.redrock.bean.DayRecommendPlaylistsBean;

import java.util.List;

/**
 *   description:日推歌单的Dao
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/5
 */

@Dao
public interface DayRecommendPlaylistsDao {

    @Insert
    public void addData(DayRecommendPlaylistsBean bean);

    @Query("SELECT * FROM DayRecommendPlaylistsBean")
    public List<DayRecommendPlaylistsBean> load();

    @Update
    public void update(DayRecommendPlaylistsBean bean);

    @Query("DELETE FROM DayRecommendPlaylistsBean")
    public void nukeTable();


}
