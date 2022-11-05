package com.github.guangyuu.interceptor;

import com.github.guangyuu.common.exception.StoreException;
import com.github.guangyuu.common.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Guangyu
 * @Date 2022/11/5
 * @Description Token拦截器
 **/
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String username = request.getHeader("username");
        if (StringUtils.isAnyBlank(token, username)) {
            throw new StoreException("未经授权的访问");
        }
        try {
            String parseResult = JwtUtils.parseToken(token);
            if (username.equals(parseResult)) {
                return true;
            }
        } catch (Exception e) {
            throw new StoreException("未经授权的访问");
        }
        throw new StoreException("未经授权的访问");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
