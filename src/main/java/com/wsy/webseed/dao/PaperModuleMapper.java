package com.wsy.webseed.dao;

import com.wsy.webseed.dao.base.ISqlMapper;
import com.wsy.webseed.domain.SurveyPaperModuleVo;

import java.util.Map;


public interface PaperModuleMapper extends ISqlMapper {

    public int save(SurveyPaperModuleVo vo);

    public int del(long id);

    public int saveModuleQuestionRelation(Map<String, Object> param);

    public int delModuleQuestionRelation(Long moduleId);
}
