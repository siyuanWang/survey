package com.wsy.webseed.controller;

import com.wsy.webseed.domain.SysUserVo;
import com.wsy.webseed.service.SysUserService;
import com.wsy.webseed.util.Operation;
import com.wsy.webseed.util.SysConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginIn(HttpServletResponse response, HttpServletRequest request) {

        return "login/login";
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    @ResponseBody
    public void loginCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setContentType("image/jpeg");//输出的文本格式为jpg
            BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);//BufferedImage他是一个类
            Graphics g = image.getGraphics();//绘图工具
            g.setColor(Color.white);
            g.fillRect(0, 0, 100, 30);
            String[] keys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                    "A", "h", "C", "D", "E", "F", "我", "好"};
            String[] fonts = {"新宋体", "微软雅黑"};
            int[] sizes = {18, 19, 20, 21, 22, 23, 24};
            int[] sys = {0, Font.BOLD, Font.ITALIC, Font.BOLD + Font.ITALIC};
            String code = "";
            for (int i = 0; i < 4; i++) {
                String c = keys[(int) (Math.random() * keys.length)];
                String font = fonts[(int) (Math.random() * fonts.length)];
                int size = sizes[(int) (Math.random() * sizes.length)];
                int sty = sys[(int) (Math.random() * sys.length)];
                int r1 = (int) Math.random() * 190;
                int g1 = (int) Math.random() * 190;
                int b1 = (int) Math.random() * 190;
                Color color = new Color(r1, g1, b1);
                g.setFont(new Font(font, sty, size));
                g.setColor(color);
                g.drawString(c, 5 + (i * 24), 28);
                code += c;
            }
            HttpSession session = request.getSession();
            session.setAttribute("code", code);
            ImageIO.write(image, "jpeg", response.getOutputStream());
        } catch (Exception e) {
            LOGGER.error("生成验证码失败:", e);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(HttpServletRequest request, String loginName, String password, String checkcode,HttpSession session) {
        String result = "";
        try {
            String _code = (String)session.getAttribute("code");
            if(StringUtils.isNotBlank(checkcode) && checkcode.equals(_code)) {
                LOGGER.info("验证码正确，验证码=" + _code);
                SysUserVo vo = sysUserService.login(loginName, password);
                if (vo != null) {
                    LOGGER.info("{} 登录成功", loginName);
                    result = Operation.result(Operation.successCode, "登录成功");
                    request.getSession().setAttribute(SysConstant.SESSION_USER_KEY, vo);
                } else {
                    LOGGER.warn("系统日志:[{}] 登录失败.用户名和密码不匹配", loginName);
                    result = Operation.result(Operation.failCode, "登录失败.用户名和密码不匹配");
                }
            } else {
                result = Operation.result(Operation.failCode, "登录失败.验证码错误");
            }
        } catch (Exception e) {
            LOGGER.error("登录服务不可用: {}", e);
            result = Operation.result(Operation.failCode, "登录服务不可用");
        }
        return result;
    }
}
