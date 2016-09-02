package com.wsy.webseed.dao;

import com.wsy.webseed.dao.base.ISqlMapper;
import com.wsy.webseed.domain.SurveyStatisticsVo;


public interface SurveyStatisticsMapper extends ISqlMapper {

    public int save(SurveyStatisticsVo vo);
}
