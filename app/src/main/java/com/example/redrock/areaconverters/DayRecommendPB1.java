package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;
import com.example.redrock.bean.DayRecommendPlaylistsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class DayRecommendPB1 {

    @TypeConverter
    public static List<DayRecommendPlaylistsBean.RecommendDTO> revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<List<DayRecommendPlaylistsBean.RecommendDTO>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(List<DayRecommendPlaylistsBean.RecommendDTO> data){

        return new Gson().toJson(data);
    }

}
