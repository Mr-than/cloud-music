package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.WonderfulRecommendPlaylistBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 *   description:精彩推荐歌单的转换器
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class HomeFoundWonderfulAC1 {

    @TypeConverter
    public static List<WonderfulRecommendPlaylistBean.PlaylistsDTO> revert(String data) {
        // 使用Gson方法把json格式的string转成List
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<List<WonderfulRecommendPlaylistBean.PlaylistsDTO>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(List<WonderfulRecommendPlaylistBean.PlaylistsDTO> data) {
        // 使用Gson方法把List转成json格式的string，便于我们用的解析
        return new Gson().toJson(data);
    }


}
