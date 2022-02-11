package com.example.redrock.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *   description:手机国家码的bean
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/1/29
 */

@Entity
public class CountryAndRegion {
    private String countryName;
    private String countryCode;
@PrimaryKey(autoGenerate = true)
long id=0;


    public CountryAndRegion(String countryName,String countryCode){
        this.countryCode=countryCode;
        this.countryName=countryName;
    }

    public String getCountryName() {
        return countryName;
    }
    public String getCountryCode() {
        return countryCode;
    }
}