package com.example.redrock.areaConverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.LoginBean;
import com.example.redrock.bean.PlaylistSongsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class PlaylistSB3 {
    @TypeConverter
    public static List<LoginBean.BindingsDTO> revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<List<LoginBean.BindingsDTO>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(List<LoginBean.BindingsDTO> data){

        return new Gson().toJson(data);
    }
}
