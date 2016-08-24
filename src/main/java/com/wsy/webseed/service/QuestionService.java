package com.wsy.webseed.service;

import com.wsy.webseed.domain.SurveyQuestionVo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
public interface QuestionService {
    public void saveQuestion(SurveyQuestionVo question);

    public void updQuestion(SurveyQuestionVo question);

    public List<SurveyQuestionVo> query(Map<String, Object> param);

    public SurveyQuestionVo queryById(Long id);

}
