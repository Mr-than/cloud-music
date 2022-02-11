package com.example.redrock.AreaConverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.DayRecommendSongsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

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
