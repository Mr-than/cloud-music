package com.example.redrock.bean;

import java.util.List;

/**
 *   description:搜索歌曲的bean
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/15
 */

public class SearchBean {

    private ResultDTO result;
    private int code;

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResultDTO {
        private List<SongsDTO> songs;
        private int songCount;

        public List<SongsDTO> getSongs() {
            return songs;
        }

        public void setSongs(List<SongsDTO> songs) {
            this.songs = songs;
        }

        public int getSongCount() {
            return songCount;
        }

        public void setSongCount(int songCount) {
            this.songCount = songCount;
        }

        public static class SongsDTO {
            private String name;
            private String id;
            private int pst;
            private int t;
            private List<ArDTO> ar;
            private List<?> alia;
            private int pop;
            private int st;
            private Object rt;
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
            private String djId;
            private int copyright;
            private String s_id;
            private String mark;
            private int originCoverType;
            private Object originSongSimpleData;
            private boolean resourceState;
            private int version;
            private int single;
            private Object noCopyrightRcmd;
            private int mst;
            private int cp;
            private int mv;
            private int rtype;
            private Object rurl;
            private long publishTime;
            private PrivilegeDTO privilege;

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

            public Object getRt() {
                return rt;
            }

            public void setRt(Object rt) {
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

            public String getDjId() {
                return djId;
            }

            public void setDjId(String djId) {
                this.djId = djId;
            }

            public int getCopyright() {
                return copyright;
            }

            public void setCopyright(int copyright) {
                this.copyright = copyright;
            }

            public String getS_id() {
                return s_id;
            }

            public void setS_id(String s_id) {
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

            public boolean isResourceState() {
                return resourceState;
            }

            public void setResourceState(boolean resourceState) {
                this.resourceState = resourceState;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
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

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public PrivilegeDTO getPrivilege() {
                return privilege;
            }

            public void setPrivilege(PrivilegeDTO privilege) {
                this.privilege = privilege;
            }

            public static class AlDTO {
                private String id;
                private String name;
                private String picUrl;
                private List<?> tns;
                private long pic;

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

                public long getPic() {
                    return pic;
                }

                public void setPic(long pic) {
                    this.pic = pic;
                }
            }

            public static class HDTO {
                private String br;
                private int fid;
                private String size;
                private String vd;

                public String getBr() {
                    return br;
                }

                public void setBr(String br) {
                    this.br = br;
                }

                public int getFid() {
                    return fid;
                }

                public void setFid(int fid) {
                    this.fid = fid;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
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
                private String br;
                private int fid;
                private String size;
                private String vd;

                public String getBr() {
                    return br;
                }

                public void setBr(String br) {
                    this.br = br;
                }

                public int getFid() {
                    return fid;
                }

                public void setFid(int fid) {
                    this.fid = fid;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
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
                private String br;
                private int fid;
                private String size;
                private String vd;

                public String getBr() {
                    return br;
                }

                public void setBr(String br) {
                    this.br = br;
                }

                public int getFid() {
                    return fid;
                }

                public void setFid(int fid) {
                    this.fid = fid;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public String getVd() {
                    return vd;
                }

                public void setVd(String vd) {
                    this.vd = vd;
                }
            }

            public static class PrivilegeDTO {
                private String id;
                private int fee;
                private int payed;
                private int st;
                private int pl;
                private int dl;
                private int sp;
                private int cp;
                private int subp;
                private boolean cs;
                private int maxbr;
                private int fl;
                private boolean toast;
                private int flag;
                private boolean preSell;
                private int playMaxbr;
                private int downloadMaxbr;
                private Object rscl;
                private FreeTrialPrivilegeDTO freeTrialPrivilege;
                private List<ChargeInfoListDTO> chargeInfoList;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
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

            public static class ArDTO {
                private String id;
                private String name;
                private List<?> tns;
                private List<?> alias;

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
    }
}
