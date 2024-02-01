# 一、Mysql主从复制

![[Pasted image 20240131214011.png]]

## 1. 虚拟机克隆

参考[虚拟机的克隆](https://blog.csdn.net/m0_59879385/article/details/130497097?spm=1001.2101.3001.6650.7&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-7-130497097-blog-106148678.235%5Ev43%5Epc_blog_bottom_relevance_base6&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-7-130497097-blog-106148678.235%5Ev43%5Epc_blog_bottom_relevance_base6&utm_relevant_index=11)（其中第三步1.中不用修改mac地址，默认克隆的时候会修改）。克隆虚拟机之前最好进行一次[快照记录](https://blog.csdn.net/cai_and_luo/article/details/106148678)，这样出错后也可以恢复

## 2. 主从数据库的配置

### 1. 主数据库

`server-id` 可以自己设置（可以根据ip地址最后部分来设置）

![[Pasted image 20240131214140.png]]

如果该命令没有，可以执行 `systemctl restart mysql`

![[Pasted image 20240131214150.png|300]]

第三步中在mysql高版本中不能够一次性执行，所以需要用下面两条命令代替（用户名和密码是自己设置的）

```sql
CREATE USER 'xiaoming'@'%' IDENTIFIED BY '1234'; -- 创建用户 
GRANT REPLICATION SLAVE ON *.* TO 'xiaoming'@'%'; -- 授权
```

![[Pasted image 20240131214159.png]]![[Pasted image 20240131214213.png]]

### 2. 从数据库

![[Pasted image 20240131214223.png]]

如果该命令没有，可以执行 `systemctl restart mysql`

![[Pasted image 20240131214235.png|300]]

第三步中最后两项值根据前面主数据库查看的数据来填写

```sql
CHANGE MASTER TO MASTER_HOST='192.168.138.128', MASTER_USER='xiaoming', MASTER_PASSWORD='1234', MASTER_LOG_FILE='mysql-bin.000001', MASTER_LOG_POS=690;
```

![[Pasted image 20240131214255.png]]

第四步可以用 `show slave status\G;` 来格式化查看

![[Pasted image 20240131214447.png]]

### 错误信息

如果从数据库最后展示出来的结果第一项不是 `Waiting for master to send event`，就代表出错了，下面可以看错误信息

每次修改了之后都退出从数据库，执行 `systemctl resatrt mysql` 重启数据库

```sql
-- 1.高版本的密码验证问题
Last_IO_Error: error connecting to master 'xiaoming@192.168.138.128:3306' - retry-time: 60 retries: 1 message: Authentication plugin 'caching_sha2_password' reported error: Authentication requires secure connection.

-- 解决：将`xiaoming`用户的认证方法改回`mysql_native_password`，在主数据库服务器上执行以下命令：
ALTER USER 'xiaoming'@'%' IDENTIFIED WITH mysql_native_password BY '1234';
```

```sql
-- 2.主从数据库相同UUID问题
Last_IO_Errno: 13117 Last_IO_Error: Fatal error: The slave I/O thread stops because master and slave have equal MySQL server UUIDs; these UUIDs must be different for replication to work.

-- 解决：
1.停止MySQL服务。
2.找到并删除`auto.cnf`文件。它通常位于MySQL的数据目录中。
3.重新启动MySQL服务。
```

# 二、读写分离


