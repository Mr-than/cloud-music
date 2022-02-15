package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;
import com.example.redrock.bean.CreatePlaylistBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *   description:私人歌单的转换器，用于数据库存储
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

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
