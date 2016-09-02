package com.wsy.webseed.service;

import com.wsy.webseed.domain.StatisticsVo;
import com.wsy.webseed.domain.SurveyStatisticsVo;

import java.util.List;

/**
 * Created by wangsiyuan1 on 2016/8/15.
 */
public interface SurveyStatisticsService {
    public void save(String statistics, String questionStatistics, String ip);

    public StatisticsVo queryStatisticsByPaperId(Long paperId);

    public List<SurveyStatisticsVo> queryStatisticsVoByPaperId(Long paperId);

    public String createCsvFile(Long paperId);
}
