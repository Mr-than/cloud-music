package com.example.redrock.AreaConverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.AccountInformationBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class AccountIB4 {
    @TypeConverter
    public static AccountInformationBean.ProfileDTO.ExpertsDTO revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<AccountInformationBean.ProfileDTO.ExpertsDTO>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(AccountInformationBean.ProfileDTO.ExpertsDTO data){

        return new Gson().toJson(data);
    }
}
