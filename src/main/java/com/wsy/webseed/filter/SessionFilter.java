package com.wsy.webseed.filter;

import com.wsy.webseed.domain.SysUserVo;
import com.wsy.webseed.util.SysConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession();
        SysUserVo user = (SysUserVo) session.getAttribute(SysConstant.SESSION_USER_KEY);
        LOGGER.info("filter url = {}", request.getRequestURI());
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login/");
            return;
        }
        arg0.setAttribute(SysConstant.SESSION_USER_KEY, user);
        chain.doFilter(arg0, arg1);
    }

    @Override
    public void destroy() {

    }

}
