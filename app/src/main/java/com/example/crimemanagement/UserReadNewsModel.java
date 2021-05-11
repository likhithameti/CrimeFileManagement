package com.example.crimemanagement;

public class UserReadNewsModel {
    String title,desc,source;

    public UserReadNewsModel(){}
    public UserReadNewsModel(String title, String desc, String source) {
        this.title = title;
        this.desc = desc;
        this.source = source;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
