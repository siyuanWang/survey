package com.wsy.webseed.service;

import com.wsy.webseed.domain.SurveyPaperVo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
public interface PaperService {
    public void savePaper(SurveyPaperVo question);

    public void updPaper(SurveyPaperVo question);

    public List<SurveyPaperVo> query(Map<String, Object> param);

    public SurveyPaperVo queryById(Long id);

}
