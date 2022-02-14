package com.example.redrock.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.redrock.bean.CreatePlaylistBean;
import com.example.redrock.bean.LoginBean;

import java.util.List;

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

    @Query("SELECT cookie FROM Loginbean")
    public String getCookie();


}
