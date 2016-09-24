package com.wsy.webseed.service.impl;

import com.wsy.webseed.common.exception.BussinessException;
import com.wsy.webseed.dao.PaperMapper;
import com.wsy.webseed.dao.PaperModuleMapper;
import com.wsy.webseed.dao.base.BaseMapper;
import com.wsy.webseed.domain.SurveyPaperModuleVo;
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

    @Autowired
    PaperModuleMapper paperModuleMapper;


    @Override
    @Transactional
    public void savePaper(SurveyPaperVo paper) {
        paper.setIsPublish(SurveyPaperVo.IS_NOT_PUBLISH);
        paper.setId(baseMapper.getSeqSurveyPk());
        paper.setIsDel(SurveyPaperVo.IS_NOT_DEL);
        int count = paperMapper.save(paper);
        if(count != 1) {
            throw new BussinessException("save paper count =" + count);
        }
        for(SurveyPaperModuleVo vo : paper.getModules()) {
            long moduleId = baseMapper.getSeqSurveyPk();
            vo.setId(moduleId);
            count = paperModuleMapper.save(vo);
            if(count != 1) {
                throw new BussinessException("save paper module count =" + count);
            }
            for(SurveyQuestionVo question: vo.getQuestions()) {
                long id = baseMapper.getSeqSurveyPk();
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("id", id);
                param.put("questionId", question.getId());
                param.put("moduleId", moduleId);
                count = paperModuleMapper.saveModuleQuestionRelation(param);
                if(count != 1) {
                    throw new BussinessException("save module question count =" + count);
                }
            }
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
    public SurveyPaperVo queryFullDataById(Long id) {
        SurveyPaperVo vo = queryById(id);
        List<SurveyPaperModuleVo> modules = paperMapper.queryModulesByPaperId(id);
        for(SurveyPaperModuleVo module: modules) {
            List<SurveyQuestionVo> questions = paperMapper.queryQuestionsByModuleId(module.getId());
            module.setQuestions(questions);
        }
        vo.setModules(modules);

        return vo;
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
        return paperMapper.queryQuestionsByPaperId(paperId);
    }

    @Override
    public List<SurveyPaperModuleVo> queryModuleByPaperId(Long paperId) {
        return paperMapper.queryModulesByPaperId(paperId);
    }

    @Override
    public List<SurveyQuestionVo> queryQuestionByModuleId(Long moduleId) {
        return paperMapper.queryQuestionsByModuleId(moduleId);
    }

    @Override
    @Transactional
    public void config(SurveyPaperVo vo) {
        //删除试卷和模块关系
        for(SurveyPaperModuleVo module:vo.getModules()) {
            paperModuleMapper.del(module.getPaperId());
            paperModuleMapper.delModuleQuestionRelation(module.getId());

            for(SurveyQuestionVo ques:module.getQuestions()) {
                Map<String,Object> param = new HashMap<String, Object>();
                param.put("id", baseMapper.getSeqSurveyPk());
                param.put("moduleId", module.getId());
                param.put("questionId", ques.getId());
                paperModuleMapper.saveModuleQuestionRelation(param);
            }
        }
    }

    @Override
    public void saveModule(SurveyPaperModuleVo vo) {
        vo.setId(baseMapper.getSeqSurveyPk());
        paperModuleMapper.save(vo);
    }

    @Override
    public void delModule(Long moduleId) {
        List<SurveyQuestionVo> questions = paperMapper.queryQuestionsByModuleId(moduleId);
        if(questions.size() > 0) {
            throw new BussinessException("请先删除模块下试题");
        }
        paperModuleMapper.del(moduleId);
    }
}
