package com.example.redrock.AreaConverters;

import androidx.room.TypeConverter;

import com.example.redrock.bean.AccountInformationBean;
import com.example.redrock.bean.PlaylistSongsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PlaylistSBXI {
    @TypeConverter
    public static PlaylistSongsBean.PrivilegesDTO.FreeTrialPrivilegeDTO revert(String data){
        Gson gson=new Gson();
        try {
            return gson.fromJson(data,new TypeToken<PlaylistSongsBean.PrivilegesDTO.FreeTrialPrivilegeDTO>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(PlaylistSongsBean.PrivilegesDTO.FreeTrialPrivilegeDTO data){

        return new Gson().toJson(data);
    }
}
