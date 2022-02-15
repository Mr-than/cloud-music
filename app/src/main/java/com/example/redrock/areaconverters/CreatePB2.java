package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 *   description:私人歌单的转换器，用于数据库存储
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class CreatePB2 {
    @TypeConverter
    public static List<?> revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<List<?>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(List<?> data){

        return new Gson().toJson(data);
    }

}
