package com.example.redrock.bean;

/**
 *   description:给adapter的私人歌单bean
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

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