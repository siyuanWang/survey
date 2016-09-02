package com.wsy.webseed.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wsy.webseed.common.exception.BussinessException;
import com.wsy.webseed.dao.SurveyStatisticsMapper;
import com.wsy.webseed.dao.SurveyStatisticsQuestionMapper;
import com.wsy.webseed.dao.base.BaseMapper;
import com.wsy.webseed.domain.StatisticsVo;
import com.wsy.webseed.domain.SurveyQuestionVo;
import com.wsy.webseed.domain.SurveyStatisticsQuestionVo;
import com.wsy.webseed.domain.SurveyStatisticsVo;
import com.wsy.webseed.service.SurveyStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
@Service
public class SurveyStatisticsServiceImpl implements SurveyStatisticsService {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("paperId", statisticsVo.getPaperId());
        param.put("ip", statisticsVo.getIp());
        List<SurveyStatisticsVo> data = surveyStatisticsMapper.query(param);
        if(data.size() > 0) {
            throw new BussinessException("您已经参加过调查问卷,不能重复提交");
        }

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

    @Override
    public StatisticsVo queryStatisticsByPaperId(Long paperId) {
        StatisticsVo returnVo = new StatisticsVo();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("paperId", paperId);
        //获得用户维度的统计
        List<SurveyStatisticsVo> data = surveyStatisticsMapper.query(param);

        //获得问卷试题维度的统计
        Map<String, List<SurveyQuestionVo>> map = new HashMap<String, List<SurveyQuestionVo>>();
        for(SurveyStatisticsVo vo: data) {//循环每个用户的数据
            long statisticsId = vo.getId();
            List<SurveyStatisticsQuestionVo> questionStatistics = surveyStatisticsQuestionMapper.queryByPaperId(statisticsId);//获得试题答案集合
            for(SurveyStatisticsQuestionVo op: questionStatistics) {//循环试题答案
                List<SurveyQuestionVo> list = map.get(op.getQuestionId()+"");
                if(list == null) {
                    List<SurveyQuestionVo> questions = new ArrayList<SurveyQuestionVo>();
                    questions.add(merge(op));
                    map.put(op.getQuestionId()+"", questions);
                } else {
                    list.add(merge(op));
                }
            }
        }
        returnVo.setMap(map);
        returnVo.setSurveyStatisticsVos(data);

        return returnVo;
    }

    @Override
    public List<SurveyStatisticsVo> queryStatisticsVoByPaperId(Long paperId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("paperId", paperId);
        //获得用户维度的统计
        List<SurveyStatisticsVo> data = surveyStatisticsMapper.query(param);

        return data;
    }

    private SurveyQuestionVo merge(SurveyStatisticsQuestionVo op) {
        SurveyQuestionVo q = new SurveyQuestionVo();
        q.setAnswer(op.getAnswer());
        q.setModeType(op.getQuestionMode());
        q.setId(op.getQuestionId());

        return q;
    }
}
