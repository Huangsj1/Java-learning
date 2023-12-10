# 一、快速入门

![[11723f73daaf86f127f655e144ab987.jpg]]
![[621d06690d34aa0311d24f7f1f391dd.jpg|210]]![[8a8a08c738bbf32568cb671b8e73f50.jpg|210]]![[45709c694f1bce19a91a81d52096106.jpg|210]]

### 1. 起步依赖

![[b901b8f622cd71250c0ad5322011e51.jpg]]

### 2. 内嵌Tomcat服务器

基于Springboot开发的Web应用程序，内置了tomcat服务器，启动类运行时会自动启动内嵌tomcat服务器

# 二、分层解耦

## 1. 三层架构

![[ba0b30ec44c82442a0b66af9ba53f2c.jpg]]

![[fd197d2c99fedb2842e44676d8a3319.jpg]]![[acf4d42c1968b28b1fe6668e5e43f4e.jpg]]

## 2. 分层解耦

Controller层需要创建Server层的对象，Server层的对象需要创建Dao层的对象，他们之间的耦合度较高，且如果Dao/Server的对象变化就需要重新修改代码

![[77f7eff3ae40d894c91b48baf346b01.jpg]]

1. **IOC 控制反转**：将Server层和Dao层的实现类交给IOC容器管理，动态创建对象放到容器中供上层使用
	* 在类上面加上 `@Component`
2. **DI 依赖注入**：为Controller和Server注入运行时依赖的对象，即IOC容器会在运行时创建bean对象并赋值给变量
	* 在类内需要用到的下层变量上面加上 `@AutoWired`

![[2e838adf621e9ea51312c47dd5d235d.jpg]]

### 1. IOC 注解

除了在类前面注解 `@Component` 还可以根据对应的层次来声明不同的注解

![[b3309a7b8114d6741b946c5cbb94488.jpg]]

一个类对应的bean对象的名字默认为类名（小写开头），但是可以在注解IOC容器中的bean对象语句后面加上新的名字 `@Component("重命名")`

### 2. DI 依赖注入

由于使用依赖注解的时候一般用的是接口名，而 `@Autowired` 默认按照类型自动装配，所以当存在多个实现了相同接口的不同类时会报错不知道用哪个类

![[a7881b66964e4ade7dac201f823b159.jpg]]

# 三、配置文件

## 1. 配置文件属性

SpringBoot支持三种格式配置文件：`.yaml` -> `.yml`(主流) -> `.properties`

![[4b946cb3d8d59f35e5738e34fd09485.jpg]]

## 2. Java系统属性 和 命令行参数配置

![[2c5bbfc62801c2d990bd94ccf9d38fb.jpg]]

点击运行的三角形左边的“当前运行的文件”的下选箭头，选择“Edit Configurations”，再点击“Modify options”找到 “Add VM optoins” 和 “Program arguments”分别配置Java系统属性 和 命令行参数（后者优先级更高）

![[Pasted image 20231209155234.png]]

* 优先级从低到高：`.yaml` -> `.yml` -> `.properties` -> `java系统属性` -> `命令行参数`

# 四、Bean 管理

## 1. 获取 bean

SpringBoot中的bean对象**默认是单例的**（同名的bean只有一个实例），在启动的时候就会创建好这些bean对象，所以多次获取到的都是同一个bean对象

bean对象的名称就是**类名**

![[3f94ee884d7836f453fb6e362660871.jpg]]

## 2. bean 的作用域

1. 默认情况下bean对象的作用域是 `@Scope(singleton)` 的，是在**IOC容器启动的时候创建的**，且**只创建一次**；可以通过 `@Lazy` 来**延迟到第一次使用的时候再创建**
2. 可以设置 `@Scope("prototype")` ，使得使用该bean的时候都会**创建一个新的实例**（一般不用）

## 3. 第三方 bean

![[9bcbce8fe2cc0652ae49f8570d1d814.jpg]]

1. 将第三方对象**放到IOC中作为bean对象**：可以在 *SpringBoot启动程序* / 定义的 *配置类* 中定义带有 `@Bean` 的函数来返回该对象，这样SpringBoot启动的时候就会调用该函数将该bean对象放到IOC容器中
	* 自定义类型的话是通过加上注解 `@Component, @RestController, @Service` 等来实现的
	* 第三bean的名称默认是定义的**方法名**，但也可以通过 `@Bean`注解的name/value属性来自定义名称
1. **第三方bean需要依赖注入**：直接在该函数上加上需要注入的类型的参数即可
	* 自定义类型的话是通过直接声明需要注入的变量作为成员，加上`@Autowired`即可

## 4. Bean 注解的使用

1. 当对象是自定义类型的，使用 `@Component` 及其衍生注解来定义
2. 当对象是第三方引入的，使用 `@Bean` 注解来定义

# 五、SpringBoot原理

## 1. 起步依赖

基于**maven的依赖传递**，使得SpringBoot可以不用像Spring一样需要考虑所有的依赖及其对应的版本匹配

## 2. 自动配置

当Spring容器启动后，一些配置类、bean对象就自动存入到了IOC容器中，不需要手动声明

### 1. 原理

SpringBoot只能扫描当前包 `com.itheima` 及其子包中的来创建bean对象；需要为其他导入的模块的类创建bean对象，在启动类中有以下两种方式：

1. `@ComponentScan({"com.itheima", "com.google", ...})` 扫描所有需要导入的包
	* ![[f98f175a882b286e5a9271b10614cd6.jpg]]
2. `@Import({xxx.class, xx.class})` 导入需要导入的类（其中第四种方法是需要提供包的人将需要导入的类添加到一个自定义的注解 `EnableXxx`上面 `@Import(xxx.class)`，然后在使用的时候在启动类上面**添加导入的模块提供的自定义的注解** `@EnableXxx`）
	* ![[927400028747bdd79bb4566fc90d93e.jpg]]

但是自己手动导入第三方jar包中的所有需要导入的类太麻烦，所以Spring规定**第三方提供jar包/依赖时一定要有一个特定的文件夹** `META-INF`，这样**Spring的启动类就会去扫描里面的两个文件的配置信息**（启动类的注解 `@EnableAutoConfiguration`），将配置的需要导入的类交给IOC管理，实现自动配置（可以手动导入jar包作为依赖，也可以在 `pom.xml` 中自动导入依赖）

![[72b779f273320d8a58820a2268983f3.jpg]]
![[85fd2888f4495c87143b9f995a448b4.jpg]]

但并不是所有配置文件中声明的类都会被注册为IOC容器的bean，因为这些类除了有 `@Bean` 注解，还会有条件注解 `@Conditional`，满足条件的才会注册对应的bean对象

![[7c8671da01e6416f509fd0444f51d77.jpg]]
![[9f6b2700928964a03ab64f37840fe4c.jpg]]

### 2. 自定义 starter

starter模块含该依赖的所有依赖，以及autoconfigure模块（包含自动配置的类）

![[cda7a2444e5906bec65d2a62652a993.jpg]]

1. `starter`模块负责在 `pom.xml` 中导入依赖 `autoconfigure`
2. `autoconfigure`模块负责将**实际需要用到的类给放到另一个配置类中，配置成bean对象**（配置类添加注解 `@Configuration`；同时定义函数返回实际用到的类对象，且为该函数添加注解 `@Bean`）；之后在 `resources/META_INF.spring`文件中**写入配置类的全名**，这样其他使用者就可以扫描到该配置类从而注入配置类中定义的实际用到的bean对象
	1. 里面用到的所有类都不能再用 `@Component` 来定义为bean对象，因为提供给别人使用的时候不会再扫描这个包，而是查看配置文件来加入配置类中的bean对象
3. 这样其他使用者在导入这两个模块后，可以直接在 `pom.xml` 加入 `starter`依赖，就可以在需要使用该类的时候直接声明，同时加上 `@Autowired`来自动注入

![[cb8e6963fea794c95e8d929e4b6d9a1.jpg]]

# 六、Web后端开发 总结

![[0364af64aceb5d613b4e74dee28ea10.jpg]]
![[f1f6679356a6a67a5aab550e67b8f9c.jpg]]