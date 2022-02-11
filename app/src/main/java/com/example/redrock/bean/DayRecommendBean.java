package com.example.redrock.bean;

public class DayRecommendBean {
    private String photo;
    private String name;
    private String id;

    public DayRecommendBean(String photo, String name,String id) {
        this.photo = photo;
        this.name = name;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }
}
