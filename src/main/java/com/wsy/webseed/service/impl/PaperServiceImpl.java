package com.wsy.webseed.service.impl;

import com.wsy.webseed.common.exception.BussinessException;
import com.wsy.webseed.dao.PaperMapper;
import com.wsy.webseed.dao.base.BaseMapper;
import com.wsy.webseed.domain.SurveyPaperVo;
import com.wsy.webseed.domain.SurveyQuestionVo;
import com.wsy.webseed.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    PaperMapper paperMapper;


    @Autowired
    BaseMapper baseMapper;

    @Override
    @Transactional
    public void savePaper(SurveyPaperVo paper) {
        paper.setIsPublish(SurveyPaperVo.IS_NOT_PUBLISH);
        paper.setId(baseMapper.getSeqSurveyPk());
        paper.setIsDel(SurveyPaperVo.IS_NOT_DEL);
        int count = paperMapper.save(paper);
        if (count != 1) {
            throw new BussinessException("insert survey_paper count不等于1");
        }
    }

    @Override
    @Transactional
    public void updPaper(SurveyPaperVo paper) {
        int count = paperMapper.update(paper);
        if (count != 1) {
            throw new BussinessException("update survey_paper count不等于1");
        }
    }

    @Override
    public List<SurveyPaperVo> query(Map<String, Object> param) {
        param.put("isDel", SurveyPaperVo.IS_NOT_DEL);
        List<SurveyPaperVo> list = paperMapper.query(param);
        for(SurveyPaperVo paper: list) {
            List<SurveyQuestionVo> questions = queryByPaperId(paper.getId());
            if(questions.size() > 0) {
                paper.setConfig(SurveyPaperVo.IS_CONFIG);
            } else {
                paper.setConfig(SurveyPaperVo.IS_NOT_CONFIG);
            }
        }
        return list;
    }

    @Override
    public SurveyPaperVo queryById(Long id) {
        return paperMapper.queryById(id);
    }

    @Override
    @Transactional
    public void del(Long id) {
        SurveyPaperVo paper = new SurveyPaperVo();
        paper.setId(id);
        paper.setIsDel(SurveyPaperVo.IS_DEL);
        int count = paperMapper.update(paper);
        if (count != 1) {
            throw new BussinessException("insert survey_paper count不等于1");
        }
    }

    @Override
    @Transactional
    public void publish(Long id) {
        SurveyPaperVo paper = new SurveyPaperVo();
        paper.setId(id);
        paper.setIsPublish(SurveyPaperVo.IS_PUBLISH);
        int count = paperMapper.update(paper);
        if (count != 1) {
            throw new BussinessException("insert survey_paper count不等于1");
        }
    }

    @Override
    public List<SurveyQuestionVo> queryByPaperId(Long paperId) {
        return paperMapper.queryByPaperId(paperId);
    }

    @Override
    @Transactional
    public void config(Long paperId, String[] questionIds) {
        //先删除试卷和试题的关系
        paperMapper.delPaperQuestionRelation(paperId);
        //在插入新的关系
        for (String questionId: questionIds) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("id", baseMapper.getSeqSurveyPk());
            param.put("paperId", paperId);
            param.put("questionId", Long.parseLong(questionId));

            int count = paperMapper.savePaperQuestionRelation(param);
            if(count != 1) {
                throw new BussinessException("insert survey_paper_question count不等于1");
            }
        }
    }
}
