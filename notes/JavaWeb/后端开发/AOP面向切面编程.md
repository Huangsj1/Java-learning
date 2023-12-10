AOP Aspect Oriented Programming，也可以称为面向特定方法编程，在不改变原始方法情况下为方法前后添加操作，其中[[8.反射 & 动态代理#二、动态代理|动态代理]]是最主流的实现方式

![[b1aea5179c95228bfef769c782518c6.jpg]]

# 一、AOP基础

## 1. AOP 快速入门

![[92f0b22eba2842051b7c596ebef791c.jpg]]

（1）`pom.xml` 导入AOP依赖

```xml
<!--AOP依赖-->  
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-aop</artifactId>  
</dependency>
```

（2）在 `main/java/com.itheima/aop`下创建 `TimeAspect` 类，里面定义了计算执行时间的aop函数

1. 类注解 `@Component` 交给IOC容器管理
2. 类注解 `@Aspect` 定义为AOP类
3. 方法注解 `@Around` 定义切入点

```java
@Slf4j  
@Component      // 交给IOC容器  
@Aspect         // AOP类  
public class TimeAspect {  
    @Around("execution(* com.itheima.service.*.*(..))")     // 切入点表达式，这些方法会到这里执行  
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {  
        // 1.记录开始时间  
        long begin = System.currentTimeMillis();  
  
        // 2.调用原始方法运行  
        Object result = joinPoint.proceed();  
  
        // 3.记录结束时间，计算方法耗时  
        long end = System.currentTimeMillis();  
        log.info(joinPoint.getSignature() + "方法执行耗时:{}ms", end - begin);  
  
        return result;  
    }  
}
```

## 2. AOP 核心概念和执行流程

1. 连接点（JoinPoint）：可以被AOP控制的方法
2. 切入点（PointCut）：实际被AOP控制的方法，通过AOP表达式定义
3. 通知（Advice）：封装共性功能的方法
4. 切面（Aspect）：通知与切入点对象的控制关系
5. 目标对象（Target）：通知所作用的对象

![[e8ab9e62960b3f5ada42bcca81854fc.jpg]]

运行的不再是原始的目标对象，而是创建新的代理对象来运行

![[eb6e167f4665868d775b2f0f9782a0c.jpg]]

# 二、AOP进阶

## 1. 通知类型

![[8c45409d93356636a50d9d26115e008.jpg]]

* `@Around` 的后置通知在出现异常后不会执行

### @Pointcut 注解

注解 `@Pointcut` 可以抽取出公共的切入点表达式，需要用时引用即可

```java
@Pointcut("execution(* com.itheima.service.*.*(..))")
public void pt() {}

@Around("pt()")
public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {}
```

## 2. 通知顺序

* 前置通知：类名排序越靠前越先执行
* 后置通知：类名排序越靠后越先执行
* 可以通过在类前面加上注解 `@Order(数字)` 来控制顺序

![[6f21784c331478a9b3bb15c20dfdfe3.jpg]]

## 3. 切入点表达式

![[3d90ca215e0a47920225cb0d3bb8066.jpg]]

### 1. execution

根据方法的描述信息来匹配方法

![[e737d4afc5ed868ba2330c9436459cd.jpg]]

1. `*` 表示**一个**参数/返回类型/包名/类名
2. `..` 表示**任意个**参数/包名
3. 可以用 `&& || !` 来对多个/单个 `execution()` 组合切入点表达式

![[4476e06768d636310487bb5a1d1994c.jpg]]

### 2. @annotation

根据方法得特定注解来匹配方法

1. 自定义一个注解，供其他类/方法使用
2. `@Pointcut("@annotation(注解全类名)")` 可以匹配使用了该注解的方法

![[8254087ea5c0dcad7e3b9f56ba64166.jpg]]

## 4. 连接点

连接点就是可以被AOP控制的方法，Spring中通过 `JointPoint` 抽象连接点，可以用它获取方法的相关信息

![[e538b5620da75fe2e8f5ca0abb93386.jpg]]

# 三、AOP 案例-记录操作日志

这里是对[[案例1.员工部门管理系统]]中的补充

![[4500457602ddfcbf39484cc85ec55b6.jpg]]
![[498ebaec0da23dea7fde4c1b53fae5b.jpg]]

（1）配置 `pom.xml` 文件，加入AOP依赖

```xml
<!--AOP依赖-->  
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-aop</artifactId>  
</dependency>
```

（2）创建记录日志表的数据库

```sql
/*AOP记录操作日志*/  
create table operate_log (  
    id int unsigned primary key auto_increment comment 'ID',  
    operate_user int unsigned comment '操作人ID',  
    operate_time datetime comment '操作时间',  
    class_name varchar(100) comment '操作的类名',  
    method_name varchar(100) comment '操作的方法名',  
    method_params varchar(1000) comment '方法的参数',  
    return_value varchar(2000) comment  '返回值',  
    cost_time bigint comment '方法执行耗时/ms'  
) comment '操作日志表';
```

（3）在 `pojo/` 下引入对应数据库的实体类 `OperateLog`

```java
@Data  
@NoArgsConstructor  
@AllArgsConstructor  
public class OperateLog {  
    private Integer id;     // ID  
    private Integer operateUser;    // 操作人ID  
    private LocalDateTime operateTime;  // 操作时间  
    private String className;   // 操作类名  
    private String methodName;  // 操作方法名  
    private String methodParams;    // 方法参数  
    private String returnValue;     // 方法返回值  
    private Long costTime;      // 操作耗时  
}
```

（4）`mapper/` 下定义用来写入数据库的接口 `OperateLogMapper`

```java
@Mapper  
public interface OperateLogMapper {  
    @Insert("insert into operate_log (operate_user, operate_time, class_name, method_name, method_params, return_value, cost_time) " +  
            "values (#{operateUser}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")  
    public void insert(OperateLog log);  
}
```

（5）在 `main/java/com.itheima/annotation` 下定义注解 `Log`

```java
// 定义注解类（需要加上下面两个元注解）  
@Retention(RetentionPolicy.RUNTIME)     // 运行时生效  
@Target(ElementType.METHOD)             // 作用在方法上  
public @interface Log {  
}
```

（6）在 `aop/` 下定义切面类 `LogAspect`，其使用注解作为切入点方法，通过`OperateLogMapper`类的成员来存日志到数据库中

* 要获取当前用户id，就需要通过request对象从请求头中获取到jwt令牌：可以通过自动注入类型 `HttpServletRequest` 的对象

```java
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
```

（7）最后在 `Controller/` 下为需要执行该AOP操作的方法上面加上注解 `@Log`