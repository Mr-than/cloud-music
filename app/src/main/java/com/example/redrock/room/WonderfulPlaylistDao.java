package com.example.redrock.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.redrock.bean.WonderfulRecommendPlaylistBean;

import java.util.List;

/**
 *   description:精彩歌单的Dao
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/5
 */

@Dao
public interface WonderfulPlaylistDao {

    @Insert
    public void addData(WonderfulRecommendPlaylistBean bean);

    @Query("SELECT * FROM WonderfulRecommendPlaylistBean")
    public List<WonderfulRecommendPlaylistBean> load();

    @Update
    public void update(WonderfulRecommendPlaylistBean bean);



}