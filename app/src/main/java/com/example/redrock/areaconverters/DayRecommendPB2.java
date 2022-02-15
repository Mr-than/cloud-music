package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;
import com.example.redrock.bean.DayRecommendPlaylistsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *   description:日推歌单的转换器
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class DayRecommendPB2 {

    @TypeConverter
    public static DayRecommendPlaylistsBean.RecommendDTO.CreatorDTO revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<DayRecommendPlaylistsBean.RecommendDTO.CreatorDTO>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(DayRecommendPlaylistsBean.RecommendDTO.CreatorDTO data){

        return new Gson().toJson(data);
    }
}
