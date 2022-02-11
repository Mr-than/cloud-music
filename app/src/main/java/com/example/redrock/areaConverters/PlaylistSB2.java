package com.example.redrock.AreaConverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.AccountInformationBean;
import com.example.redrock.bean.PlaylistSongsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class PlaylistSB2 {
    @TypeConverter
    public static List<PlaylistSongsBean.PlaylistDTO.TracksDTO> revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<List<PlaylistSongsBean.PlaylistDTO.TracksDTO>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(List<PlaylistSongsBean.PlaylistDTO.TracksDTO> data){

        return new Gson().toJson(data);
    }
}
