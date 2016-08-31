package com.wsy.webseed.service;

import com.wsy.webseed.domain.SurveyPaperVo;
import com.wsy.webseed.domain.SurveyQuestionVo;

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

    public void del(Long id);

    public void publish(Long id);

    public List<SurveyQuestionVo> queryByPaperId(Long paperId);

    public void config(Long paperId, String[] questionIds);

}
