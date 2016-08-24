package com.wsy.webseed.domain;

import com.wsy.webseed.domain.entity.SurveyQuestion;

import java.io.Serializable;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
public class SurveyQuestionVo extends SurveyQuestion implements Serializable{
    public static final int IS_DEL = 1;
    public static final int IS_NOT_DEL = 0;
}
