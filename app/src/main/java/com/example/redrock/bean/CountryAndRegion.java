package com.example.redrock.bean;

public class CountryAndRegion {
    private String countryName;
    private String countryCode;

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
