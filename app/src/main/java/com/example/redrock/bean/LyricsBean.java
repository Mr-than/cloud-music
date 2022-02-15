package com.example.redrock.bean;

/**
 *   description:给adapter用的歌词bean
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class LyricsBean {
    private String lyrics1;
    private String lyrics2;
    private String time;

    public LyricsBean(String lyrics1, String lyrics2,String time) {
        this.lyrics1 = lyrics1;
        this.lyrics2 = lyrics2;
        this.time=time;
    }


    public String getTime() {
        return time;
    }

    public String getLyrics1() {
        return lyrics1;
    }

    public String getLyrics2() {
        return lyrics2;
    }


}
