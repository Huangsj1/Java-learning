package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

// 注解为过滤器，过来所有请求
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    // 初始化方法，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    // 拦截请求之后调用，调用多次，调用完后需要放行
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求-----");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("放行后-----");
    }

    // 结尾销毁方法，只执行一次
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
