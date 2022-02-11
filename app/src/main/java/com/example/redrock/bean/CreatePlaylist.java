package com.example.redrock.bean;


public class CreatePlaylist {
    private String name;
    private String picture;
    private String id;

    public CreatePlaylist(String name,String picture,String id){
        this.name=name;
        this.picture=picture;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }
}