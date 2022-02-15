package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *   description:object的转换器
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class Objects {
    @TypeConverter
    public static Object revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<Object>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(Object data){

        return new Gson().toJson(data);
    }

}
