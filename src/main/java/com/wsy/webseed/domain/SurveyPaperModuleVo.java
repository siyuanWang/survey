package com.wsy.webseed.domain;

import com.wsy.webseed.domain.entity.SurveyPaper;
import com.wsy.webseed.domain.entity.SurveyPaperModule;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
public class SurveyPaperModuleVo extends SurveyPaperModule implements Serializable{
    private List<SurveyQuestionVo> questions;

    public List<SurveyQuestionVo> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyQuestionVo> questions) {
        this.questions = questions;
    }
}
