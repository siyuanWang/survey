package com.wsy.webseed.dao;

import com.wsy.webseed.dao.base.ISqlMapper;
import com.wsy.webseed.domain.SurveyStatisticsVo;

import java.util.List;
import java.util.Map;


public interface SurveyStatisticsMapper extends ISqlMapper {

    public int save(SurveyStatisticsVo vo);

    public List<SurveyStatisticsVo> query(Map<String, Object> param);
}
