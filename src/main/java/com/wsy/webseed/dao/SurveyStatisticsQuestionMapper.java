package com.wsy.webseed.dao;

import com.wsy.webseed.dao.base.ISqlMapper;
import com.wsy.webseed.domain.SurveyStatisticsQuestionVo;


public interface SurveyStatisticsQuestionMapper extends ISqlMapper {

    public int save(SurveyStatisticsQuestionVo vo);
}
