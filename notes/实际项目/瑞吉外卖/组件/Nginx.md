# 一、安装

![[Pasted image 20240201112155.png]]

# 二、命令

下面命令都需要进入安装nginx的文件夹中的 `sbin/` 目录下执行

1. 查看版本号：`./nginx -v`
2. 检查配置文件正确性：`./nginx -t`
3. 启动Nginx服务：`./nginx`
	* 开启防火墙本机才能访问：`firewall-cmd --zone=public --add-port=80/tcp --permanent`
4. 停止Nginx服务：`./nginx -s stop` 
5. 修改配置文件后重新加载服务器：`./nginx -s reload`

添加环境变量后可以直接用 `nginx **` 来执行上面操作：

1. 在 `/etc/profile` 文件中的环境路径添加 `/usr/local/nginx/sbin`![[Pasted image 20240201105604.png|300]]
2. 重新加载该配置文件 `source /etc/profile`

# 三、配置文件介绍

![[Pasted image 20240201112209.png]]

# 四、Nginx具体应用

## 1. 部署静态页面

![[Pasted image 20240201112223.png]]

## 2. 反向代理

* 正向代理：（客户端 + 代理服务器）访问 web服务器
* 反向代理：客户端 访问 （代理服务器 + web服务器）

![[Pasted image 20240201112245.png]]![[Pasted image 20240201112309.png]]

![[Pasted image 20240201112325.png]]

## 3. 负载均衡

![[Pasted image 20240201112335.png]]

![[Pasted image 20240201112348.png|400]]

![[Pasted image 20240201112404.png]]