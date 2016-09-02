package com.wsy.webseed.domain;

/**
 * Created by wsy on 2016/9/2.
 */
public class OptionVo {
    private String content;//选项内容
    private String title;//选项序号,比如A,B,C
    private Integer num;//选项选择数量

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
