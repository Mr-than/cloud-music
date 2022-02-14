package com.example.redrock.areaConverters;

import androidx.room.TypeConverter;
import com.example.redrock.bean.CreatePlaylistBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class CreatePB1 {
    @TypeConverter
    public static List<CreatePlaylistBean.PlaylistDTO> revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<List<CreatePlaylistBean.PlaylistDTO>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(List<CreatePlaylistBean.PlaylistDTO> data){

        return new Gson().toJson(data);
    }

}
