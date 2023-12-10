# 0. 开发安排

![[386fcd0d139c58ae7fc52db66bf7b6a.jpg]]

# 一、Maven准备

可用于构建和管理Java项目

![[44569b37328a553eaf9e73df8e240d8.jpg]]

## 1. 环境安装配置

[参考](https://blog.csdn.net/u012660464/article/details/114113349)

1. 在[Maven官网](https://maven.apache.org/download.cgi)下载 `**bin.zip` 压缩包并解压
2. 配置本地仓库：修改 `conf/settings.xml` 中的 `<localRepository>` 为指定目录 `<localRepository>D:\develop\maven\apache-maven-3.9.6\mvn_repo</localRepository>`
3. 配置阿里云私服：修改 `conf/settings.xml` 中的 `<mirrors>` 添加阿里云私服
4. 配置环境遍历：在系统环境变量中新建 `MAVEN_HOME` 为maven的解压目录，并在环境变量 `path` 中加入该 `%MAVEN_HOME%/bin`

## 2. IDEA集成Maven

### 1. 配置Maven环境

1. 退出当前项目，在Customize下选择 All settings...
2. 在 `Build,Execution,Deployment` 下的 `Build Tools/Maven` 中设置 Maven home path为maven目录安装路径，同时设置User settings file为maven目录下的`conf\settings.xml`
3. 在 `Build Tools/Maven/Runner` 中设置 JRE 为对应版本（17）
4. 在 `Compiler/Java Compiler`中选择 Project bytecode version为对应版本（17）

![[666044bf13b2000ee2e17d300c8dc7d.jpg]]

### 2. 创建Maven项目

1. 在项目下新建Maven模块，选择 `Maven Archetype`，右边的 `Archetype` 选择结尾为 `quickstart` 的来创建

![[c0fee1c28dff5d1c1a24fd8e3a3115e.jpg]]

### 3. 导入Maven项目

1. 将模块放到项目文件夹下
2. 在IDEA右侧选择 Maven，点击➕选择导入文件夹下的 `pom.xml` 文件

![[0281514194901d58d2a09b8a9f714a2.jpg]]

## 3. 依赖管理

### 1. 依赖配置

* 依赖：当前项目运行所需要的jar包，一个项目可以引入多个依赖
* 配置：
	1. 在模块下的 `pom.xml` 中编写 `<dependencies>` 标签，里面使用 `<dependency>` 引入坐标
	2. 在[Maven Repository](https://mvnrepository.com/)查找需要的依赖，复制对应的 `<dependency>`代码到 `pom.xml` 下
	3. 点击下载maven依赖按钮，然后在右侧的maven处刷新，引入最新坐标

### 2. 依赖传递

依赖具有传递性

![[473510d22042c920f15d4970a227383.jpg]]

在 `pom.xml` 文件下右键选择 `Diagrams/Show Diagram...` 可以查看依赖关系

#### 排除依赖

![[b18189e28281e6659c3e338d6c01aa7.jpg]]

在 `pom.xml` 下的 `<dependency>` 下可以添加 `<exclusions><exclusion></exclusion></exclusions>`来指定需要排除的依赖名

```xml
<dependency>  
	<groupId>ch.qos.logback</groupId>  <!-- 包结构 -->
	<artifactId>logback-classic</artifactId>  <!-- 项目名 -->
	<version>1.2.10</version>  <!-- 版本 -->
	<scope>test</scope>  <!-- 依赖作用范围 -->
	<!-- 排除依赖-->  
	<exclusions>  
		<exclusion>      
		    <groupId>org.slf4j</groupId>  
		    <artifactId>slf4j-api</artifactId>  
	    </exclusion>  
	</exclusions>
</dependency>
```

### 3. 依赖范围

* 通过设置 `<dependency>` 下的 `<scope>` 来设置以来的作用范围：
	1. main文件夹范围内
	2. test文件夹范围内
	3. package是否参与打包运行

![[16929337539d41da256f4e5085bb924.jpg]]

### 4. 生命周期

![[7a6ebf39e0f29725787e6f677edc3ed.jpg|315]]![[fe70c246734774962398ffcbf9234dc.jpg|315]]

1. clean：清理
2. compile：编译
3. test：测试（执行测试文件）
	1. 在测试函数上面添加 `@Test`（报错就改 `pom.xml` 的依赖 `junit` 版本为4.13.2）
4. package：打包，结果存放在`target/`下，后缀为 .jar
5. install：安装到对应的maven仓库的包结构文件夹下，可以给别人使用

上面的操作都是通过插件来实现的

![[6542e7b34f635cb6da0c503d74907da.jpg]]