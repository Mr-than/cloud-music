package com.example.redrock.AreaConverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.AccountInformationBean;
import com.example.redrock.bean.PlaylistSongsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PlaylistSBX {
    @TypeConverter
    public static PlaylistSongsBean.PlaylistDTO.TracksDTO.LDTO revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<PlaylistSongsBean.PlaylistDTO.TracksDTO.LDTO>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(PlaylistSongsBean.PlaylistDTO.TracksDTO.LDTO data){

        return new Gson().toJson(data);
    }
}
