package com.example.administrator.mymusicplayer.config;


public interface NetConfig {
    /**
     * https://cms.loongscity.com/cityparlor-web/mobile/cityparlor/vod/video/list?videoType=11f521fe-6a8d-4acc-b021-f997c3ec661b
     */
    String BASE_VIDEO_PATH="https://cms.loongscity.com/cityparlor-web/mobile/cityparlor/vod/video/list?";//视频接口
    String VIDEO_Type="11f521fe-6a8d-4acc-b021-f997c3ec661b";//视频接口

    String LIANG_LIANG = "http://192.168.43.76:8080/Music/liangliang.mp3";//凉凉  张碧晨&杨宗纬
    String PIAO_YANG_GUOHAI = "http://192.168.43.76:8080/Music/pyghlkn.mp3";//漂洋过海来看你  朱亚文&王丽坤
    String GANG_HAO_YU_JIAN_NI = "http://192.168.43.76:8080/Music/ghyjn.mp3";//刚好遇见你  李玉刚
    String XIN_YANG = "http://192.168.43.76:8080/Music/xinyang.mp3"; //信仰  张信哲
}
