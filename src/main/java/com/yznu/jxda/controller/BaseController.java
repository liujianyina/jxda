package com.yznu.jxda.controller;

import com.yznu.jxda.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */


public class BaseController {

    /**
     * 请求
     */
    @Autowired
    protected HttpServletRequest request;

    /**
     * 会话
     */
    @Autowired
    protected HttpSession session;

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected HttpSession getSession() {
        return session;
    }

    protected Integer getStatus() {
        if (!Utils.isEmpty(getRequest().getParameter("status"))) {
            try {
                return Integer.valueOf(getRequest().getParameter("status"));
            } catch (Exception e) {
            }
        }
        return -2;
    }
}
