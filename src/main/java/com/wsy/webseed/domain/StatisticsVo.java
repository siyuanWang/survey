package com.wsy.webseed.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by wsy on 2016/9/2.
 */
public class StatisticsVo {
    private List<SurveyStatisticsVo> surveyStatisticsVos;
    private Map<String, List<SurveyQuestionVo>> map;
    private List<SurveyQuestionVo> questionVos;
    private List<SurveyQuestionVo> wendas;//问答试题集合

    public List<SurveyStatisticsVo> getSurveyStatisticsVos() {
        return surveyStatisticsVos;
    }

    public void setSurveyStatisticsVos(List<SurveyStatisticsVo> surveyStatisticsVos) {
        this.surveyStatisticsVos = surveyStatisticsVos;
    }

    public Map<String, List<SurveyQuestionVo>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<SurveyQuestionVo>> map) {
        this.map = map;
    }

    public List<SurveyQuestionVo> getQuestionVos() {
        return questionVos;
    }

    public void setQuestionVos(List<SurveyQuestionVo> questionVos) {
        this.questionVos = questionVos;
    }

    public List<SurveyQuestionVo> getWendas() {
        return wendas;
    }

    public void setWendas(List<SurveyQuestionVo> wendas) {
        this.wendas = wendas;
    }
}
