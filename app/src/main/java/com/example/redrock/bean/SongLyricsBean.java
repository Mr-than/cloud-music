package com.example.redrock.bean;

/**
 *   description:歌词
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class SongLyricsBean {


    private boolean sgc;
    private boolean sfy;
    private boolean qfy;
    private TransUserDTO transUser;
    private LyricUserDTO lyricUser;
    private LrcDTO lrc;
    private KlyricDTO klyric;
    private TlyricDTO tlyric;
    private int code;

    public boolean isSgc() {
        return sgc;
    }

    public void setSgc(boolean sgc) {
        this.sgc = sgc;
    }

    public boolean isSfy() {
        return sfy;
    }

    public void setSfy(boolean sfy) {
        this.sfy = sfy;
    }

    public boolean isQfy() {
        return qfy;
    }

    public void setQfy(boolean qfy) {
        this.qfy = qfy;
    }

    public TransUserDTO getTransUser() {
        return transUser;
    }

    public void setTransUser(TransUserDTO transUser) {
        this.transUser = transUser;
    }

    public LyricUserDTO getLyricUser() {
        return lyricUser;
    }

    public void setLyricUser(LyricUserDTO lyricUser) {
        this.lyricUser = lyricUser;
    }

    public LrcDTO getLrc() {
        return lrc;
    }

    public void setLrc(LrcDTO lrc) {
        this.lrc = lrc;
    }

    public KlyricDTO getKlyric() {
        return klyric;
    }

    public void setKlyric(KlyricDTO klyric) {
        this.klyric = klyric;
    }

    public TlyricDTO getTlyric() {
        return tlyric;
    }

    public void setTlyric(TlyricDTO tlyric) {
        this.tlyric = tlyric;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class TransUserDTO {
        private String id;
        private int status;
        private int demand;
        private String userid;
        private String nickname;
        private String uptime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getDemand() {
            return demand;
        }

        public void setDemand(int demand) {
            this.demand = demand;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUptime() {
            return uptime;
        }

        public void setUptime(String uptime) {
            this.uptime = uptime;
        }
    }

    public static class LyricUserDTO {
        private String id;
        private int status;
        private int demand;
        private String userid;
        private String nickname;
        private String uptime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getDemand() {
            return demand;
        }

        public void setDemand(int demand) {
            this.demand = demand;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUptime() {
            return uptime;
        }

        public void setUptime(String uptime) {
            this.uptime = uptime;
        }
    }

    public static class LrcDTO {
        private int version;
        private String lyric;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getLyric() {
            return lyric;
        }

        public void setLyric(String lyric) {
            this.lyric = lyric;
        }
    }

    public static class KlyricDTO {
        private int version;
        private String lyric;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getLyric() {
            return lyric;
        }

        public void setLyric(String lyric) {
            this.lyric = lyric;
        }
    }

    public static class TlyricDTO {
        private int version;
        private String lyric;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getLyric() {
            return lyric;
        }

        public void setLyric(String lyric) {
            this.lyric = lyric;
        }
    }
}
