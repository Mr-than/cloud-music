package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.LoginBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PlaylistSB1 {
    @TypeConverter
    public static LoginBean.AccountDTO revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<LoginBean.AccountDTO>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(LoginBean.AccountDTO data){

        return new Gson().toJson(data);
    }
}
