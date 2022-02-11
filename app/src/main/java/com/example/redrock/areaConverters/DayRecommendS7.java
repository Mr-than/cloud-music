package com.example.redrock.AreaConverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.DayRecommendSongsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DayRecommendS7 {
    @TypeConverter
    public static DayRecommendSongsBean.DataDTO.DailySongsDTO.MDTO revert(String data) {

        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<DayRecommendSongsBean.DataDTO.DailySongsDTO.MDTO>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(DayRecommendSongsBean.DataDTO.DailySongsDTO.MDTO data) {

        return new Gson().toJson(data);
    }
}
