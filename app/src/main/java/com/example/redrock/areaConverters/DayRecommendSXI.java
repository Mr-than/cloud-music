package com.example.redrock.areaConverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.DayRecommendSongsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class DayRecommendSXI {
    @TypeConverter
    public static List<DayRecommendSongsBean.DataDTO.DailySongsDTO.PrivilegeDTO.ChargeInfoListDTO> revert(String data) {

        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<List<DayRecommendSongsBean.DataDTO.DailySongsDTO.PrivilegeDTO.ChargeInfoListDTO>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(List<DayRecommendSongsBean.DataDTO.DailySongsDTO.PrivilegeDTO.ChargeInfoListDTO> data) {

        return new Gson().toJson(data);
    }
}
