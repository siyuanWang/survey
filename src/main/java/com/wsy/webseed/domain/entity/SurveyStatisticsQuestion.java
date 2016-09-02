package com.wsy.webseed.domain.entity;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
public class SurveyStatisticsQuestion {
    private Integer questionMode;
    private String answer;
    private Long questionId;
    private Long paperId;
    private Long statisticsId;

    public Integer getQuestionMode() {
        return questionMode;
    }

    public void setQuestionMode(Integer questionMode) {
        this.questionMode = questionMode;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Long getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(Long statisticsId) {
        this.statisticsId = statisticsId;
    }
}
