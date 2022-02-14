package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.DayRecommendSongsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DayRecommendSX {
    @TypeConverter
    public static DayRecommendSongsBean.DataDTO.DailySongsDTO.PrivilegeDTO.FreeTrialPrivilegeDTO revert(String data) {

        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<DayRecommendSongsBean.DataDTO.DailySongsDTO.PrivilegeDTO.FreeTrialPrivilegeDTO>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(DayRecommendSongsBean.DataDTO.DailySongsDTO.PrivilegeDTO.FreeTrialPrivilegeDTO data) {

        return new Gson().toJson(data);
    }
}
