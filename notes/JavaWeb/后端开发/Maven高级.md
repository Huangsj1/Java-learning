# 一、分模块设计

![[aee14f0e8e524b1cca498009a5cb3b6.jpg]]

### 1. tlias-pojo 模块

（1）创建 tlias-pojo 模块，用来存储所有的实体类（无Parent，注意Location路径为项目路径）

![[Pasted image 20231210090255.png]]

（2）tlias-pojo的 `src/main/java/` 下创建包 `com.itheima`，将原本的tlias-web-management的 `pojo/` 文件夹复制过来

![[Pasted image 20231209235456.png|300]]

（3）tlias-pojo下的 `pom.xml` 中添加依赖：

```xml
<dependencies>  
    <dependency>        
	    <groupId>org.projectlombok</groupId>  
        <artifactId>lombok</artifactId>  
        <version>1.18.30</version>  
    </dependency>
</dependencies>
```

（4）tlias-web-management下的 `pom.xml` 中添加 tlias-pojo 的依赖：

```xml
<!--tlias-pojo依赖-->  
<dependency>  
    <groupId>com.itheima</groupId>  
    <artifactId>tlias-pojo</artifactId>  
    <version>1.0-SNAPSHOT</version>  
</dependency>
```

### 2. tlias-utlis

（1）创建 tlias-utils 模块，用来存储所有的实体类（无Parent，注意Location路径为项目路径）

![[Pasted image 20231209235908.png]]

（2）tlias-utils的 `src/main/java/` 下创建包 `com.itheima`，将原本的tlias-web-management的 `utils/` 文件夹复制过来

![[Pasted image 20231210000515.png|300]]

（3）tlias-utils下的 `pom.xml` 中添加依赖：

```xml
<dependencies>  
    <!--JWT令牌配置-->  
    <dependency>  
        <groupId>io.jsonwebtoken</groupId>  
        <artifactId>jjwt</artifactId>  
        <version>0.9.1</version>  
    </dependency>  
    <!--阿里云OSS依赖-->  
    <dependency>  
        <groupId>com.aliyun.oss</groupId>  
        <artifactId>aliyun-sdk-oss</artifactId>  
        <version>3.15.1</version>  
    </dependency>    <dependency>        <groupId>javax.xml.bind</groupId>  
        <artifactId>jaxb-api</artifactId>  
        <version>2.3.1</version>  
    </dependency>    <dependency>        <groupId>javax.activation</groupId>  
        <artifactId>activation</artifactId>  
        <version>1.1.1</version>  
    </dependency>    <!-- no more than 2.3.3-->  
    <dependency>  
        <groupId>org.glassfish.jaxb</groupId>  
        <artifactId>jaxb-runtime</artifactId>  
        <version>2.3.3</version>  
    </dependency>  
    <!--WEB开发依赖-->  
    <dependency>  
        <groupId>org.springframework.boot</groupId>  
        <artifactId>spring-boot-starter-web</artifactId>  
        <version>3.2.0</version>  
    </dependency>  
    <!--lombok依赖-->  
    <dependency>  
        <groupId>org.projectlombok</groupId>  
        <artifactId>lombok</artifactId>  
        <version>1.18.30</version>  
    </dependency></dependencies>
```

（4）tlias-web-management下的 `pom.xml` 中添加 tlias-utils 的依赖：

```xml
<!--tlias-utils的依赖-->  
<dependency>  
    <groupId>com.itheima</groupId>  
    <artifactId>tlias-utils</artifactId>  
    <version>1.0-SNAPSHOT</version>  
</dependency>
```

# 二、继承与聚合

## 1. 继承

### 1. 继承关系实现

提取子工程的公共依赖来继承，在xml文件中的 `<parent>...</parent>`实现

* 打包方式：
	1. jar：普通模块打包，springboot项目基本都是jar包（内嵌tomcat运行）
	2. war：普通web程序打包，需要部署在外部的tomcat服务器运行
	3. pom：父工程/聚合工程，该模块不写代码，仅进行依赖管理

![[a391b07a383b14acb7ff7e440ab8d54.jpg]]

（1）创建父模块 tlias-parent，包含公共依赖

![[Pasted image 20231210085326.png]]

（2）父模块只保留 `pom.xml` 文件，里面包含父工程和设置打包方式为pom（默认jar）

```xml
<parent>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-parent</artifactId>  
    <version>3.2.0</version>  
    <relativePath/> <!-- lookup parent from repository -->  
</parent>  
  
<groupId>com.itheima</groupId>  
<artifactId>tlias-parent</artifactId>  
<version>1.0-SNAPSHOT</version>  
<!--打包方式为pom-->
<packaging>pom</packaging>
```

（3）tlias-utils、tlias-pojo、tlias-web-management中都修改 `pom.xml` 文件的父模块为tlias-parent；同时删除多余重复模块（其中 `relativePath`为父模块的 `pom.xml` 文件相对路径）

```xml
<parent>  
    <groupId>com.itheima</groupId>  
    <artifactId>tlias-parent</artifactId>  
    <version>1.0-SNAPSHOT</version>  
    <relativePath>../tlias-parent/pom.xml</relativePath>  
</parent>
```

（4）接着就可以将公共模块复制到父工程tlias-parent中，删除子工程重复依赖（若父工程和子工程都配置了同一个依赖的不同版本，以子工程为准）

### 2. 版本锁定

![[0acb4688a0a8c587bcf2351ad4c9db7.jpg]]

父工程可以通过配置 `<dependencyManagement>` 包含所有依赖来管理依赖的版本，且不会引入依赖，子工程还是需要配置 `<dependency>` 才能引入依赖，且不用再指定版本号

```xml
<dependencyManagement>  
    <dependencies>
	    <dependency>  
		    ...
		</dependency>
		...
    </dependencies>
</dependencyManagement> 
```

但是这样依赖还是分散开来不便于一起管理，所以可以通过自定义属性值 `<properties>` 来统一设置各个依赖版本号，然后再依赖里面引用该属性值（如果依赖名使用 "-" 分隔，将其改为 "."）

```xml
<properties>  
	  ...
    <jjwt.version>0.9.1</jjwt.version>  
    <aliyun.sdk.oss.version>3.15.1</aliyun.sdk.oss.version>
    ...  
</properties>

<dependencyManagement>  
    <dependencies>        <!--JWT令牌配置-->  
        <dependency>  
            <groupId>io.jsonwebtoken</groupId>  
            <artifactId>jjwt</artifactId>  
            <version>${jjwt.version}</version>  
        </dependency>  
        <!--阿里云OSS依赖-->  
        <dependency>  
            <groupId>com.aliyun.oss</groupId>  
            <artifactId>aliyun-sdk-oss</artifactId>  
            <version>${aliyun.sdk.oss.version}</version>  
        </dependency>  
    </dependencies>
</dependencyManagement> 
```

## 2. 聚合

* **聚合**：将多个模块组织成一个整体，同时进行项目的构建
* **聚合工程**：一个不具备业务功能的“空“工程（有且仅有一个pom文件）

![[2cf2e7e49970696d722b49b17533770.jpg]]

（1）在父工程的 `pom.xml` 文件中设置当前聚合工程所包含的子模块

```xml
<!--聚合其他模块-->  
<modules>  
    <module>../tlias-pojo</module>  
    <module>../tlias-utils</module>  
    <module>../tlias-web-management</module>  
</modules>
```

（2）之后对聚合工程/父工程 tlias-parent 的clean/package等操作都会对整个聚合工程的所有模块一起执行（自动构建依赖关系）（如果打包时有某个模块的test部分报错，可以去除掉再打包）

![[Pasted image 20231210162721.png|300]]

![[afd612144a24217ca33e25433a53c28.jpg]]

# 三、私服

下载依赖：本地仓库 -> 私服 -> 远程中央仓库

![[af103c25091a862ed9083d872802105.jpg]]

1. 在**本地仓库**maven的 `settings.xml` 配置文件中配置访问私服的用户名和密码
2. 配置私服的连接地址
3. 配置私服依赖下载的仓库组地址
4. 在**自己的项目**的 `pom.xml` 文件中配置私服的地址
5. 发布项目，运行deploy生命周期即可

![[25ecf5623ad85862badbdb8ad95e47f.jpg]]