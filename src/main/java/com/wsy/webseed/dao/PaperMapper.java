package com.wsy.webseed.dao;

import com.wsy.webseed.dao.base.ISqlMapper;
import com.wsy.webseed.domain.SurveyPaperModuleVo;
import com.wsy.webseed.domain.SurveyPaperVo;
import com.wsy.webseed.domain.SurveyQuestionVo;
import com.wsy.webseed.domain.entity.SurveyQuestion;

import java.util.List;
import java.util.Map;


public interface PaperMapper extends ISqlMapper {

    public int save(SurveyPaperVo vo);

    public int update(SurveyPaperVo vo);

    public List<SurveyPaperVo> query(Map<String, Object> param);

    public SurveyPaperVo queryById(Long id);

    public List<SurveyPaperModuleVo> queryModulesByPaperId(Long paperId);

    public List<SurveyQuestionVo> queryQuestionsByModuleId(Long moduleId);

    public List<SurveyQuestionVo> queryQuestionsByPaperId(Long paperId);
}
