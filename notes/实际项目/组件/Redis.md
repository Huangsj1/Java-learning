Redis是基于内存的数据库，适用于一些热点数据（短时间大量访问，如新闻、秒杀等）

# 一、配置使用

默认使用的是0号数据库，要用其他的可以用 `select <index>` 来选择其他数据库

## 1. Linux

![[6789913c06b2fe0d1e842de33c39c53.jpg]]

1. redis根目录下修改配置文件 `redis.conf`，找到 `daemonize no` 改成 `yes`即可让服务在后台运行
2. 在redis根目录下执行 ` src/redis-server ./redis.conf` 即可启动服务，然后另一个终端在 `src/` 目录下执行 `./redis-cli` 即可开启客户端

### 1. 密码设置与登录

1. 配置密码：修改配置文件 `redis.conf` 中的 `# requirepass foobared` 为 `requirepass 1234` 取消注释改成需要设置的密码
2. 客户端登录：客户端可以通过 `./redis-cli -h localhost -p 6379 -a <密码>` 用密码登录（也可以直接进入 `./redis-cli`，进入后用 `auth <密码>`来登录） 

### 2. 允许远程连接

默认只能本地连接，远程连接需要修改配置文件 `redis.conf` 中的 `bind 127.0.0.1` 将其注释掉即可

* 注意：需要开放端口 `firewall-cmd --zone=public --add-port=6379/tcp --permanent`，然后立即生效 `firewall-cmd --reload`，外界才能连接

## 2. Windows

直接双击 `redis-server` 和 `redis-cli` 即可执行

如果需要连接指定地址的服务器，可以通过 `Shift + 鼠标右键` 打开 `PowerShell`，然后输入 `./redis-server -h <ip地址> -p <端口号6379> -a <密码>` 远程连接

# 二、数据类型

Redis是存储 `key-value` 结构的数据，其中 `key` 是字符串类型，`value`有5种常用数据类型：

1. 字符串 string
2. 哈希 hash
3. 列表 list
4. 集合 set
5. 有序集合 sorted set

![[4de80cf8b0daeb3214c0df126b4a6b6.jpg]]

## 1. 字符串 string 操作

![[Pasted image 20240130110239.png]]

## 2. 哈希 hash 操作

![[Pasted image 20240130110247.png]]

## 3. 列表 list 操作

![[Pasted image 20240130110257.png]]

## 4. 集合 set 操作

![[Pasted image 20240130110345.png]]

## 5. 有序集合 sorted set 操作

![[Pasted image 20240130110310.png]]

## 6. 通用命令

![[Pasted image 20240130110328.png]]

# 三、Java使用Redis

## 1. Jedis

需要先引入依赖：

```xml
<dependency>
	<groupId>redis.clients</groupId>
	<artifactId>jedis</artifactId>
</dependency>
```

1. 建立连接（需要开启了服务端才能连接上）
2. 执行具体操作
3. 释放连接

```java
@Test  
public void testRedis() {  
    // 1.建立连接  
    Jedis jedis = new Jedis("localhost", 6379);  
  
    // 2.执行操作  
    jedis.set("username", "张三");  
    System.out.println(jedis.get("username"));  
  
    // 3.释放连接  
    jedis.close();  
}
```

## 2. Spring Data Redis

![[Pasted image 20240130170427.png]]

首先引入依赖

```xml
<!-- pom.xml -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

接着配置一些配置信息

```yml
# application.yml
spring:
  # Redis相关配置  
  data:  
    redis:  
      host: localhost  
      port: 6379  
      #password: 1234  
      database: 0 # 操作0号数据库
      jedis:  
        # Redis连接池配置  
        pool:  
          max-active: 8 # 最大连接数  
          max-wait: 1ms # 连接池中最大阻塞等待时间  
          max-idle: 4 # 最大空闲连接  
          min-idle: 0 # 最小空闲连接
```

测试使用：

1. 注入 `RedisTemplate`
2. 使用 `redisTemplate.opsFor***().***` 来选择对应的类别的对应操作

```java
@SpringBootTest  
@RunWith(SpringRunner.class)  
class ReggieApplicationTests {  
  
    @Autowired  
    private RedisTemplate redisTemplate;  

    @Test  
    public void testDataRedis() {  
        redisTemplate.opsForValue().set("city", "beijing");  
    }  
}
```

但是直接使用的 key 是经过序列化的，导致实际的键值与代码的不太一样

![[Pasted image 20240130170518.png|300]]

所以需要自己实现一个配置类来配置 `RedisTemplate` 类，之后的键值就正常：

```java
// /config/RedisConfig

@Configuration  
public class RedisConfig extends CachingConfigurerSupport {  
  
    @Bean  
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {  
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();  
  
        // 默认的Key序列化器为：JdkSerializationRedisSerializer  
        redisTemplate.setKeySerializer(new StringRedisSerializer());  
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());  
  
        redisTemplate.setConnectionFactory(connectionFactory);  
  
        return redisTemplate;  
    }  
}
```

