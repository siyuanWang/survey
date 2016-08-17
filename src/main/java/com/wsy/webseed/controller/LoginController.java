package com.wsy.webseed.controller;

import com.wsy.webseed.domain.SysUserVo;
import com.wsy.webseed.service.SysUserService;
import com.wsy.webseed.util.Operation;
import com.wsy.webseed.util.SysConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginIn() {
        return "login/login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(HttpServletRequest request, String loginName, String password) {
        String result = "";
        try {
            SysUserVo vo = sysUserService.login(loginName, password);
            if(vo != null) {
                LOGGER.info("{} 登录成功", loginName);
                result = Operation.result(Operation.successCode, "登录成功");
                request.getSession().setAttribute(SysConstant.SESSION_USER_KEY, vo);
            } else {
                LOGGER.warn("系统日志:[{}] 登录失败.用户名和密码不匹配", loginName);
                result = Operation.result(Operation.failCode, "登录失败.用户名和密码不匹配");
            }
        } catch (Exception e) {
            LOGGER.error("登录服务不可用: {}", e);
            result = Operation.result(Operation.failCode, "登录服务不可用");
        }
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        String result = Operation.result(Operation.failCode, "注销失败");
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            result = Operation.result(Operation.successCode, "注销成功");
            LOGGER.debug("user logout sucess");
            request.getSession().setAttribute(SysConstant.SESSION_USER_KEY, null);
        } catch (Exception e) {
            LOGGER.error("logout服务不可用:{}",e);
        }
        return result;
    }

}
