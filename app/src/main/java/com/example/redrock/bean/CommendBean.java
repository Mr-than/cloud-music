package com.example.redrock.bean;

public class CommendBean {
    private String photo;
    private String name;
    private String data;
    private String content;
    private String like;
    private String commendId;

    public CommendBean(String photo, String name, String data, String content, String like, String commendId) {
        this.photo = photo;
        this.name = name;
        this.data = data;
        this.content = content;
        this.like = like;
        this.commendId = commendId;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public String getContent() {
        return content;
    }

    public String getLike() {
        return like;
    }

    public String getCommendId() {
        return commendId;
    }
}
