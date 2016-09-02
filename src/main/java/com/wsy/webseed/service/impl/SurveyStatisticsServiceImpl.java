package com.wsy.webseed.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wsy.webseed.common.exception.BussinessException;
import com.wsy.webseed.dao.SurveyStatisticsMapper;
import com.wsy.webseed.dao.SurveyStatisticsQuestionMapper;
import com.wsy.webseed.dao.base.BaseMapper;
import com.wsy.webseed.domain.SurveyStatisticsQuestionVo;
import com.wsy.webseed.domain.SurveyStatisticsVo;
import com.wsy.webseed.service.SurveyStatisticsService;
import com.wsy.webseed.util.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
@Service
public class SurveyStatisticsServiceImpl implements SurveyStatisticsService {

    @Autowired
    SurveyStatisticsMapper surveyStatisticsMapper;
    @Autowired
    SurveyStatisticsQuestionMapper surveyStatisticsQuestionMapper;
    @Autowired
    BaseMapper baseMapper;

    @Override
    @Transactional
    public void save(String statistics, String questionStatistics, String ip) {
        SurveyStatisticsVo statisticsVo = JSON.parseObject(statistics, SurveyStatisticsVo.class);
        statisticsVo.setIp(ip);
        JSONArray array = JSON.parseArray(questionStatistics);
        List<SurveyStatisticsQuestionVo> list = new ArrayList<SurveyStatisticsQuestionVo>();
        for(Object obj: array) {
            JSONObject o = (JSONObject) obj;
            SurveyStatisticsQuestionVo ques = JSON.parseObject(o.toJSONString(), SurveyStatisticsQuestionVo.class);
            list.add(ques);
        }
        if(statisticsVo != null && list.size() > 0) {
            statisticsVo.setId(baseMapper.getSeqSurveyPk());
            int count = surveyStatisticsMapper.save(statisticsVo);
            if(count != 1) {
                throw new BussinessException("insert survey_statistics count != 1");
            }
            long statisticsId = statisticsVo.getId();
            for(SurveyStatisticsQuestionVo ques: list) {
                ques.setStatisticsId(statisticsId);
                count = surveyStatisticsQuestionMapper.save(ques);
                if(count != 1) {
                    throw new BussinessException("insert survey_statistics_questionMapper count != 1");
                }
            }
        } else {
            throw new BussinessException("问卷调查数据错误");
        }
    }
}
