# 一、虚拟机配置github和gitee密钥

### 1. 设置账号

```shell
git config --global user.name "your name"
```

### 2. 设置邮箱

```shell
git config --global user.email "your email"
```

### 3. 创建 .ssh 文件夹，在里面进行下面操作

```shell
# 1.进入 /~
cd

# 2.创建文件夹
mkdir .ssh

# 3.进入该文件夹
cd .ssh
```

### 3. 分别生成github和gitee的密钥

```shell
# 1.生成github的密钥
ssh-keygen -t rsa -C "github绑定的邮箱" -f "id_rsa_github"

# 2.生成gitee的密钥
ssh-keygen -t rsa -C "gitee绑定的邮箱" -f "id_rsa_gitee"
```

这样 `.ssh` 文件夹下面多了id_rsa_github和id_rsa_github.pub以及id_rsa_gitee和id_rsa_gitee.pub,到这里说明github和gitee的私钥和公钥都生成了

### 4. 将生成的公钥分别复制到github和gitee中

先分别查看github和gitee的公钥

```shell
cat id_rsa_github.pub
cat id_rsa_gitee.pub
```

分别将它们复制到 github 和 gitee 的ssh密钥中（全部内容复制）

之后新建一个 `config` 文件：`vim config`，在里面填入：

```config
# gitee
Host gitee.com
HostName gitee.com
PreferredAuthentications publickey
IdentityFile ~/.ssh/id_rsa_gitee
 
# github
Host github.com
HostName github.com
PreferredAuthentications publickey
IdentityFile ~/.ssh/id_rsa_github
```

这样就可以区分github和gitee了

### 5. 测试是否成功

```shell
ssh -T git@github.com
ssh -T git@gitee.com
```

执行的时候中间要输入 `yes` 确认

### 参考：

[Linux 配置gitee](https://www.cnblogs.com/fortunely/p/14832446.html#:~:text=Linux%20%E9%85%8D%E7%BD%AEgitee%20%E5%AE%89%E8%A3%85%E5%A5%BDgit%E5%90%8E%2C%20%E5%A6%82%E4%BD%95%E9%85%8D%E7%BD%AE%E8%BF%9E%E6%8E%A5%E8%87%B3gitee%20%3F%20%E9%A6%96%E5%85%88%2C%20%E9%9C%80%E8%A6%81%E5%9C%A8%E5%AE%98%E7%BD%91%E6%B3%A8%E5%86%8C%E4%B8%80%E4%B8%AAgitee%E8%B4%A6%E5%8F%B7%2C%20%E7%84%B6%E5%90%8E%E8%BF%9B%E8%A1%8C%E4%BB%A5%E4%B8%8B%E9%85%8D%E7%BD%AE%E6%AD%A5%E9%AA%A4%3A,%24%20git%20config%20--global%20user.name%20%22your%20name%22%202.)

[Windows下使用Git同时绑定Github以及Gitee](https://blog.csdn.net/qq_45628733/article/details/128538732#:~:text=1.%20ssh-keygen%20-t%20rsa%20-C%20%22%E6%AD%A4%E5%A4%84%E5%A1%AB%E4%BD%A0%E7%9A%84github%E7%BB%91%E5%AE%9A%E9%82%AE%E7%AE%B1%22%20-f%20%22id_rsa_github%22,ssh-keygen%20-t%20rsa%20-C%20%22%E6%AD%A4%E5%A4%84%E5%A1%AB%E4%BD%A0%E7%9A%84gitee%E7%BB%91%E5%AE%9A%E9%82%AE%E7%AE%B1%22%20-f%20%22id_rsa_gitee%22%201)

# 二、配置用户密码

由于每次 `git pull/push` 都需要输入用户名和密码，可以通过下面命令，之后再执行 `git pull/push`，第一次需要，后面就不用

```shell
git config --global credential.helper store
```

