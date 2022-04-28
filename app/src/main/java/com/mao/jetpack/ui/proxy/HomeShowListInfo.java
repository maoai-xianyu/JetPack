package com.mao.jetpack.ui.proxy;

import androidx.annotation.Keep;

import java.io.Serializable;
import java.util.List;

@Keep
public class HomeShowListInfo implements Serializable {

    public List<WonderfulShowDetail> recordList;
    public String title; // "精彩现场",
    public String schemaUrl; //"http://h5.51ping.com/app/myshow/index.html#/",

    @Keep
    public class WonderfulShowDetail implements Serializable {
        public int projectId;
        public String posterUrl;  //海报图链接
        public String tagText;  //tag 文本
        public String tagIcon;  //icon url
        public String score;  //评分 后端非要用字符串  😡

        //0 无标签1 "火热抢购中" 2即将开抢 3本周演出4 折扣 无倒计时5 热门演出
        public int tagType;
        public String projectName;  //演出名称
        public String url;  //详情页跳转url
        public String startTime;  //演出开始时间
        public String endTime;  //演出结束时间
        public String onSaleTime;  //演出开售时间
        public String discount;  //折扣   示例：7.2

        public String getUrl() {
            return url;
        }
    }
}
