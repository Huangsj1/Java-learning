package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1.获取请求url
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getRequestURL().toString();
        log.info("请求的url: {}", url);

        // 2.判断url是否包含login，包含就放行进入登录判断
        if (url.contains("login")) {
            log.info("登陆操作，放行...");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 3.获取请求头中的令牌(token)
        String jwt = req.getHeader("token");

        // 4.判断令牌是否存在，不存在就返回错误结果（未登录）
        //  hasLength包括null和0长度的判断
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换成JSON对象 --- 需要配置fastJSON依赖
            String notLogin = JSONObject.toJSONString(error);
            // 将JSON信息放到响应体中
            resp.getWriter().write(notLogin);
            return;
        }

        // 5.解析token，解析失败就返回错误结果（未登录）
        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {     // jwt解析失败会出现异常
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        // 6.解析令牌成功，放行
        log.info("解析令牌成功，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
