package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    // 自动注入请求头，用来获取JWT令牌中的token的信息
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    // 环绕注解（要计算执行时间）
    @Around("@annotation(com.itheima.annotation.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 一、获取各种信息
        // 1.操作人ID -> 当前登录员工ID
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer operateUser = (Integer) claims.get("id");

        // 2.操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 3.操作类名
        String className = joinPoint.getTarget().getClass().getName();

        // 4.操作方法名
        String methodName = joinPoint.getSignature().getName();

        // 5.操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        // 二、调用原方法执行
        long begin = System.currentTimeMillis();
        // 调用原始方法运行
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        // 返回值的JSON格式
        String returnValue = JSONObject.toJSONString(result);
        // 操作耗时
        Long costTime = end - begin;

        // 三、记录日志操作
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志：{}", operateLog);

        // 返回执行结果
        return result;
    }
}
