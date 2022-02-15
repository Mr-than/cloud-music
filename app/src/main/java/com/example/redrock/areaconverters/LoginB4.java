package com.example.redrock.areaconverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.LoginBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *   description:登录信息转换器
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class LoginB4 {
    @TypeConverter
    public static LoginBean.ProfileDTO.ExpertsDTO revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<LoginBean.ProfileDTO.ExpertsDTO>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(LoginBean.ProfileDTO.ExpertsDTO data){

        return new Gson().toJson(data);
    }
}
