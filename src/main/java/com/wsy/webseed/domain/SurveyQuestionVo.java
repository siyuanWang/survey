package com.wsy.webseed.domain;

import com.wsy.webseed.domain.entity.SurveyQuestion;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
public class SurveyQuestionVo extends SurveyQuestion implements Serializable{
    public static final int IS_DEL = 1;
    public static final int IS_NOT_DEL = 0;

    private List<String> optionStrs;

    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getOptionStrs() {
        return optionStrs;
    }

    public void setOptionStrs(List<String> optionStrs) {
        this.optionStrs = optionStrs;
    }
}
