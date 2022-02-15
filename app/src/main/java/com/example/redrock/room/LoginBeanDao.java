package com.example.redrock.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.redrock.bean.CreatePlaylistBean;
import com.example.redrock.bean.LoginBean;

import java.util.List;

/**
 *   description:登录数据的Dao
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/5
 */

@Dao
public interface LoginBeanDao {

    @Insert
    public void addData(LoginBean bean);


    @Query("SELECT * FROM LoginBean")
    public List<LoginBean> load();


    @Update
    public int update(LoginBean bean);

    @Query("UPDATE Loginbean SET cookie=:cookie")
    public int upC(String cookie);

    @Query("SELECT cookie FROM LoginBean")
    public String getCookie();

    @Query("DELETE FROM LoginBean")
    public void nukeTable();


}
