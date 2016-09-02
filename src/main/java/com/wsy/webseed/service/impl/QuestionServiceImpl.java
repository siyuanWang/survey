package com.wsy.webseed.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wsy.webseed.common.exception.BussinessException;
import com.wsy.webseed.dao.QuestionMapper;
import com.wsy.webseed.dao.base.BaseMapper;
import com.wsy.webseed.domain.SurveyQuestionVo;
import com.wsy.webseed.service.QuestionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangsiyuan1 on 2016/8/18.
 */
@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    BaseMapper baseMapper;

    @Override
    @Transactional
    public void saveQuestion(SurveyQuestionVo surveyQuestion) {
        surveyQuestion.setId(baseMapper.getSeqSurveyPk());
        int count = questionMapper.save(surveyQuestion);
        if(count != 1) {
            throw new BussinessException("保存count不等于1");
        }
    }

    @Override
    @Transactional
    public void updQuestion(SurveyQuestionVo question) {
        int count = questionMapper.update(question);
        if(count != 1) {
            throw new BussinessException("修改count不等于1");
        }
    }

    @Override
    public List<SurveyQuestionVo> query(Map<String, Object> param) {
        param.put("idDel", SurveyQuestionVo.IS_NOT_DEL);
        List<SurveyQuestionVo> list = questionMapper.query(param);
        for(SurveyQuestionVo vo: list) {
            List<String> optionStrs = new ArrayList<String>();
            if(StringUtils.isNotBlank(vo.getOptions())) {
                JSONArray array = JSON.parseArray(vo.getOptions());
                for(int i = 0, length = array.size(); i < length; i++) {
                    String optionStr = (String)array.get(i);
                    optionStrs.add(optionStr);
                    vo.setOptionStrs(optionStrs);
                }
            }
        }

        return list;
    }

    @Override
    public SurveyQuestionVo queryById(Long id) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("isDel", SurveyQuestionVo.IS_NOT_DEL);
        List<SurveyQuestionVo> list = questionMapper.query(param);
        if(list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public void del(Long id) {
        SurveyQuestionVo vo = new SurveyQuestionVo();
        vo.setId(id);
        vo.setIsDel(SurveyQuestionVo.IS_DEL);
        int count = questionMapper.update(vo);
        if(count != 1) {
            throw new BussinessException("修改count不等于1");
        }
    }

}
