package com.example.redrock.bean;

/**
 *   description:给adapter的日推bean
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

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
