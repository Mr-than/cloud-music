package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.LoginBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 *   description:登录信息转换器
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class LoginB3 {
    @TypeConverter
    public static List<LoginBean.BindingsDTO> revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<List<LoginBean.BindingsDTO>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(List<LoginBean.BindingsDTO> data){

        return new Gson().toJson(data);
    }
}
