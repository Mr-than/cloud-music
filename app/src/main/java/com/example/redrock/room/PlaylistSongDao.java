package com.example.redrock.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.redrock.bean.PlaylistSongsBean;

import java.util.List;

@Dao
public interface PlaylistSongDao {
    @Insert
    public void addData(PlaylistSongsBean bean);

    @Query("SELECT * FROM PlaylistSongsBean")
    public List<PlaylistSongsBean> load();

    @Update
    public void update(PlaylistSongsBean bean);
}
