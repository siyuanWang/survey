package com.wsy.webseed.service.impl;

import com.wsy.webseed.common.exception.BussinessException;
import com.wsy.webseed.dao.PaperMapper;
import com.wsy.webseed.domain.SurveyPaperVo;
import com.wsy.webseed.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    PaperMapper paperMapper;

    @Override
    @Transactional
    public void savePaper(SurveyPaperVo paper) {
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
        return paperMapper.query(param);
    }

    @Override
    public SurveyPaperVo queryById(Long id) {
        return paperMapper.queryById(id);
    }
}
