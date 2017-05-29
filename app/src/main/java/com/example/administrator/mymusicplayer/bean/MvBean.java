package com.example.administrator.mymusicplayer.bean;


import java.util.List;

public class MvBean {

    private String msg;
    private int retcode;
    private boolean success;
    private List<RetObjBean> retObj;

    public MvBean(String msg, int retcode, boolean success, List<RetObjBean> retObj) {
        this.msg = msg;
        this.retcode = retcode;
        this.success = success;
        this.retObj = retObj;
    }

    public MvBean() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<RetObjBean> getRetObj() {
        return retObj;
    }

    public void setRetObj(List<RetObjBean> retObj) {
        this.retObj = retObj;
    }

    @Override
    public String toString() {
        return "MvBean{" +
                "msg='" + msg + '\'' +
                ", retcode=" + retcode +
                ", success=" + success +
                ", retObj=" + retObj +
                '}';
    }

    public static class RetObjBean {
        /**
         * id : 1612231255503590074
         * isNewRecord : false
         * createDate : 2016-12-23 15:16:34
         * updateDate : 2016-12-23 15:20:12
         * delFlag : 0
         * title : 我们都一样
         * star : 0.0
         * level : 0
         * isTop : 0
         * imageUrl : https://cityparlor.oss-cn-beijing.aliyuncs.com/vod/edit1482477391883.jpg
         * hdUrl : https://zgzsvideo.b0.upaiyun.com/upload/test1482480647.mp4
         * sort : 0
         * playTimes : 0.0
         * isTransfer : 1
         * videoSource : https://zgzsvideo.b0.upaiyun.com/upload/test1494312900.mp4
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private String updateDate;
        private String delFlag;
        private String title;
        private double star;
        private String level;
        private String isTop;
        private String imageUrl;
        private String hdUrl;
        private int sort;
        private double playTimes;
        private String isTransfer;
        private String videoSource;

        public RetObjBean(String id, boolean isNewRecord, String createDate, String updateDate, String delFlag, String title, double star, String level, String isTop, String imageUrl, String hdUrl, int sort, double playTimes, String isTransfer, String videoSource) {
            this.id = id;
            this.isNewRecord = isNewRecord;
            this.createDate = createDate;
            this.updateDate = updateDate;
            this.delFlag = delFlag;
            this.title = title;
            this.star = star;
            this.level = level;
            this.isTop = isTop;
            this.imageUrl = imageUrl;
            this.hdUrl = hdUrl;
            this.sort = sort;
            this.playTimes = playTimes;
            this.isTransfer = isTransfer;
            this.videoSource = videoSource;
        }

        public RetObjBean() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getStar() {
            return star;
        }

        public void setStar(double star) {
            this.star = star;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getIsTop() {
            return isTop;
        }

        public void setIsTop(String isTop) {
            this.isTop = isTop;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getHdUrl() {
            return hdUrl;
        }

        public void setHdUrl(String hdUrl) {
            this.hdUrl = hdUrl;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public double getPlayTimes() {
            return playTimes;
        }

        public void setPlayTimes(double playTimes) {
            this.playTimes = playTimes;
        }

        public String getIsTransfer() {
            return isTransfer;
        }

        public void setIsTransfer(String isTransfer) {
            this.isTransfer = isTransfer;
        }

        public String getVideoSource() {
            return videoSource;
        }

        public void setVideoSource(String videoSource) {
            this.videoSource = videoSource;
        }

        @Override
        public String toString() {
            return "RetObjBean{" +
                    "id='" + id + '\'' +
                    ", isNewRecord=" + isNewRecord +
                    ", createDate='" + createDate + '\'' +
                    ", updateDate='" + updateDate + '\'' +
                    ", delFlag='" + delFlag + '\'' +
                    ", title='" + title + '\'' +
                    ", star=" + star +
                    ", level='" + level + '\'' +
                    ", isTop='" + isTop + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", hdUrl='" + hdUrl + '\'' +
                    ", sort=" + sort +
                    ", playTimes=" + playTimes +
                    ", isTransfer='" + isTransfer + '\'' +
                    ", videoSource='" + videoSource + '\'' +
                    '}';
        }
    }
}
