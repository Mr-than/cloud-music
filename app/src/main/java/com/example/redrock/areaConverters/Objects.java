package com.example.redrock.AreaConverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.AccountInformationBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
