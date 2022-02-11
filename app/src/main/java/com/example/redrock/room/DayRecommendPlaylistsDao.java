package com.example.redrock.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.redrock.bean.DayRecommendPlaylistsBean;

import java.util.List;

@Dao
public interface DayRecommendPlaylistsDao {

    @Insert
    public void addData(DayRecommendPlaylistsBean bean);

    @Query("SELECT * FROM DayRecommendPlaylistsBean")
    public List<DayRecommendPlaylistsBean> load();

    @Update
    public void update(DayRecommendPlaylistsBean bean);


}
