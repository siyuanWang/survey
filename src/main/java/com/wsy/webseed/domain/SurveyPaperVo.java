package com.wsy.webseed.domain;

import com.wsy.webseed.domain.entity.SurveyPaper;

import java.io.Serializable;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
public class SurveyPaperVo extends SurveyPaper implements Serializable{
    public static final int IS_PUBLISH = 1;
    public static final int IS_NOT_PUBLISH = 0;
}
