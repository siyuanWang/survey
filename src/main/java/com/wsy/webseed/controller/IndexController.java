package com.wsy.webseed.controller;

import com.wsy.webseed.common.exception.BussinessException;
import com.wsy.webseed.domain.SysUserVo;
import com.wsy.webseed.service.SysUserService;
import com.wsy.webseed.util.SysConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/index")
public class IndexController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SysConstant.SESSION_USER_KEY);
        return "redirect:/index/";
    }

    @RequestMapping(value = "/updpassword", method = RequestMethod.GET)
    public String toUpdPassword() {
        return "index/updPass";
    }

    @RequestMapping(value = "/updpassword", method = RequestMethod.POST)
    public String updPassword(Model model, HttpServletRequest request, String origin, String newPass, String confirmPass) {

        try {
            SysUserVo userVo = (SysUserVo) request.getSession().getAttribute(SysConstant.SESSION_USER_KEY);
            if (userVo != null) {
                if (!sysUserService.updatePassword(userVo.getLoginName(), origin, newPass, confirmPass)) {
                    throw new BussinessException("修改密码失败");
                }
            } else {
                throw new BussinessException("当前登录用户为Null");
            }
        } catch (BussinessException e) {
            LOGGER.error("修改密码服务不可用:{}", e.getServiceMsg());
            model.addAttribute("error", "修改密码失败");
            return "index/updPass";
        } catch (Exception e) {
            LOGGER.error("修改密码服务不可用:{}", e);
            model.addAttribute("error", "修改密码失败");
            return "index/updPass";
        }

        return "redirect:/index/";
    }
}
