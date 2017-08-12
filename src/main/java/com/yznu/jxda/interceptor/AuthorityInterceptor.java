package com.yznu.jxda.interceptor;

import com.yznu.jxda.utils.Constant;
import com.yznu.jxda.utils.Utils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 刘剑银 on 2017/8/12.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

@Component
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String url = httpServletRequest.getRequestURI().toString();

        if (url.startsWith("/resource")) {
            HttpSession session = httpServletRequest.getSession();
            if (Utils.isNull(session.getAttribute(Constant.SessionKey.CURRENT_USER))) {
                httpServletResponse.sendRedirect("/login");
                return false;
            }
        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
