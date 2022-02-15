package com.example.redrock.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.redrock.bean.*;

import java.util.List;

/**
 *   description:个人歌单的Dao
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/5
 */

@Dao
public interface PersonalPlaylistDao {

    @Insert
    public void addData(CreatePlaylistBean bean);

    @Query("SELECT * FROM CreatePlaylistBean")
    public List<CreatePlaylistBean> load();

    @Update
    public void update(CreatePlaylistBean bean);

    @Query("DELETE FROM CreatePlaylistBean")
    public void nukeTable();

}