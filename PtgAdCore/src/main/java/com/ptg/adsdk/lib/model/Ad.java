package com.ptg.adsdk.lib.model;

import java.util.List;
import java.util.Map;

public class Ad {
    private int action;//0 下载 1 跳转 2小程序唤起 。
    private String source;
    private String ad_id;
    private String feedType;
    private AppInfo app;
    private List<String> click_tracking;
    private List<String> imp_tracking;
    private String keyword;
    private String title;
    private String desc;
    private String keyword_replace;
    private String landing_url;
    private String dp_url;
    private String feature_tag;
    private List<String> dp_clk;
    private List<String> clk;
    private List<String> ext_urls;
    private Map<String,List<String>> video_imp;
    private List<String> view_ability;//可见监测链接
    private String url;// 落地页    有dp_url dp_url ,否则 URL
    private int sid;
    private int needApi;
    private int price;
    private int aid;
    private int height;
    private int duration;
    private int width;
    private String style;
    private String mime;//物料类型，jpg|png|gif|webp|swf|flv|mp4|txt|icon|c，一般为素材后缀，特殊的txt:文字链icon:图文c:富媒体
    private String src;
    private List<String> imp;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Map<String, List<String>> getVideo_imp() {
        return video_imp;
    }

    public void setVideo_imp(Map<String, List<String>> video_imp) {
        this.video_imp = video_imp;
    }

    public List<String> getView_ability() {
        return view_ability;
    }

    public void setView_ability(List<String> view_ability) {
        this.view_ability = view_ability;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFeature_tag() {
        return feature_tag;
    }

    public void setFeature_tag(String feature_tag) {
        this.feature_tag = feature_tag;
    }

    public int getNeedApi() {
        return needApi;
    }

    public void setNeedApi(int needApi) {
        this.needApi = needApi;
    }

    public List<String> getImp() {
        return imp;
    }

    public void setImp(List<String> imp) {
        this.imp = imp;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<String> getClk() {
        return clk;
    }

    public void setClk(List<String> clk) {
        this.clk = clk;
    }



    public List<String> getExt_urls() {
        return ext_urls;
    }

    public void setExt_urls(List<String> ext_urls) {
        this.ext_urls = ext_urls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public List<String> getImp_tracking() {
        return imp_tracking;
    }

    public void setImp_tracking(List<String> imp_tracking) {
        this.imp_tracking = imp_tracking;
    }

    public String getKeyword_replace() {
        return keyword_replace;
    }

    public void setKeyword_replace(String keyword_replace) {
        this.keyword_replace = keyword_replace;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public AppInfo getApp() {
        return app;
    }

    public void setApp(AppInfo app) {
        this.app = app;
    }

    public List<String> getClick_tracking() {
        return click_tracking;
    }

    public void setClick_tracking(List<String> click_tracking) {
        this.click_tracking = click_tracking;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLanding_url() {
        return landing_url;
    }

    public void setLanding_url(String landing_url) {
        this.landing_url = landing_url;
    }

    public String getDp_url() {
        return dp_url;
    }

    public void setDp_url(String dp_url) {
        this.dp_url = dp_url;
    }

    public List<String> getDp_clk() {
        return dp_clk;
    }

    public void setDp_clk(List<String> dp_clk) {
        this.dp_clk = dp_clk;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "action=" + action +
                ", ad_id='" + ad_id + '\'' +
                ", app=" + app +
                ", click_tracking=" + click_tracking +
                ", imp_tracking=" + imp_tracking +
                ", keyword='" + keyword + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", keyword_replace='" + keyword_replace + '\'' +
                ", landing_url='" + landing_url + '\'' +
                ", dp_url='" + dp_url + '\'' +
                ", feature_tag='" + feature_tag + '\'' +
                ", dp_clk=" + dp_clk +
                ", clk=" + clk +
                ", ext_urls=" + ext_urls +
                ", url='" + url + '\'' +
                ", sid=" + sid +
                ", needApi=" + needApi +
                ", price=" + price +
                ", aid=" + aid +
                ", height=" + height +
                ", width=" + width +
                ", style='" + style + '\'' +
                ", src='" + src + '\'' +
                ", imp=" + imp +
                '}';
    }
}
