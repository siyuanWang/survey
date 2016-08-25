package com.wsy.webseed.domain;

import com.wsy.webseed.domain.entity.SurveyPaper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
public class SurveyPaperVo extends SurveyPaper implements Serializable{
    private Integer config;

    public static final int IS_PUBLISH = 1;
    public static final int IS_NOT_PUBLISH = 0;

    public static final int IS_DEL = 1;
    public static final int IS_NOT_DEL = 0;

    public static final int IS_CONFIG = 1;
    public static final int IS_NOT_CONFIG = 0;

    public static final Map<Integer, Object> configMap = new HashMap<Integer, Object>(){{
        put(IS_CONFIG,"已配置");
        put(IS_NOT_CONFIG,"未配置");
    }};

    public static final Map<Integer, Object> delMap = new HashMap<Integer, Object>(){{
        put(IS_DEL,"已删除");
        put(IS_NOT_DEL,"未删除");
    }};

    public static final Map<Integer, Object> publishMap = new HashMap<Integer, Object>(){{
        put(IS_PUBLISH,"已发布");
        put(IS_NOT_PUBLISH,"未发布");
    }};

    public Integer getConfig() {
        return config;
    }

    public void setConfig(Integer config) {
        this.config = config;
    }
}
