package com.example.redrock.areaConverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.WonderfulRecommendPlaylistBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HomeFoundWonderfulAC3 {

    @TypeConverter
    public static WonderfulRecommendPlaylistBean.PlaylistsDTO.CreatorDTO.AvatarDetailDTO revert(String data) {

        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<WonderfulRecommendPlaylistBean.PlaylistsDTO.CreatorDTO.AvatarDetailDTO>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(WonderfulRecommendPlaylistBean.PlaylistsDTO.CreatorDTO.AvatarDetailDTO data) {

        return new Gson().toJson(data);
    }

}
