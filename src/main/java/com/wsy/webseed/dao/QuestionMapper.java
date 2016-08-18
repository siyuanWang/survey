package com.wsy.webseed.dao;

import com.wsy.webseed.dao.base.ISqlMapper;
import com.wsy.webseed.domain.SurveyQuestionVo;

import java.util.List;
import java.util.Map;


public interface QuestionMapper extends ISqlMapper {

    public int save(SurveyQuestionVo vo);

    public int del(long id);

    public int update(SurveyQuestionVo vo);

    public List<SurveyQuestionVo> query(Map<String, Object> param);
}
