package com.wsy.webseed.domain.entity;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
public class SurveyQuestion {
    private Long id;
    private String title;
    private Integer mode;
    private String options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
