package com.wsy.webseed.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wsy.webseed.common.exception.BussinessException;
import com.wsy.webseed.domain.SurveyStatisticsQuestionVo;
import com.wsy.webseed.domain.SurveyStatisticsVo;
import com.wsy.webseed.service.SurveyStatisticsService;
import com.wsy.webseed.util.Operation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/statistics")
public class StatisticsController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SurveyStatisticsService surveyStatisticsService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList() {
        return "statistics/";
    }

    @RequestMapping(value = "/save/{paperId}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String save(HttpServletRequest request, @PathVariable Long paperId) {
        String result = "";
        String ip = request.getRemoteAddr();
        String statistics = request.getParameter("surveyStatistics");
        String questionStatistics = request.getParameter("surveyStatisticsQues");
        try {
            if(StringUtils.isBlank(statistics) || StringUtils.isBlank(questionStatistics)) {
                throw new BussinessException("保存问卷入参错误");
            }
            surveyStatisticsService.save(statistics, questionStatistics, ip);
            result = Operation.result(Operation.successCode, "问卷调查保存成功");
        } catch (BussinessException e) {
            LOGGER.error(e.getServiceMsg());
            result = Operation.result(Operation.failCode, e.getServiceMsg());
        } catch (Exception e) {
            LOGGER.error("统计问卷失败, paperId="+paperId);
            result = Operation.result(Operation.failCode, "统计问卷失败");
        }

        return result;
    }
}
