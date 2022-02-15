package com.example.redrock.bean;

import java.util.List;

/**
 *   description:歌曲详细
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class SongDetail {


    private List<DataDTO> data;
    private int code;

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataDTO {
        private String id;
        private String url;
        private String br;
        private String size;
        private String md5;
        private int code;
        private int expi;
        private String type;
        private int gain;
        private int fee;
        private Object uf;
        private int payed;
        private int flag;
        private boolean canExtend;
        private FreeTrialInfoDTO freeTrialInfo;
        private Object level;
        private Object encodeType;
        private FreeTrialPrivilegeDTO freeTrialPrivilege;
        private FreeTimeTrialPrivilegeDTO freeTimeTrialPrivilege;
        private int urlSource;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBr() {
            return br;
        }

        public void setBr(String br) {
            this.br = br;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getExpi() {
            return expi;
        }

        public void setExpi(int expi) {
            this.expi = expi;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getGain() {
            return gain;
        }

        public void setGain(int gain) {
            this.gain = gain;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public Object getUf() {
            return uf;
        }

        public void setUf(Object uf) {
            this.uf = uf;
        }

        public int getPayed() {
            return payed;
        }

        public void setPayed(int payed) {
            this.payed = payed;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public boolean isCanExtend() {
            return canExtend;
        }

        public void setCanExtend(boolean canExtend) {
            this.canExtend = canExtend;
        }

        public FreeTrialInfoDTO getFreeTrialInfo() {
            return freeTrialInfo;
        }

        public void setFreeTrialInfo(FreeTrialInfoDTO freeTrialInfo) {
            this.freeTrialInfo = freeTrialInfo;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public Object getEncodeType() {
            return encodeType;
        }

        public void setEncodeType(Object encodeType) {
            this.encodeType = encodeType;
        }

        public FreeTrialPrivilegeDTO getFreeTrialPrivilege() {
            return freeTrialPrivilege;
        }

        public void setFreeTrialPrivilege(FreeTrialPrivilegeDTO freeTrialPrivilege) {
            this.freeTrialPrivilege = freeTrialPrivilege;
        }

        public FreeTimeTrialPrivilegeDTO getFreeTimeTrialPrivilege() {
            return freeTimeTrialPrivilege;
        }

        public void setFreeTimeTrialPrivilege(FreeTimeTrialPrivilegeDTO freeTimeTrialPrivilege) {
            this.freeTimeTrialPrivilege = freeTimeTrialPrivilege;
        }

        public int getUrlSource() {
            return urlSource;
        }

        public void setUrlSource(int urlSource) {
            this.urlSource = urlSource;
        }

        public static class FreeTrialInfoDTO {
            private int start;
            private int end;

            public int getStart() {
                return start;
            }

            public void setStart(int start) {
                this.start = start;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }
        }

        public static class FreeTrialPrivilegeDTO {
            private boolean resConsumable;
            private boolean userConsumable;
            private Object listenType;

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

            public Object getListenType() {
                return listenType;
            }

            public void setListenType(Object listenType) {
                this.listenType = listenType;
            }
        }

        public static class FreeTimeTrialPrivilegeDTO {
            private boolean resConsumable;
            private boolean userConsumable;
            private int type;
            private int remainTime;

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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getRemainTime() {
                return remainTime;
            }

            public void setRemainTime(int remainTime) {
                this.remainTime = remainTime;
            }
        }
    }
}
