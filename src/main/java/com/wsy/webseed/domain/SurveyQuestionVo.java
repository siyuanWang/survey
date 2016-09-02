package com.wsy.webseed.domain;

import com.wsy.webseed.domain.entity.SurveyQuestion;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
public class SurveyQuestionVo extends SurveyQuestion implements Serializable{
    public static final int IS_DEL = 1;
    public static final int IS_NOT_DEL = 0;

    public static final int SINGLE = 1;//单选题
    public static final int DOUBLE = 2;//多选题
    public static final int WEN_DA = 3;//问答题

    public static final Map<Integer, String> modeMap = new HashMap<Integer, String>(){
        {
            put(1, "单选题");
            put(2, "多选题");
            put(3, "问答题");
        }
    };

    public static final String[] OPTION_ARRAY = {"A", "B", "C", "D", "E", "F", "G", "H"};

    private List<OptionVo> opts;

    private List<String> optionStrs;

    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<OptionVo> getOpts() {
        return opts;
    }

    public void setOpts(List<OptionVo> opts) {
        this.opts = opts;
    }

    public List<String> getOptionStrs() {
        return optionStrs;
    }

    public void setOptionStrs(List<String> optionStrs) {
        this.optionStrs = optionStrs;
    }
}
