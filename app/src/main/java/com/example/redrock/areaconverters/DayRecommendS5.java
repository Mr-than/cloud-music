package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.DayRecommendSongsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *   description:日推歌曲的转换器
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class DayRecommendS5 {
    @TypeConverter
    public static DayRecommendSongsBean.DataDTO.DailySongsDTO.AlDTO revert(String data) {

        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<DayRecommendSongsBean.DataDTO.DailySongsDTO.AlDTO>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(DayRecommendSongsBean.DataDTO.DailySongsDTO.AlDTO data) {

        return new Gson().toJson(data);
    }
}
