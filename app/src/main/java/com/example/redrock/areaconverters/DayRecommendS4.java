package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.DayRecommendSongsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 *   description:日推歌曲的转换器
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class DayRecommendS4 {
    @TypeConverter
    public static List<DayRecommendSongsBean.DataDTO.DailySongsDTO.ArDTO> revert(String data) {

        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<List<DayRecommendSongsBean.DataDTO.DailySongsDTO.ArDTO>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(List<DayRecommendSongsBean.DataDTO.DailySongsDTO.ArDTO> data) {

        return new Gson().toJson(data);
    }
}
