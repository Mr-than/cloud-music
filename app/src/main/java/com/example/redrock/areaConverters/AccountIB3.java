package com.example.redrock.AreaConverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.AccountInformationBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class AccountIB3 {
    @TypeConverter
    public static List<AccountInformationBean.BindingsDTO> revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<List<AccountInformationBean.BindingsDTO>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(List<AccountInformationBean.BindingsDTO> data){

        return new Gson().toJson(data);
    }

}
