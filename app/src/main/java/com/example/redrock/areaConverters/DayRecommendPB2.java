package com.example.redrock.AreaConverters;

import androidx.room.TypeConverter;
import com.example.redrock.bean.DayRecommendPlaylistsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


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
