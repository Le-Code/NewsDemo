package com.example.yaojian.newsdemo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by yaojian on 2018/1/30.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsDetail {

    @JsonProperty("img")
    private List<ImageEntity> imageList;

    @JsonProperty("body")
    private String body;
    @JsonProperty("digest")
    public String digest;
    @JsonProperty("title")
    public String title;
    @JsonProperty("source")
    public String source;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImageEntity{
        @JsonProperty("src")
        private String src;
        @JsonProperty("alt")
        private String alt;
        @JsonProperty("pixel")
        private String pixel;
        @JsonProperty("ref")
        private String ref;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getPixel() {
            return pixel;
        }

        public void setPixel(String pixel) {
            this.pixel = pixel;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }
    }

    public List<ImageEntity> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageEntity> imageList) {
        this.imageList = imageList;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "NewsDetail{" +
                "imageList=" + imageList +
                ", body='" + body + '\'' +
                ", digest='" + digest + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
