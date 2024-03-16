* Docker能够将应用程序、依赖、环境、配置、系统函数库一起打包成**镜像**，只打包了应用需要的东西
* 这些镜像能够直接在任意Linux操作系统上执行，执行后称为**容器**，每个容器都有对应的文件系统

![[d7564c48637de10482f64c19eee92f8.jpg]]![[f205ac5de53e503ce42beff25703c62.jpg]]

# 一、Docker与虚拟机

![[1b6aeb03e001c7b67cb8bf72305430a.jpg]]

![[Pasted image 20240223145922.png]]

# 二、Docker架构

镜像和容器：

![[044fa3656deff3fd607dd8d46bf2d58.jpg]]

镜像托管平台DockerHub

![[986f4e60ea23af73ed3819967b71c18.jpg]]

Docker的CS架构

![[fee23f98fa3358fd9bc03f36359d82f.jpg]]

## 总结

![[Pasted image 20240223150624.png]]

# 三、Docker 安装

在CentOS 7内核版本不低于 3.10 下安装Docker（ `uname -a` 来查看内核版本）

参考[Docker安装与完全卸载(这一篇绝对够用)_docker 卸载-CSDN博客](https://blog.csdn.net/doublepg13/article/details/131781150)

配置阿里云镜像参考[配置镜像加速器_容器镜像服务(ACR)-阿里云帮助中心 (aliyun.com)](https://help.aliyun.com/zh/acr/user-guide/accelerate-the-pulls-of-docker-official-images?spm=a2c4g.11186623.0.0.7784379davmPqL)

# 四、Docker基本操作

## 1. 镜像命令

![[adcc1ca9b3327a55917746088d5cf12.jpg]]

拉取镜像：

![[058dcb3f6d1e5442227db33c2fa4e7b.jpg]]

打包镜像和加载压缩包镜像：

```shell
# 打包
docker save -o 包名.tar 镜像名:标签

# 加载
docker load -i 包名.tar
```

## 2. 容器命令

![[aa1ffe14108ead01e3494ac4d1d90aa.jpg]]

* 暂停会保存当前容器状态，没杀死进程
* 停止直接杀死进程，只保留静态资源（文件系统等）

`docker ps`默认查看运行中的容器，可通过 `-a` 来查看所有容器 

### 1. 运行容器

```shell
docker run --name 容器名 -p 宿主机端口:容器端口 -d 镜像名
```

![[69b5bd86a3002b999971d01813d6a21.jpg]]

### 2. 进入容器

进入容器是进入该容器的文件系统中，可以通过bash来执行各种终端命令（但是只包括打包镜像时需要的环境/应用），但是不推荐进入容器修改文件

```shell
# 进入容器
docker exec -it 容器名 要执行的命令（如bash通过终端进入）

# 推出容器
exit
```

![[9375f6b4a0970195b81bc243b943691.jpg]]

## 3. 数据卷命令

Docker中有数据卷Volume，其可以**映射文件到宿主机磁盘文**件中，于是可以通过**将容器的文件映射到数据卷中**，再映射到宿主机中，这样就能够实现不直接进入容器中修改数据，将容器与数据分离

![[df76c74eaef355a323bf4377164bfe4.jpg]]

### 1. 操作数据卷

![[Pasted image 20240223161356.png]]

### 2. 挂载数据卷到容器某个目录

* 如果数据卷映射的**宿主机磁盘内容为空**，那么创建容器挂载的时候会**将容器挂载的文件夹的内容放到宿主机磁盘中**
* 如果**宿主机磁盘已有内容**，会隐**藏容器中该文件夹下的内容**，用的是宿主机映射文件夹的内容
* 如果数据卷不存在，创建容器挂载时会**自动创建数据卷**

![[d104d6df2aefb442da14a6ed2692737.jpg]]

![[3cb99e29f1929b753c4f7ddce657f3f.jpg]]

### 3. 挂载宿主机目录/文件到容器某个目录/文件

挂载宿主机目录/文件的命令和挂载数据卷类似，只是将数据卷名称换为宿主机目录/文件位置

![[278bfc9e98f0989176d2c6498ca5b8e.jpg]]

![[Pasted image 20240223164321.png]]

# 五、Dockerfile 自定义镜像

## 1. 镜像文件

![[1049857912cd86b84fdc419331dbc80.jpg]]

![[Pasted image 20240223164900.png]]

## 2. 创建镜像

在当前宿主机中创建镜像，需要有一个**基础镜像**来基于此创建，然后通过**Dockerfile**中的命令来逐层创建镜像

![[9b76cff0f5d9612df487514ac9de323.jpg]]

![[Pasted image 20240223165927.png]]![[Pasted image 20240223165950.png]]

![[Pasted image 20240223165959.png]]

# 六、DockerCompose

DockerCompose基于Compose文件能够快速部署分布式应用（创建、运行容器），如果是创建容器需要对应文件夹下有对应的Dockerfile文件来执行创建

![[53e5c30dca8fb05d36c2ac81efe4e4a.jpg]]![[de35cd4be7ae7d3df12f3f1636459e2.jpg]]

## 1. 安装

[docker-compose教程（安装，使用, 快速入门）-CSDN博客](https://blog.csdn.net/pushiqiang/article/details/78682323)

## 2. 部署微服务集群

DockerCompose来部署微服务，微服务之间可以通过服务名称来互相访问（所以需要修改各个模块配置文件中访问各个服务的地址为服务名称）

![[b80277a62740e3e2e0f8ef658d771bf.jpg]]![[7f55a3d35a7a783682bfdd39c69f109.jpg]]

# 七、Docker镜像仓库

Docker镜像仓库除了共有的Docker Huh，还可以自己搭建私有的镜像仓库

[镜像仓库的搭建、推送、拉取](https://blog.csdn.net/I_r_o_n_M_a_n/article/details/123793554)

![[Pasted image 20240223173044.png]]