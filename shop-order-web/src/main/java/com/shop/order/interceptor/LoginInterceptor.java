package com.shop.order.interceptor;

import com.shop.common.utils.CookieUtils;
import com.shop.pojo.TbUser;
import com.shop.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Value(value = "USER_TOKEN")
    private String USER_TOKEN;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String cookieValue = CookieUtils.getCookieValue(httpServletRequest, USER_TOKEN);
        System.out.println("cookieValue = " +cookieValue);
        cookieValue = "1722";

        // 跳转到登录页面,需要保存用户现在的请求地址，如果登录成功，应该跳转到这个地址上
        String redirectURL = httpServletRequest.getRequestURL().toString();

        if (StringUtils.isBlank(cookieValue)) {
            httpServletResponse.sendRedirect("http://localhost:82/page/login.html?redirectURL=" + redirectURL);
            return false;
        }
        TbUser user = userService.queryUserByToken(cookieValue);
        if (user == null) {
            httpServletResponse.sendRedirect("http://localhost:82/page/login.html?redirectURL=" + redirectURL);
            return false;
        }
        httpServletRequest.setAttribute("user", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
