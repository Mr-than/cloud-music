package com.example.redrock.areaConverters;

import androidx.room.TypeConverter;
import com.example.redrock.bean.CreatePlaylistBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CreatePB3 {
    @TypeConverter
    public static CreatePlaylistBean.PlaylistDTO.CreatorDTO revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<CreatePlaylistBean.PlaylistDTO.CreatorDTO>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(CreatePlaylistBean.PlaylistDTO.CreatorDTO data){

        return new Gson().toJson(data);
    }

}
