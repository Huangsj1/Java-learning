在不惊动原始设计的基础上为其添加新的功能

![[092738c38d2304ed22bad8f252eb264.jpg]]![[b5b714596161019328296d3218486ba.jpg]]

## 1. 使用流程

1、首先是一些准备工作，包括引入依赖、定义dao接口类和实现类

![[Pasted image 20240115182327.png]]![[Pasted image 20240115182338.png]]

2、接着定义通知类、定义切入点、定义通知、绑定切入点和通知的关系

![[Pasted image 20240115182350.png]]![[Pasted image 20240115182359.png]]![[Pasted image 20240115182410.png]]

3、最后定义通知类为切面类、为SpringConfig配置类添加注解 `@EnableAspectAutoProxy`允许自动代理

![[Pasted image 20240115182420.png]]![[Pasted image 20240115182428.png]]

## 2. 工作原理/过程

![[Pasted image 20240115182440.png]]

只要匹配成功就会创建对原始对象的代理对象来管理原始对象

## 3. 切入点表达式

* 切入点：需要增强的方法
* 切入点表达式：需要增强的方法的描述方式（描述需要增强哪些方法)

![[fa233c448d20ff78fa699ec75dc94fe.jpg]]
![[9eb1c706070deda55d287c2c373016a.jpg]]

![[Pasted image 20240115184025.png]]

## 4. 通知类型

1. 前置通知：`Before`
2. 后置通知：`After`，函数返回前执行，出现异常依旧执行
3. **环绕通知**：`Around`
	1. 里面需要定义形参 `ProceedingJoinPoint pjp` 来获取原本连接点对象，这样就可以在函数内执行原本的函数 `pjp.proceed();`
		* 可以通过 `Signature sn = pjp.getSignature();` 来获取签名，然后通过签名得到类和方法 `sn.getxxx();`
	2. 如果切入的函数有返回值则该函数需要返回 `Object` 类型
4. 返回后通知：`AfterReturning`，函数返回后执行，出现异常不会执行
5. 抛出异常后通知：`AfterThrowing`，出现异常后才执行

![[1182b92fbd89abd0af5bd629c468768.jpg]]

### 通知获取数据

![[Pasted image 20240115190618.png]]

![[57082befe1d76532ac2478ffe497fd9.jpg]]![[058e3f523f3e24ae8b97b5cc07d6731.jpg]]![[d83e7b959e9ac128a86060496bad61b.jpg]]

