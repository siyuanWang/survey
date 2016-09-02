package com.wsy.webseed.dao;

import com.wsy.webseed.dao.base.ISqlMapper;
import com.wsy.webseed.domain.SurveyStatisticsQuestionVo;

import java.util.List;


public interface SurveyStatisticsQuestionMapper extends ISqlMapper {

    public int save(SurveyStatisticsQuestionVo vo);

    public List<SurveyStatisticsQuestionVo> queryByPaperId(Long statisticsId);
}
