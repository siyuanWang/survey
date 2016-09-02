package com.wsy.webseed.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wsy.webseed.common.exception.BussinessException;
import com.wsy.webseed.dao.SurveyStatisticsMapper;
import com.wsy.webseed.dao.SurveyStatisticsQuestionMapper;
import com.wsy.webseed.dao.base.BaseMapper;
import com.wsy.webseed.domain.*;
import com.wsy.webseed.service.QuestionService;
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
    @Autowired
    QuestionService questionService;

    @Override
    @Transactional
    public void save(String statistics, String questionStatistics, String ip) {
        SurveyStatisticsVo statisticsVo = JSON.parseObject(statistics, SurveyStatisticsVo.class);
        statisticsVo.setIp(ip);

//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("paperId", statisticsVo.getPaperId());
//        param.put("ip", statisticsVo.getIp());
//        List<SurveyStatisticsVo> data = surveyStatisticsMapper.query(param);
//        if(data.size() > 0) {
//            throw new BussinessException("您已经参加过调查问卷,不能重复提交");
//        }

        JSONArray array = JSON.parseArray(questionStatistics);
        List<SurveyStatisticsQuestionVo> list = new ArrayList<SurveyStatisticsQuestionVo>();
        for (Object obj : array) {
            JSONObject o = (JSONObject) obj;
            SurveyStatisticsQuestionVo ques = JSON.parseObject(o.toJSONString(), SurveyStatisticsQuestionVo.class);
            list.add(ques);
        }
        if (statisticsVo != null && list.size() > 0) {
            statisticsVo.setId(baseMapper.getSeqSurveyPk());
            int count = surveyStatisticsMapper.save(statisticsVo);
            if (count != 1) {
                throw new BussinessException("insert survey_statistics count != 1");
            }
            long statisticsId = statisticsVo.getId();
            for (SurveyStatisticsQuestionVo ques : list) {
                ques.setStatisticsId(statisticsId);
                count = surveyStatisticsQuestionMapper.save(ques);
                if (count != 1) {
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
        for (SurveyStatisticsVo vo : data) {//循环每个用户的数据
            long statisticsId = vo.getId();
            List<SurveyStatisticsQuestionVo> questionStatistics = surveyStatisticsQuestionMapper.queryByPaperId(statisticsId);//获得试题答案集合
            for (SurveyStatisticsQuestionVo op : questionStatistics) {//循环试题答案
                List<SurveyQuestionVo> list = map.get(op.getQuestionId() + "");
                if (list == null) {
                    List<SurveyQuestionVo> questions = new ArrayList<SurveyQuestionVo>();
                    questions.add(merge(op));
                    map.put(op.getQuestionId() + "", questions);
                } else {
                    list.add(merge(op));
                }
            }
        }

        List<SurveyQuestionVo> questionVos = new ArrayList<SurveyQuestionVo>();
        //循环每个试题
        for (String key : map.keySet()) {
            List<SurveyQuestionVo> list = map.get(key);
            SurveyQuestionVo ques = list.get(0);
            if (ques.getModeType() == SurveyQuestionVo.WEN_DA) continue;
            JSONArray array = JSON.parseArray(ques.getOptions());
            List<OptionVo> optionVos = new ArrayList<OptionVo>();
            for (int i = 0, length = array.size(); i < length; i++) {
                String option = (String) array.get(i);
                OptionVo o = new OptionVo();
                o.setContent(option);
                o.setTitle(SurveyQuestionVo.OPTION_ARRAY[i]);
                o.setNum(0);
                optionVos.add(o);
            }

            //循环每个答案
            for (SurveyQuestionVo vo : list) {
                JSONArray choose = JSON.parseArray(vo.getAnswer());//获得答案jsonArray
                for (int j = 0, length1 = choose.size(); j < length1; j++) {
                    String a = choose.getString(j);
                    OptionVo o = optionVos.get(Integer.parseInt(a) - 1);
                    int num = o.getNum();
                    o.setNum(++num);
                }
            }

            ques.setOpts(optionVos);
            questionVos.add(ques);
        }

        returnVo.setQuestionVos(questionVos);
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

    @Override
    public String createCsvFile(Long paperId) {
        StringBuffer sb = new StringBuffer();
        sb.append("试卷ID,试题ID,试题题干,试题类型,试题选项,试题答案,选项选择数量\t\r");
        StatisticsVo statisticsVo = queryStatisticsByPaperId(paperId);
        for (SurveyQuestionVo vo : statisticsVo.getQuestionVos()) {
            List<OptionVo> options = vo.getOpts();
            if(options != null) {
                for(OptionVo op : options) {
                    sb.append(paperId).append(",");
                    sb.append(vo.getId()).append(",");
                    sb.append(vo.getTitle()).append(",");
                    sb.append(SurveyQuestionVo.modeMap.get(vo.getModeType())).append(",");
                    sb.append(op.getContent()).append(",");
                    sb.append(op.getTitle()).append(",");
                    sb.append(op.getNum()).append(",").append("\t\r");
                }
            }

        }

        return sb.toString();
    }

    private SurveyQuestionVo merge(SurveyStatisticsQuestionVo op) {
        SurveyQuestionVo q = questionService.queryById(op.getQuestionId());
        q.setAnswer(op.getAnswer());
        return q;
    }
}
