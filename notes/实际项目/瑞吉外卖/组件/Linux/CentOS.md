# 一、安装虚拟机 & 远程连接

安装 `VMware`（参考[vmware workstation pro 15.5.5 下载地址](https://blog.csdn.net/zdhsoft/article/details/107914245)以及安装教程[VMware 15.5 虚拟机安装完整教程](https://zhuanlan.zhihu.com/p/467420692)），并且下载 `Centos7` 的镜像来安装虚拟机，具体参考黑马的瑞吉外卖中的教程

下载 `FinalShell` 进行连接，连接的地址通过在虚拟机中运行 `ip addr` 得到（第一次没有配置网卡就没有，需要在 `cd /etc/sysconfig/network-scripts`，然后 `vi ifcfg-ens33` 通过vi将最后一行的 `ONBOOT=no` 改成 `yes`）

![[Pasted image 20240128221812.png]]

# 二、常用命令

## 1. 查看

1. `ll` = `ls -l`
2. `find <指定目录下查找> -name <文件名>`：在指定目录下**查找文件**
	* 例子：`find . -name *.java` 在当前目录及子目录下查找`.java`结尾的文件
3. `grep <文本内容> <文件名>`：在文件下**查找文本内容**
	* `ps -ef | grep <需要查找的进程信息>`：查找包含特定信息的进程
4. `cat`：**一次性显示文件**内容
5. `more`：**分页显示文件**内容
	* 空格 -- 下一页；回车 -- 下一行
6. `tail [-f] <filename>`：查看**文件末尾内容**
	* `-<数字>` -- 查看末尾多少行
	* `-f` 动态查看末尾内容

## 2. 操作文件（夹）

1. `touch <文件名>`：创建文件
2. `mkdir -p <文件夹路径>`：创建多层目录
3. `cp [-r] <source> <dest>`：复制
	* `-r` -- 复制文件夹

## 3. 打包压缩

对文件可以进行 打包、解包、压缩、解压

* `.tar` 后缀表示只是打包，没有压缩
* `.tar.gz` 后缀表示打包的同时还进行压缩

命令：

1. `tar [-zcxvf] <结果文件名> [需要处理的文件]`
	* `-z` -- 压缩 / 解压
	* `-c` -- 打包
	* `-x` -- 解包
	* `-v` -- 显示执行过程
	* `-f` -- 指定结果文件名
2. `tar -cvf <结果文件名.tar> [需要打包的文件]`：打包
3. `tar -zcvf <结果文件名.tar.gz> [需要打包压缩的文件]`：打包压缩
4. `tar -xvf <需要解包的包名> -C [目的目录]`：解包
5. `tar -zxvf <需要解压的包名> -C [目的目录]`：解压解包

## 4. vi操作

1. 查找 `/<需要查找的内容>`
2. 取消查找/高亮：`:nohl`
3. 查找到后看下一个用 `n`，上一个用 `N`
4. 显示行号 `:set nu`

# 三、软件安装

具体查看黑马瑞吉外卖Linux软件安装部分

## jdk

版本需要和IDEA中的项目版本一致

## Tomcat

需要关闭防火墙：

* 查看防火墙状态：`systemctl status firewalld`、`firewall-cmd --state`
* 暂时关闭防火墙：`systemctl stop firewalld`
* 永久关闭防火墙：`systemctl disable firewalld`
* 暂时开启防火墙：`systemctl start firewalld`
* 永久开放防火墙：`systemctl enable firewalld`
* 开放指定端口：`firewall-cmd --zone=public --add-port=8080/tcp --permanent`（开放后需要下面执行立即生效才可）
* 关闭指定端口：`firewall-cmd --zone=public --remove-port=8080/tcp --permanent`
* 立即生效：`firewall-cmd --reload`
* 查看开放的端口：`firewall-cmd --zone=public --list-ports`

下面是Tomcat的开启和关闭操作：

1. 开启Tomcat服务器：进入到解压缩的包的文件夹中的 `/bin`目录下执行 `sh start.sh` 即可开启（要关闭防火墙指定端口8080）
2. 关闭Tomcat服务器：在同样目录下（`/usr/local/apache-tomcat***/bin`）执行 `sh shutdown.sh` 即可关闭
	* 也可以先用 `ps -ef | grep tomcat` 查看进程，然后用 `kill -9 <进程号>` 关闭进程

## MySQL

安装参考[linux 安装mysql8.0](https://blog.csdn.net/u011421988/article/details/107234718)

* 查看已经启动的服务：`netstat -tunlp`
	* 需要先安装 `yum install net-tools`
* 设置开机启动mysql服务：`systemctl enable mysql`

## lrzsz

先查看yum是否有可安装的：`yum list lrzsz`，接着安装对应的名称：`yum install lrzsz.x86_64`

安装完成之后可以执行 `rz` 命令来进行**文件上传**（当前可以用 `FinalShell`来进行文件上传和下载）

# 四、项目部署

## 1. 人工部署

1. 在IDEA中开发SpringBoot项目并且**打成jar包**：通过maven下的 `项目名/Lifecycle` 下的 `package` 操作
	* 打包后项目的 `target/` 会有对应的 `***.jar` 包
2. 将IDEA中的**jar包上传**到Linux服务器中（上传到 `/usr/local/app`中执行）
3. Linux中**启动SpringBoot程序**：`java -jar boot工程.jar`（注意要关闭防火墙对应的端口，同时关闭原本的Tomcat服务器，让SpringBoot自动开启）
	* **不挂断运行**程序：`nohup java -jar boot工程.jar &> out.log &`（不挂断运行`boot工程.jar`，并且将输出放到 `out.log` 文件中）
4. **停止**SpringBoot程序：先查看`ps -ef | grep 'java -jar'`，接着停止 `kill -9 <查看的进程号>`

## 2. Shell部署

1. 先Linux下安装 `Git`，`maven`
2. 编写 `Shell` **脚本来拉取代码、编译、打包、启动**
	* 编写了之后还需要赋予该脚本文件执行权限才能执行 `chmod 777 bootStart.sh`
3. 为用户授予执行Shell脚本的权限
4. 执行Shell脚本

```shell
#!/bin/sh
echo =================================
echo  自动化部署脚本启动
echo =================================
 
echo 停止原来运行中的工程
APP_NAME=reggie_take_out
 
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Stop Process...'
    kill -15 $tpid
fi
sleep 2
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo 'Stop Success!'
fi
 
echo 准备从Git仓库拉取最新代码
cd /usr/local/reggie
 
echo 开始从Git仓库拉取最新代码
git pull
echo 代码拉取完成
 
echo 开始打包
output=`mvn clean package -Dmaven.test.skip=true`
 
cd target
 
echo 启动项目
nohup java -jar  reggie_take_out-1.0-SNAPSHOT.jar &>  reggie.log &
echo 项目启动完成
```

### 设置静态ip

由于虚拟机的ip地址是通过DHCP动态分配的，所以需要设置为一个静态的地址，这样才能够每次都访问同样的地址

![[Pasted image 20240129132904.png]]

改完后需要通过 `systemctl restart network` 重启网络服务