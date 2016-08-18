package com.wsy.webseed.controller;

import com.wsy.webseed.common.exception.BussinessException;
import com.wsy.webseed.domain.SurveyQuestionVo;
import com.wsy.webseed.service.QuestionService;
import com.wsy.webseed.util.Operation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/question")
public class QuestionController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String save(HttpServletRequest request, SurveyQuestionVo vo) {
        String result = "";
        try {
            questionService.saveQuestion(vo);
            result = Operation.result(Operation.successCode, "新增问题成功");
        } catch (BussinessException e) {
            LOGGER.error("新增问题失败: {}", e);
            result = Operation.result(Operation.failCode, "新增问题失败");
        } catch (Exception e) {
            LOGGER.error("新增问题服务不可用: {}", e);
            result = Operation.result(Operation.failCode, "新增问题服务不可用");
        }
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toSavePage(String title, Model model) {

        return "question/add";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String query(String title, Model model) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(title)) {
            param.put("title", title);
        }
        List<SurveyQuestionVo> vos = new ArrayList<SurveyQuestionVo>();

        try {
            vos = questionService.query(param);
        } catch (BussinessException e) {
            LOGGER.error("查询问题列表失败: {}", e);
        } catch (Exception e) {
            LOGGER.error("查询问题列表服务不可用: {}", e);
        }

        model.addAttribute("list", vos);
        model.addAttribute("title", title);

        return "question/list";
    }
}