package com.example.redrock.AreaConverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.AccountInformationBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AccountIB1 {

    @TypeConverter
    public static AccountInformationBean.AccountDTO revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<AccountInformationBean.AccountDTO>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(AccountInformationBean.AccountDTO data){

        return new Gson().toJson(data);
    }


}
