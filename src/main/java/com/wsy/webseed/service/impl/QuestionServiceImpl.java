package com.wsy.webseed.service.impl;

import com.wsy.webseed.common.exception.BussinessException;
import com.wsy.webseed.dao.QuestionMapper;
import com.wsy.webseed.domain.SurveyQuestionVo;
import com.wsy.webseed.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wangsiyuan1 on 2016/8/18.
 */
@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionMapper questionMapper;

    @Override
    @Transactional
    public void saveQuestion(SurveyQuestionVo surveyQuestion) {
        int count = questionMapper.save(surveyQuestion);
        if(count != 1) {
            throw new BussinessException("保存count不等于1");
        }
    }

    @Override
    public List<SurveyQuestionVo> query(Map<String, Object> param) {
        return questionMapper.query(param);
    }
}
