* SpringMVC是一种基于Java实现MVC模型的轻量级Web框架（表现层）
	* MVC模式：模型Model（接收视图请求并返回结果）、视图View（页面显示）、控制器Controller（分发器，连接模型和视图）

# 一、入门案例

1. 导入SpringMVC和Servlet**依赖**
2. 创建SpringMVC**控制类**，设置其为**bean对象**，同时定义**请求处理方法**
	* `@Controller`：设定SpringMVC控制器bean
	* `@RequestMapping`：设定当前控制器方法请求访问路径
	* `@ResponseBody`：设置当前控制器响应内容为返回值（json)，无需解析
3. 初始化**SpringMVC环境**（同Spring环境)，定义**配置类**，**扫描**bean对象（需要通过第四步来创建容器，这里只是提供配置和扫描信息）
4. 初始化**Servlet容器**，加载SpringMVC环境，设置SpringMVC请求
	1. `createServletApplicationContext()`：创建 `Servlet` 容器，加载SpringMVC对应的bean放入该容器中，返回容器
	2. `getServletMappings()`：设置SpringMVC对应的请求路径，`/`表示拦截所有请求，都转到SpringMVC执行
	3. `createRootApplicationContext()`：如果创建Servlet容器时需要加载非SpringMVC对应的bean，使用当前方法（同方法1）

![[b0f980a79b3f2f34ea5b71cc7da88dd.jpg]]![[Pasted image 20240116092911.png]]![[Pasted image 20240116092918.png]]![[Pasted image 20240116092931.png]]

![[Pasted image 20240116092941.png]]

## 1. 流程分析

![[213b6fe1299074c01968bb33d7ab63f.jpg]]

## 2. SpringMVC和Spring加载bean对象

1. SpringMVC主要负责加载**控制类**
2. Spring主要负责加载**业务类**和**功能/数据类**

![[44884d5fd2a19b3d7bed1430c6e1611.jpg]]

![[3a5b1e886f9ff714e429d0f035c3ac8.jpg]]![[963f4baaf9abc3f8d7e81254a2826e6.jpg]]![[Pasted image 20240116095626.png]]

# 二、请求响应

![[Pasted image 20240116101936.png]]

可以将注解 `@RequestMapping("请求路径")` 放到控制类上面来设置路径**前缀**

## 1. 参数传递

1. 请求的参数可以直接在函数的**形参中定义相同类型和名称的变量**来接收（对于不同名称参数可以在参数前面加 `@RequestParam("请求参数名")`来绑定）
2. 对于**数组**类型参数可以直接传递多个相同名称的参数来请求
3. 对于**集合**类型参数传的也是多个相同名称的参数，但是需要在接收请求的形参前添加注解 `@RequestParam`
4. 请求**JSON类型数据**（参数放到请求体Body中）：
	1. 在 `pom.xml` 中加入json依赖
	2. 核心配置 `SpringMvcConfig` 类中加上注解 `@EnableWebMvc`
	3. 控制类处理请求的函数的参数前加上 `@RequestBody`（一个处理方法中只能用一次，因为一次请求只有一个请求体）

![[588b25823654e846caf206a6c84904c.jpg]]![[a02a15677a6cd342b40ebeac2111e62.jpg]]![[95f3ed0f89f148cd481d3c054a151bc.jpg]]![[b1ed9a5b4a94f366cf030b30a62842c.jpg]]

![[Pasted image 20240116103000.png]]

### 日期格式

默认可以直接将字符串类型的参数转换为 `Data` 类型的参数，但是要满足格式 `yyyy/MM/dd`，对于其他格式的日期需要在参数前添加注解 `@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")`

![[facd8e2497e46e7160fcb08a2a2286b.jpg]]

## 2. 响应

1. 如果控制类的处理函数前面没有加响应相关的注解，响应的是字符串对应的**页面**（找不到页面就报错）
2. 加注解 `@ResponseBody` 返回的是**请求体**，里面包含return返回的对象（如果是**字符串就返回Text文本**数据，**对象/集合就返回JSON**数据）
	* 里面是类型转换器 `HttpMessageConverter` 接口进行类型转换

![[0eee573231ff499e09a2b8a006230ce.jpg]]

# 三、REST风格

REST是一种风格，可以按照这种风格来进行Web请求

![[610c1e41a3465945faace046a4af6be.jpg]]![[4a441ecdb18f846d98f86d343f0cbb1.jpg]]

控制类的请求响应函数需要做出的修改：

1. 在注解 `@RequestMapping` 里面用 `method=` **添加http请求动作**（`GET/POST/PUT/DELETE`）
2. 如果请求参数在路径变量中需要更改 `@RequestMapping` 中的value值，同时在形参前面添加 `@PathVariable`

![[8069867e86982e18ead32f04912c801.jpg]]![[Pasted image 20240116110642.png]]

![[Pasted image 20240116110701.png]]

## 1. 快速开发

1. 控制类用注解 `@RestController` 替代 `@Controller,@ResponseBody`，并且用 `@RequestMapping("/xxx")` 提取请求路径前缀
2. 请求处理方法用 `@Get(/Post/Put/Delete)Mapping("/{xxx}")` 来代替 `@RequestMapping(value="/{xxx}", method=RequestMethod.GET)`

![[Pasted image 20240116111345.png]]![[Pasted image 20240116111357.png]]

## 2. 开发实例

* 由于当前SpringMVC处理的是所有的请求，对于访问静态页面请求也会跳转到SpringMVC处理，所以需要添加一个**新的配置类来处理静态页面的访问**

![[e2eb86d7e68d8f4e785fe496335e753.jpg]]![[7046dd32e0381b95132ff246ad7182f.jpg]]![[cf4a5a7559aea380e4909ab58972cbe.jpg]]

# 四、统一响应结果

在表现层响应的结果应该统一

* `Object data` 表示返回的数据
* `Integer code` 表示返回的code，里面包含了成功与否的信息
* `String msg` 表示返回的信息，如果出错了就要有报错信息

![[df7e4aad2515fa2bc1c9657c627dfad.jpg]] 