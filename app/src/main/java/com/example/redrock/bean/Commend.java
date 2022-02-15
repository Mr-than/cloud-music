package com.example.redrock.bean;

import java.util.List;

/**
 *   description:评论的bean
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class Commend {

    private boolean isMusician;
    private int cnum;
    private String userId;
    private List<?> topComments;
    private boolean moreHot;
    private List<HotCommentsDTO> hotComments;
    private Object commentBanner;
    private int code;
    private List<CommentsDTO> comments;
    private String total;
    private boolean more;

    public boolean isIsMusician() {
        return isMusician;
    }

    public void setIsMusician(boolean isMusician) {
        this.isMusician = isMusician;
    }

    public int getCnum() {
        return cnum;
    }

    public void setCnum(int cnum) {
        this.cnum = cnum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<?> getTopComments() {
        return topComments;
    }

    public void setTopComments(List<?> topComments) {
        this.topComments = topComments;
    }

    public boolean isMoreHot() {
        return moreHot;
    }

    public void setMoreHot(boolean moreHot) {
        this.moreHot = moreHot;
    }

    public List<HotCommentsDTO> getHotComments() {
        return hotComments;
    }

    public void setHotComments(List<HotCommentsDTO> hotComments) {
        this.hotComments = hotComments;
    }

    public Object getCommentBanner() {
        return commentBanner;
    }

    public void setCommentBanner(Object commentBanner) {
        this.commentBanner = commentBanner;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<CommentsDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentsDTO> comments) {
        this.comments = comments;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public static class HotCommentsDTO {
        private UserDTO user;
        private List<?> beReplied;
        private Object pendantData;
        private Object showFloorComment;
        private int status;
        private String commentId;
        private String content;
        private Object contentResource;
        private String time;
        private String timeStr;
        private boolean needDisplayTime;
        private int likedCount;
        private Object expressionUrl;
        private int commentLocationType;
        private String parentCommentId;
        private Object decoration;
        private Object repliedMark;
        private boolean liked;

        public UserDTO getUser() {
            return user;
        }

        public void setUser(UserDTO user) {
            this.user = user;
        }

        public List<?> getBeReplied() {
            return beReplied;
        }

        public void setBeReplied(List<?> beReplied) {
            this.beReplied = beReplied;
        }

        public Object getPendantData() {
            return pendantData;
        }

        public void setPendantData(Object pendantData) {
            this.pendantData = pendantData;
        }

        public Object getShowFloorComment() {
            return showFloorComment;
        }

        public void setShowFloorComment(Object showFloorComment) {
            this.showFloorComment = showFloorComment;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getContentResource() {
            return contentResource;
        }

        public void setContentResource(Object contentResource) {
            this.contentResource = contentResource;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTimeStr() {
            return timeStr;
        }

        public void setTimeStr(String timeStr) {
            this.timeStr = timeStr;
        }

        public boolean isNeedDisplayTime() {
            return needDisplayTime;
        }

        public void setNeedDisplayTime(boolean needDisplayTime) {
            this.needDisplayTime = needDisplayTime;
        }

        public int getLikedCount() {
            return likedCount;
        }

        public void setLikedCount(int likedCount) {
            this.likedCount = likedCount;
        }

        public Object getExpressionUrl() {
            return expressionUrl;
        }

        public void setExpressionUrl(Object expressionUrl) {
            this.expressionUrl = expressionUrl;
        }

        public int getCommentLocationType() {
            return commentLocationType;
        }

        public void setCommentLocationType(int commentLocationType) {
            this.commentLocationType = commentLocationType;
        }

        public String getParentCommentId() {
            return parentCommentId;
        }

        public void setParentCommentId(String parentCommentId) {
            this.parentCommentId = parentCommentId;
        }

        public Object getDecoration() {
            return decoration;
        }

        public void setDecoration(Object decoration) {
            this.decoration = decoration;
        }

        public Object getRepliedMark() {
            return repliedMark;
        }

        public void setRepliedMark(Object repliedMark) {
            this.repliedMark = repliedMark;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public static class UserDTO {
            private Object locationInfo;
            private Object liveInfo;
            private int anonym;
            private String userId;
            private Object avatarDetail;
            private int userType;
            private String nickname;
            private String avatarUrl;
            private int authStatus;
            private Object expertTags;
            private Object experts;
            private int vipType;
            private boolean followed;
            private boolean mutual;
            private Object remarkName;
            private VipRightsDTO vipRights;
            private Object commonIdentity;

            public Object getLocationInfo() {
                return locationInfo;
            }

            public void setLocationInfo(Object locationInfo) {
                this.locationInfo = locationInfo;
            }

            public Object getLiveInfo() {
                return liveInfo;
            }

            public void setLiveInfo(Object liveInfo) {
                this.liveInfo = liveInfo;
            }

            public int getAnonym() {
                return anonym;
            }

            public void setAnonym(int anonym) {
                this.anonym = anonym;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public Object getAvatarDetail() {
                return avatarDetail;
            }

            public void setAvatarDetail(Object avatarDetail) {
                this.avatarDetail = avatarDetail;
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

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public int getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(int authStatus) {
                this.authStatus = authStatus;
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

            public int getVipType() {
                return vipType;
            }

            public void setVipType(int vipType) {
                this.vipType = vipType;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public boolean isMutual() {
                return mutual;
            }

            public void setMutual(boolean mutual) {
                this.mutual = mutual;
            }

            public Object getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(Object remarkName) {
                this.remarkName = remarkName;
            }

            public VipRightsDTO getVipRights() {
                return vipRights;
            }

            public void setVipRights(VipRightsDTO vipRights) {
                this.vipRights = vipRights;
            }

            public Object getCommonIdentity() {
                return commonIdentity;
            }

            public void setCommonIdentity(Object commonIdentity) {
                this.commonIdentity = commonIdentity;
            }

            public static class VipRightsDTO {
                private AssociatorDTO associator;
                private Object musicPackage;
                private int redVipAnnualCount;
                private int redVipLevel;

                public AssociatorDTO getAssociator() {
                    return associator;
                }

                public void setAssociator(AssociatorDTO associator) {
                    this.associator = associator;
                }

                public Object getMusicPackage() {
                    return musicPackage;
                }

                public void setMusicPackage(Object musicPackage) {
                    this.musicPackage = musicPackage;
                }

                public int getRedVipAnnualCount() {
                    return redVipAnnualCount;
                }

                public void setRedVipAnnualCount(int redVipAnnualCount) {
                    this.redVipAnnualCount = redVipAnnualCount;
                }

                public int getRedVipLevel() {
                    return redVipLevel;
                }

                public void setRedVipLevel(int redVipLevel) {
                    this.redVipLevel = redVipLevel;
                }

                public static class AssociatorDTO {
                    private int vipCode;
                    private boolean rights;

                    public int getVipCode() {
                        return vipCode;
                    }

                    public void setVipCode(int vipCode) {
                        this.vipCode = vipCode;
                    }

                    public boolean isRights() {
                        return rights;
                    }

                    public void setRights(boolean rights) {
                        this.rights = rights;
                    }
                }
            }
        }
    }

    public static class CommentsDTO {
        private UserDTO user;
        private List<?> beReplied;
        private Object pendantData;
        private Object showFloorComment;
        private int status;
        private String commentId;
        private String content;
        private Object contentResource;
        private long time;
        private String timeStr;
        private boolean needDisplayTime;
        private int likedCount;
        private Object expressionUrl;
        private int commentLocationType;
        private String parentCommentId;
        private DecorationDTO decoration;
        private Object repliedMark;
        private boolean liked;

        public UserDTO getUser() {
            return user;
        }

        public void setUser(UserDTO user) {
            this.user = user;
        }

        public List<?> getBeReplied() {
            return beReplied;
        }

        public void setBeReplied(List<?> beReplied) {
            this.beReplied = beReplied;
        }

        public Object getPendantData() {
            return pendantData;
        }

        public void setPendantData(Object pendantData) {
            this.pendantData = pendantData;
        }

        public Object getShowFloorComment() {
            return showFloorComment;
        }

        public void setShowFloorComment(Object showFloorComment) {
            this.showFloorComment = showFloorComment;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getContentResource() {
            return contentResource;
        }

        public void setContentResource(Object contentResource) {
            this.contentResource = contentResource;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTimeStr() {
            return timeStr;
        }

        public void setTimeStr(String timeStr) {
            this.timeStr = timeStr;
        }

        public boolean isNeedDisplayTime() {
            return needDisplayTime;
        }

        public void setNeedDisplayTime(boolean needDisplayTime) {
            this.needDisplayTime = needDisplayTime;
        }

        public int getLikedCount() {
            return likedCount;
        }

        public void setLikedCount(int likedCount) {
            this.likedCount = likedCount;
        }

        public Object getExpressionUrl() {
            return expressionUrl;
        }

        public void setExpressionUrl(Object expressionUrl) {
            this.expressionUrl = expressionUrl;
        }

        public int getCommentLocationType() {
            return commentLocationType;
        }

        public void setCommentLocationType(int commentLocationType) {
            this.commentLocationType = commentLocationType;
        }

        public String getParentCommentId() {
            return parentCommentId;
        }

        public void setParentCommentId(String parentCommentId) {
            this.parentCommentId = parentCommentId;
        }

        public DecorationDTO getDecoration() {
            return decoration;
        }

        public void setDecoration(DecorationDTO decoration) {
            this.decoration = decoration;
        }

        public Object getRepliedMark() {
            return repliedMark;
        }

        public void setRepliedMark(Object repliedMark) {
            this.repliedMark = repliedMark;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public static class UserDTO {
            private Object locationInfo;
            private Object liveInfo;
            private int anonym;
            private String userId;
            private Object avatarDetail;
            private int userType;
            private String nickname;
            private String avatarUrl;
            private int authStatus;
            private Object expertTags;
            private Object experts;
            private int vipType;
            private boolean followed;
            private boolean mutual;
            private Object remarkName;
            private HotCommentsDTO.UserDTO.VipRightsDTO vipRights;
            private Object commonIdentity;

            public Object getLocationInfo() {
                return locationInfo;
            }

            public void setLocationInfo(Object locationInfo) {
                this.locationInfo = locationInfo;
            }

            public Object getLiveInfo() {
                return liveInfo;
            }

            public void setLiveInfo(Object liveInfo) {
                this.liveInfo = liveInfo;
            }

            public int getAnonym() {
                return anonym;
            }

            public void setAnonym(int anonym) {
                this.anonym = anonym;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public Object getAvatarDetail() {
                return avatarDetail;
            }

            public void setAvatarDetail(Object avatarDetail) {
                this.avatarDetail = avatarDetail;
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

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public int getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(int authStatus) {
                this.authStatus = authStatus;
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

            public int getVipType() {
                return vipType;
            }

            public void setVipType(int vipType) {
                this.vipType = vipType;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public boolean isMutual() {
                return mutual;
            }

            public void setMutual(boolean mutual) {
                this.mutual = mutual;
            }

            public Object getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(Object remarkName) {
                this.remarkName = remarkName;
            }

            public HotCommentsDTO.UserDTO.VipRightsDTO getVipRights() {
                return vipRights;
            }

            public void setVipRights(HotCommentsDTO.UserDTO.VipRightsDTO vipRights) {
                this.vipRights = vipRights;
            }

            public Object getCommonIdentity() {
                return commonIdentity;
            }

            public void setCommonIdentity(Object commonIdentity) {
                this.commonIdentity = commonIdentity;
            }

            public static class VipRightsDTO {
                private HotCommentsDTO.UserDTO.VipRightsDTO.AssociatorDTO associator;
                private Object musicPackage;
                private int redVipAnnualCount;
                private int redVipLevel;

                public HotCommentsDTO.UserDTO.VipRightsDTO.AssociatorDTO getAssociator() {
                    return associator;
                }

                public void setAssociator(HotCommentsDTO.UserDTO.VipRightsDTO.AssociatorDTO associator) {
                    this.associator = associator;
                }

                public Object getMusicPackage() {
                    return musicPackage;
                }

                public void setMusicPackage(Object musicPackage) {
                    this.musicPackage = musicPackage;
                }

                public int getRedVipAnnualCount() {
                    return redVipAnnualCount;
                }

                public void setRedVipAnnualCount(int redVipAnnualCount) {
                    this.redVipAnnualCount = redVipAnnualCount;
                }

                public int getRedVipLevel() {
                    return redVipLevel;
                }

                public void setRedVipLevel(int redVipLevel) {
                    this.redVipLevel = redVipLevel;
                }

                public static class AssociatorDTO {
                    private int vipCode;
                    private boolean rights;

                    public int getVipCode() {
                        return vipCode;
                    }

                    public void setVipCode(int vipCode) {
                        this.vipCode = vipCode;
                    }

                    public boolean isRights() {
                        return rights;
                    }

                    public void setRights(boolean rights) {
                        this.rights = rights;
                    }
                }
            }
        }

        public static class DecorationDTO {
        }
    }
}
