package com.example.redrock.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.redrock.bean.*;

import java.util.List;

@Dao
public interface AccountInformationDao {

    @Insert
    public void addData(AccountInformationBean bean);

    @Query("SELECT * FROM AccountInformationBean")
    public List<AccountInformationBean> load();

    @Update
    public void update(AccountInformationBean bean);


}