package com.example.redrock.bean;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 *   description:歌单歌曲bean
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

@Entity
public class PlaylistSongsBean {
    @PrimaryKey(autoGenerate = true)
    public long id=0;


    private int code;
    private Object relatedVideos;
    private PlaylistDTO playlist;
    private Object urls;
    private List<PrivilegesDTO> privileges;
    private Object sharedPrivilege;
    private Object resEntrance;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getRelatedVideos() {
        return relatedVideos;
    }

    public void setRelatedVideos(Object relatedVideos) {
        this.relatedVideos = relatedVideos;
    }

    public PlaylistDTO getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistDTO playlist) {
        this.playlist = playlist;
    }

    public Object getUrls() {
        return urls;
    }

    public void setUrls(Object urls) {
        this.urls = urls;
    }

    public List<PrivilegesDTO> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<PrivilegesDTO> privileges) {
        this.privileges = privileges;
    }

    public Object getSharedPrivilege() {
        return sharedPrivilege;
    }

    public void setSharedPrivilege(Object sharedPrivilege) {
        this.sharedPrivilege = sharedPrivilege;
    }

    public Object getResEntrance() {
        return resEntrance;
    }

    public void setResEntrance(Object resEntrance) {
        this.resEntrance = resEntrance;
    }

    public static class PlaylistDTO {
        private String id;
        private String name;
        private long coverImgId;
        private String coverImgUrl;
        private Object coverImgId_str;
        private int adType;
        private String userId;
        private String createTime;
        private int status;
        private boolean opRecommend;
        private boolean highQuality;
        private boolean newImported;
        private long updateTime;
        private int trackCount;
        private int specialType;
        private int privacy;
        private String trackUpdateTime;
        private String commentThreadId;
        private String playCount;
        private long trackNumberUpdateTime;
        private int subscribedCount;
        private int cloudTrackCount;
        private boolean ordered;
        private Object description;
        private List<?> tags;
        private Object updateFrequency;
        private String backgroundCoverId;
        private Object backgroundCoverUrl;
        private String titleImage;
        private Object titleImageUrl;
        private Object englishTitle;
        private Object officialPlaylistType;
        private List<?> subscribers;
        private boolean subscribed;
        private CreatorDTO creator;
        private List<TracksDTO> tracks;
        private Object videoIds;
        private Object videos;
        private List<TrackIdsDTO> trackIds;
        private int shareCount;
        private int commentCount;
        private Object remixVideo;
        private Object sharedUsers;
        private Object historySharedUsers;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getCoverImgId() {
            return coverImgId;
        }

        public void setCoverImgId(long coverImgId) {
            this.coverImgId = coverImgId;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public Object getCoverImgId_str() {
            return coverImgId_str;
        }

        public void setCoverImgId_str(Object coverImgId_str) {
            this.coverImgId_str = coverImgId_str;
        }

        public int getAdType() {
            return adType;
        }

        public void setAdType(int adType) {
            this.adType = adType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public boolean isOpRecommend() {
            return opRecommend;
        }

        public void setOpRecommend(boolean opRecommend) {
            this.opRecommend = opRecommend;
        }

        public boolean isHighQuality() {
            return highQuality;
        }

        public void setHighQuality(boolean highQuality) {
            this.highQuality = highQuality;
        }

        public boolean isNewImported() {
            return newImported;
        }

        public void setNewImported(boolean newImported) {
            this.newImported = newImported;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getTrackCount() {
            return trackCount;
        }

        public void setTrackCount(int trackCount) {
            this.trackCount = trackCount;
        }

        public int getSpecialType() {
            return specialType;
        }

        public void setSpecialType(int specialType) {
            this.specialType = specialType;
        }

        public int getPrivacy() {
            return privacy;
        }

        public void setPrivacy(int privacy) {
            this.privacy = privacy;
        }

        public String getTrackUpdateTime() {
            return trackUpdateTime;
        }

        public void setTrackUpdateTime(String trackUpdateTime) {
            this.trackUpdateTime = trackUpdateTime;
        }

        public String getCommentThreadId() {
            return commentThreadId;
        }

        public void setCommentThreadId(String commentThreadId) {
            this.commentThreadId = commentThreadId;
        }

        public String getPlayCount() {
            return playCount;
        }

        public void setPlayCount(String playCount) {
            this.playCount = playCount;
        }

        public long getTrackNumberUpdateTime() {
            return trackNumberUpdateTime;
        }

        public void setTrackNumberUpdateTime(long trackNumberUpdateTime) {
            this.trackNumberUpdateTime = trackNumberUpdateTime;
        }

        public int getSubscribedCount() {
            return subscribedCount;
        }

        public void setSubscribedCount(int subscribedCount) {
            this.subscribedCount = subscribedCount;
        }

        public int getCloudTrackCount() {
            return cloudTrackCount;
        }

        public void setCloudTrackCount(int cloudTrackCount) {
            this.cloudTrackCount = cloudTrackCount;
        }

        public boolean isOrdered() {
            return ordered;
        }

        public void setOrdered(boolean ordered) {
            this.ordered = ordered;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }

        public Object getUpdateFrequency() {
            return updateFrequency;
        }

        public void setUpdateFrequency(Object updateFrequency) {
            this.updateFrequency = updateFrequency;
        }

        public String getBackgroundCoverId() {
            return backgroundCoverId;
        }

        public void setBackgroundCoverId(String backgroundCoverId) {
            this.backgroundCoverId = backgroundCoverId;
        }

        public Object getBackgroundCoverUrl() {
            return backgroundCoverUrl;
        }

        public void setBackgroundCoverUrl(Object backgroundCoverUrl) {
            this.backgroundCoverUrl = backgroundCoverUrl;
        }

        public String getTitleImage() {
            return titleImage;
        }

        public void setTitleImage(String titleImage) {
            this.titleImage = titleImage;
        }

        public Object getTitleImageUrl() {
            return titleImageUrl;
        }

        public void setTitleImageUrl(Object titleImageUrl) {
            this.titleImageUrl = titleImageUrl;
        }

        public Object getEnglishTitle() {
            return englishTitle;
        }

        public void setEnglishTitle(Object englishTitle) {
            this.englishTitle = englishTitle;
        }

        public Object getOfficialPlaylistType() {
            return officialPlaylistType;
        }

        public void setOfficialPlaylistType(Object officialPlaylistType) {
            this.officialPlaylistType = officialPlaylistType;
        }

        public List<?> getSubscribers() {
            return subscribers;
        }

        public void setSubscribers(List<?> subscribers) {
            this.subscribers = subscribers;
        }

        public boolean isSubscribed() {
            return subscribed;
        }

        public void setSubscribed(boolean subscribed) {
            this.subscribed = subscribed;
        }

        public CreatorDTO getCreator() {
            return creator;
        }

        public void setCreator(CreatorDTO creator) {
            this.creator = creator;
        }

        public List<TracksDTO> getTracks() {
            return tracks;
        }

        public void setTracks(List<TracksDTO> tracks) {
            this.tracks = tracks;
        }

        public Object getVideoIds() {
            return videoIds;
        }

        public void setVideoIds(Object videoIds) {
            this.videoIds = videoIds;
        }

        public Object getVideos() {
            return videos;
        }

        public void setVideos(Object videos) {
            this.videos = videos;
        }

        public List<TrackIdsDTO> getTrackIds() {
            return trackIds;
        }

        public void setTrackIds(List<TrackIdsDTO> trackIds) {
            this.trackIds = trackIds;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public Object getRemixVideo() {
            return remixVideo;
        }

        public void setRemixVideo(Object remixVideo) {
            this.remixVideo = remixVideo;
        }

        public Object getSharedUsers() {
            return sharedUsers;
        }

        public void setSharedUsers(Object sharedUsers) {
            this.sharedUsers = sharedUsers;
        }

        public Object getHistorySharedUsers() {
            return historySharedUsers;
        }

        public void setHistorySharedUsers(Object historySharedUsers) {
            this.historySharedUsers = historySharedUsers;
        }

        public static class CreatorDTO {
            private boolean defaultAvatar;
            private int province;
            private int authStatus;
            private boolean followed;
            private String avatarUrl;
            private int accountStatus;
            private int gender;
            private int city;
            private int birthday;
            private String userId;
            private int userType;
            private String nickname;
            private String signature;
            private String description;
            private String detailDescription;
            private String avatarImgId;
            private String backgroundImgId;
            private String backgroundUrl;
            private int authority;
            private boolean mutual;
            private Object expertTags;
            private Object experts;
            private int djStatus;
            private int vipType;
            private Object remarkName;
            private int authenticationTypes;
            private Object avatarDetail;
            private boolean anchor;
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
            private String avatarImgId_str;

            public boolean isDefaultAvatar() {
                return defaultAvatar;
            }

            public void setDefaultAvatar(boolean defaultAvatar) {
                this.defaultAvatar = defaultAvatar;
            }

            public int getProvince() {
                return province;
            }

            public void setProvince(int province) {
                this.province = province;
            }

            public int getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(int authStatus) {
                this.authStatus = authStatus;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public int getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(int accountStatus) {
                this.accountStatus = accountStatus;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getCity() {
                return city;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public int getBirthday() {
                return birthday;
            }

            public void setBirthday(int birthday) {
                this.birthday = birthday;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDetailDescription() {
                return detailDescription;
            }

            public void setDetailDescription(String detailDescription) {
                this.detailDescription = detailDescription;
            }

            public String getAvatarImgId() {
                return avatarImgId;
            }

            public void setAvatarImgId(String avatarImgId) {
                this.avatarImgId = avatarImgId;
            }

            public String getBackgroundImgId() {
                return backgroundImgId;
            }

            public void setBackgroundImgId(String backgroundImgId) {
                this.backgroundImgId = backgroundImgId;
            }

            public String getBackgroundUrl() {
                return backgroundUrl;
            }

            public void setBackgroundUrl(String backgroundUrl) {
                this.backgroundUrl = backgroundUrl;
            }

            public int getAuthority() {
                return authority;
            }

            public void setAuthority(int authority) {
                this.authority = authority;
            }

            public boolean isMutual() {
                return mutual;
            }

            public void setMutual(boolean mutual) {
                this.mutual = mutual;
            }

            public Object getExpertTags() {
                return expertTags;
            }

            public void setExpertTags(Object expertTags) {
                this.expertTags = expertTags;
            }

            public Object getExperts() {
                return experts;
            }

            public void setExperts(Object experts) {
                this.experts = experts;
            }

            public int getDjStatus() {
                return djStatus;
            }

            public void setDjStatus(int djStatus) {
                this.djStatus = djStatus;
            }

            public int getVipType() {
                return vipType;
            }

            public void setVipType(int vipType) {
                this.vipType = vipType;
            }

            public Object getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(Object remarkName) {
                this.remarkName = remarkName;
            }

            public int getAuthenticationTypes() {
                return authenticationTypes;
            }

            public void setAuthenticationTypes(int authenticationTypes) {
                this.authenticationTypes = authenticationTypes;
            }

            public Object getAvatarDetail() {
                return avatarDetail;
            }

            public void setAvatarDetail(Object avatarDetail) {
                this.avatarDetail = avatarDetail;
            }

            public boolean isAnchor() {
                return anchor;
            }

            public void setAnchor(boolean anchor) {
                this.anchor = anchor;
            }

            public String getAvatarImgIdStr() {
                return avatarImgIdStr;
            }

            public void setAvatarImgIdStr(String avatarImgIdStr) {
                this.avatarImgIdStr = avatarImgIdStr;
            }

            public String getBackgroundImgIdStr() {
                return backgroundImgIdStr;
            }

            public void setBackgroundImgIdStr(String backgroundImgIdStr) {
                this.backgroundImgIdStr = backgroundImgIdStr;
            }

            public String getAvatarImgId_str() {
                return avatarImgId_str;
            }

            public void setAvatarImgId_str(String avatarImgId_str) {
                this.avatarImgId_str = avatarImgId_str;
            }
        }

        public static class TracksDTO {
            private String name;
            private String id;
            private int pst;
            private int t;
            private List<ArDTO> ar;
            private List<?> alia;
            private int pop;
            private int st;
            private String rt;
            private int fee;
            private int v;
            private Object crbt;
            private String cf;
            private AlDTO al;
            private int dt;
            private HDTO h;
            private MDTO m;
            private LDTO l;
            private Object a;
            private String cd;
            private int no;
            private Object rtUrl;
            private int ftype;
            private List<?> rtUrls;
            private int djId;
            private int copyright;
            private int s_id;
            private String mark;
            private int originCoverType;
            private Object originSongSimpleData;
            private int single;
            private Object noCopyrightRcmd;
            private int rtype;
            private Object rurl;
            private int mst;
            private int cp;
            private int mv;
            private String publishTime;
            private List<String> tns;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getPst() {
                return pst;
            }

            public void setPst(int pst) {
                this.pst = pst;
            }

            public int getT() {
                return t;
            }

            public void setT(int t) {
                this.t = t;
            }

            public List<ArDTO> getAr() {
                return ar;
            }

            public void setAr(List<ArDTO> ar) {
                this.ar = ar;
            }

            public List<?> getAlia() {
                return alia;
            }

            public void setAlia(List<?> alia) {
                this.alia = alia;
            }

            public int getPop() {
                return pop;
            }

            public void setPop(int pop) {
                this.pop = pop;
            }

            public int getSt() {
                return st;
            }

            public void setSt(int st) {
                this.st = st;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public int getV() {
                return v;
            }

            public void setV(int v) {
                this.v = v;
            }

            public Object getCrbt() {
                return crbt;
            }

            public void setCrbt(Object crbt) {
                this.crbt = crbt;
            }

            public String getCf() {
                return cf;
            }

            public void setCf(String cf) {
                this.cf = cf;
            }

            public AlDTO getAl() {
                return al;
            }

            public void setAl(AlDTO al) {
                this.al = al;
            }

            public int getDt() {
                return dt;
            }

            public void setDt(int dt) {
                this.dt = dt;
            }

            public HDTO getH() {
                return h;
            }

            public void setH(HDTO h) {
                this.h = h;
            }

            public MDTO getM() {
                return m;
            }

            public void setM(MDTO m) {
                this.m = m;
            }

            public LDTO getL() {
                return l;
            }

            public void setL(LDTO l) {
                this.l = l;
            }

            public Object getA() {
                return a;
            }

            public void setA(Object a) {
                this.a = a;
            }

            public String getCd() {
                return cd;
            }

            public void setCd(String cd) {
                this.cd = cd;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public Object getRtUrl() {
                return rtUrl;
            }

            public void setRtUrl(Object rtUrl) {
                this.rtUrl = rtUrl;
            }

            public int getFtype() {
                return ftype;
            }

            public void setFtype(int ftype) {
                this.ftype = ftype;
            }

            public List<?> getRtUrls() {
                return rtUrls;
            }

            public void setRtUrls(List<?> rtUrls) {
                this.rtUrls = rtUrls;
            }

            public int getDjId() {
                return djId;
            }

            public void setDjId(int djId) {
                this.djId = djId;
            }

            public int getCopyright() {
                return copyright;
            }

            public void setCopyright(int copyright) {
                this.copyright = copyright;
            }

            public int getS_id() {
                return s_id;
            }

            public void setS_id(int s_id) {
                this.s_id = s_id;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public int getOriginCoverType() {
                return originCoverType;
            }

            public void setOriginCoverType(int originCoverType) {
                this.originCoverType = originCoverType;
            }

            public Object getOriginSongSimpleData() {
                return originSongSimpleData;
            }

            public void setOriginSongSimpleData(Object originSongSimpleData) {
                this.originSongSimpleData = originSongSimpleData;
            }

            public int getSingle() {
                return single;
            }

            public void setSingle(int single) {
                this.single = single;
            }

            public Object getNoCopyrightRcmd() {
                return noCopyrightRcmd;
            }

            public void setNoCopyrightRcmd(Object noCopyrightRcmd) {
                this.noCopyrightRcmd = noCopyrightRcmd;
            }

            public int getRtype() {
                return rtype;
            }

            public void setRtype(int rtype) {
                this.rtype = rtype;
            }

            public Object getRurl() {
                return rurl;
            }

            public void setRurl(Object rurl) {
                this.rurl = rurl;
            }

            public int getMst() {
                return mst;
            }

            public void setMst(int mst) {
                this.mst = mst;
            }

            public int getCp() {
                return cp;
            }

            public void setCp(int cp) {
                this.cp = cp;
            }

            public int getMv() {
                return mv;
            }

            public void setMv(int mv) {
                this.mv = mv;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public List<String> getTns() {
                return tns;
            }

            public void setTns(List<String> tns) {
                this.tns = tns;
            }

            public static class AlDTO {
                private int id;
                private String name;
                private String picUrl;
                private List<?> tns;
                private String pic;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public List<?> getTns() {
                    return tns;
                }

                public void setTns(List<?> tns) {
                    this.tns = tns;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }
            }

            public static class HDTO {
                private int br;
                private int fid;
                private int size;
                private String vd;

                public int getBr() {
                    return br;
                }

                public void setBr(int br) {
                    this.br = br;
                }

                public int getFid() {
                    return fid;
                }

                public void setFid(int fid) {
                    this.fid = fid;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getVd() {
                    return vd;
                }

                public void setVd(String vd) {
                    this.vd = vd;
                }
            }

            public static class MDTO {
                private int br;
                private int fid;
                private int size;
                private String vd;

                public int getBr() {
                    return br;
                }

                public void setBr(int br) {
                    this.br = br;
                }

                public int getFid() {
                    return fid;
                }

                public void setFid(int fid) {
                    this.fid = fid;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getVd() {
                    return vd;
                }

                public void setVd(String vd) {
                    this.vd = vd;
                }
            }

            public static class LDTO {
                private int br;
                private int fid;
                private int size;
                private String vd;

                public int getBr() {
                    return br;
                }

                public void setBr(int br) {
                    this.br = br;
                }

                public int getFid() {
                    return fid;
                }

                public void setFid(int fid) {
                    this.fid = fid;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getVd() {
                    return vd;
                }

                public void setVd(String vd) {
                    this.vd = vd;
                }
            }

            public static class ArDTO {
                private int id;
                private String name;
                private List<?> tns;
                private List<?> alias;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<?> getTns() {
                    return tns;
                }

                public void setTns(List<?> tns) {
                    this.tns = tns;
                }

                public List<?> getAlias() {
                    return alias;
                }

                public void setAlias(List<?> alias) {
                    this.alias = alias;
                }
            }
        }

        public static class TrackIdsDTO {
            private int id;
            private int v;
            private int t;
            private String at;
            private Object alg;

            private String uid;
            private String rcmdReason;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getV() {
                return v;
            }

            public void setV(int v) {
                this.v = v;
            }

            public int getT() {
                return t;
            }

            public void setT(int t) {
                this.t = t;
            }

            public String getAt() {
                return at;
            }

            public void setAt(String at) {
                this.at = at;
            }

            public Object getAlg() {
                return alg;
            }

            public void setAlg(Object alg) {
                this.alg = alg;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uidX) {
                this.uid = uid;
            }

            public String getRcmdReason() {
                return rcmdReason;
            }

            public void setRcmdReason(String rcmdReason) {
                this.rcmdReason = rcmdReason;
            }
        }
    }

    public static class PrivilegesDTO {
        private int id;
        private int fee;
        private int payed;
        private int realPayed;
        private int st;
        private int pl;
        private int dl;
        private int sp;
        private int cp;
        private int subp;
        private boolean cs;
        private int maxbr;
        private int fl;
        private Object pc;
        private boolean toast;
        private int flag;
        private boolean paidBigBang;
        private boolean preSell;
        private int playMaxbr;
        private int downloadMaxbr;
        private Object rscl;
        private FreeTrialPrivilegeDTO freeTrialPrivilege;
        private List<ChargeInfoListDTO> chargeInfoList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getPayed() {
            return payed;
        }

        public void setPayed(int payed) {
            this.payed = payed;
        }

        public int getRealPayed() {
            return realPayed;
        }

        public void setRealPayed(int realPayed) {
            this.realPayed = realPayed;
        }

        public int getSt() {
            return st;
        }

        public void setSt(int st) {
            this.st = st;
        }

        public int getPl() {
            return pl;
        }

        public void setPl(int pl) {
            this.pl = pl;
        }

        public int getDl() {
            return dl;
        }

        public void setDl(int dl) {
            this.dl = dl;
        }

        public int getSp() {
            return sp;
        }

        public void setSp(int sp) {
            this.sp = sp;
        }

        public int getCp() {
            return cp;
        }

        public void setCp(int cp) {
            this.cp = cp;
        }

        public int getSubp() {
            return subp;
        }

        public void setSubp(int subp) {
            this.subp = subp;
        }

        public boolean isCs() {
            return cs;
        }

        public void setCs(boolean cs) {
            this.cs = cs;
        }

        public int getMaxbr() {
            return maxbr;
        }

        public void setMaxbr(int maxbr) {
            this.maxbr = maxbr;
        }

        public int getFl() {
            return fl;
        }

        public void setFl(int fl) {
            this.fl = fl;
        }

        public Object getPc() {
            return pc;
        }

        public void setPc(Object pc) {
            this.pc = pc;
        }

        public boolean isToast() {
            return toast;
        }

        public void setToast(boolean toast) {
            this.toast = toast;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public boolean isPaidBigBang() {
            return paidBigBang;
        }

        public void setPaidBigBang(boolean paidBigBang) {
            this.paidBigBang = paidBigBang;
        }

        public boolean isPreSell() {
            return preSell;
        }

        public void setPreSell(boolean preSell) {
            this.preSell = preSell;
        }

        public int getPlayMaxbr() {
            return playMaxbr;
        }

        public void setPlayMaxbr(int playMaxbr) {
            this.playMaxbr = playMaxbr;
        }

        public int getDownloadMaxbr() {
            return downloadMaxbr;
        }

        public void setDownloadMaxbr(int downloadMaxbr) {
            this.downloadMaxbr = downloadMaxbr;
        }

        public Object getRscl() {
            return rscl;
        }

        public void setRscl(Object rscl) {
            this.rscl = rscl;
        }

        public FreeTrialPrivilegeDTO getFreeTrialPrivilege() {
            return freeTrialPrivilege;
        }

        public void setFreeTrialPrivilege(FreeTrialPrivilegeDTO freeTrialPrivilege) {
            this.freeTrialPrivilege = freeTrialPrivilege;
        }

        public List<ChargeInfoListDTO> getChargeInfoList() {
            return chargeInfoList;
        }

        public void setChargeInfoList(List<ChargeInfoListDTO> chargeInfoList) {
            this.chargeInfoList = chargeInfoList;
        }

        public static class FreeTrialPrivilegeDTO {
            private boolean resConsumable;
            private boolean userConsumable;

            public boolean isResConsumable() {
                return resConsumable;
            }

            public void setResConsumable(boolean resConsumable) {
                this.resConsumable = resConsumable;
            }

            public boolean isUserConsumable() {
                return userConsumable;
            }

            public void setUserConsumable(boolean userConsumable) {
                this.userConsumable = userConsumable;
            }
        }

        public static class ChargeInfoListDTO {
            private int rate;
            private Object chargeUrl;
            private Object chargeMessage;
            private int chargeType;

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }

            public Object getChargeUrl() {
                return chargeUrl;
            }

            public void setChargeUrl(Object chargeUrl) {
                this.chargeUrl = chargeUrl;
            }

            public Object getChargeMessage() {
                return chargeMessage;
            }

            public void setChargeMessage(Object chargeMessage) {
                this.chargeMessage = chargeMessage;
            }

            public int getChargeType() {
                return chargeType;
            }

            public void setChargeType(int chargeType) {
                this.chargeType = chargeType;
            }
        }
    }
}