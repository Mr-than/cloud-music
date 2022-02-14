package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.LoginBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PlaylistSB2 {
    @TypeConverter
    public static LoginBean.ProfileDTO revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<LoginBean.ProfileDTO>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(LoginBean.ProfileDTO data){

        return new Gson().toJson(data);
    }
}
