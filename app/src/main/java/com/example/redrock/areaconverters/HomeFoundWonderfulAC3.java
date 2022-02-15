package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.WonderfulRecommendPlaylistBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *   description:精彩推荐歌单的转换器
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

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
