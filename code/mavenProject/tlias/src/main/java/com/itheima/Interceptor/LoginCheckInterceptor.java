package com.itheima.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// 定义拦截器
@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url: {}", url);

        // 2.判断url是否包含login，包含就放行进入登录判断
        //  如果在`WebConfig`中排除了地址"/login"就可以不用判断
        if (url.contains("login")) {
            log.info("登陆操作，放行...");
            return true;
        }

        // 3.获取请求头中的令牌(token)
        String jwt = request.getHeader("token");

        // 4.判断令牌是否存在，不存在就返回错误结果（未登录）
        //  hasLength包括null和0长度的判断
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换成JSON对象 --- 需要配置fastJSON依赖
            String notLogin = JSONObject.toJSONString(error);
            // 将JSON信息放到响应体中
            response.getWriter().write(notLogin);
            return false;
        }

        // 5.解析token，解析失败就返回错误结果（未登录）
        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {     // jwt解析失败会出现异常
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        // 6.解析令牌成功，放行
        log.info("解析令牌成功，放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
