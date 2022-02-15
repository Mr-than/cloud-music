package com.example.redrock.bean;

/**
 *   description:给adapter用的歌单bean
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class PlaylistSongs {
    private int num;
    private String name;
    private String album;
    private String author;
    private String photo;
    private String id;
    private String playlistPhoto;
    private String playlistName;

    public PlaylistSongs(int num, String name, String album, String author,String photo,String playlistPhoto,String playlistName,String id) {
        this.num = num;
        this.id=id;
        this.name = name;
        this.album = album;
        this.author = author;
        this.photo=photo;
        this.playlistName=playlistName;
        this.playlistPhoto=playlistPhoto;
    }

    public String getId() {
        return id;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getPlaylistPhoto() {
        return playlistPhoto;
    }

    public int getNum() {
        return num;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public String getAuthor() {
        return author;
    }
}
