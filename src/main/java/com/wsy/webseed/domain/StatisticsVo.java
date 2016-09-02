package com.wsy.webseed.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by wsy on 2016/9/2.
 */
public class StatisticsVo {
    private List<SurveyStatisticsVo> surveyStatisticsVos;
    private Map<String, List<SurveyQuestionVo>> map;

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
}
