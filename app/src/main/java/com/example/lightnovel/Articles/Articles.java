package com.example.lightnovel.Articles;

public class Articles {
    String tag;
    String title;
    String lastRespondTime;
    String upDaterName;

    String urlForTitle;

    public Articles(String tag, String title, String lastRespondTime, String upDaterName, String urlForTitle) {
        this.tag = tag;
        this.title = title;
        this.lastRespondTime = lastRespondTime;
        this.upDaterName = upDaterName;
        this.urlForTitle = urlForTitle;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastRespondTime() {
        return lastRespondTime;
    }

    public void setLastRespondTime(String lastRespondTime) {
        this.lastRespondTime = lastRespondTime;
    }

    public String getUpDaterName() {
        return upDaterName;
    }

    public void setUpDaterName(String upDaterName) {
        this.upDaterName = upDaterName;
    }

    public String getUrlForTitle() {
        return urlForTitle;
    }

    public void setUrlForTitle(String urlForTitle) {
        this.urlForTitle = urlForTitle;
    }
}
