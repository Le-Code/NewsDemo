package com.example.yaojian.newsdemo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yaojian on 2018/1/27.
 */
//忽略掉从json数据中获得的多余属性
@JsonIgnoreProperties(ignoreUnknown = true)
public class News {
    @JsonProperty("title")
    private String titleName;
    @JsonProperty("imgsrc")
    private String imgsrc;
    @JsonProperty("ptime")
    private String ptTime;

    @JsonProperty("detailUrl")
    private String detailUrl = "http://www.qq.com";

    @JsonProperty("postid")
    private String postid;

    @JsonProperty("digest")
    private String digest;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPtTime() {
        return ptTime;
    }

    public void setPtTime(String ptTime) {
        this.ptTime = ptTime;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
