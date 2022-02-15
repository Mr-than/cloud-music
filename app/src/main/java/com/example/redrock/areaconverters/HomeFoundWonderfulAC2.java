package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 *   description:精彩推荐歌单的转换器
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class HomeFoundWonderfulAC2 {
    @TypeConverter
    public static List<String> revert(String data) {

        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<List<String>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(List<String> data) {

        return new Gson().toJson(data);
    }
}
