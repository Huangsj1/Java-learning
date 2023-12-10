JDBC是一套操作关系型数据库的API/规范，具体实现是由各个数据库实现的；而Mybatis能够简化JDBC开发

![[860b7c0ae5e837f8ceaf450fa2f6526.jpg]]
![[90bb551688bbee2189d969892d591ed.jpg]]

# 一、步骤

![[58947d4f0b447379c1a796d35b1f9d2.jpg]]

## 1. 准备工作

1、创建数据库 `db01`，里面创建表 `tb_user`

![[Pasted image 20231206092301.png|400]]


2、创建Springboot工程

![[1a5ea0fd1a4111145217ea313414959.png|315]]![[9ab3b2724c9a1257fc54b163ae6dd50.png|315]]

3、在模块的 `src/main/java/<公司名>/` 创建 `pojo/User` 来创建数据库表类 `User`（成员与数据库表的属性名相同，若数据库表的属性名有下划线，则在java类中改成驼峰命名）

```java
// 用来接收数据库表数据的类结构
public class User {  
    private Integer id;  
    private String username;  
    private String name;  
    private Integer age;  
    private Short gender;
    ...
}
```

### lombok 简化类结构

由于上面写一个类需要包含 `set(),get(),toString()`等函数太过臃肿，所以可以通过添加依赖 `lombok` 来使用注解简化类定义

1、在`pom.xml`配置文件中添加 `lombok` 依赖

```xml
<!--lombok依赖-->  
<dependency>  
    <groupId>org.projectlombok</groupId>  
    <artifactId>lombok</artifactId>  
</dependency>
```

2、修改类定义

```java
//@Getter  
//@Setter  
//@ToString  
//@EqualsAndHashCode  
@Data  // Data相当于上面四个
@NoArgsConstructor  
@AllArgsConstructor  
public class User {  
    private Integer id;  
    private String username;  
    private String name;  
    private Integer age;  
    private Short gender;
}
```

![[ef5a34b89c532ed83d25264762cc049.jpg]]

## 2. Mybatis依赖

在 `src/main/resource/application.properties` 配置文件中配置Mybatis依赖

```properties
# 配置数据库的连接信息  
# 驱动类名称  
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver  
# 数据库连接的url  
spring.datasource.url=jdbc:mysql://localhost:3306/db01  
# 连接数据库的用户名  
spring.datasource.username=root  
# 连接数据库的密码  
spring.datasource.password=1234
```

## 3. 编写SQL语句

1、在模块的 `src/main/java/<公司名>/` 下创建 `mapper/UserMapper`接口来声明sql语句的函数

```java
//程序运行时,会自动生成该接口实现类对象,并将该对象交给IOC容器管理 
@Mapper 
public interface UserMapper {  
    // 查询全部用户信息  
    @Select("select * from tb_user")  
    public List<User> list();  
}
```

2、在测试文件 `src/test/java/<公司名>/<模块名>ApplicationTests.java`  中编写测试上面的 `UserMapper` 下的函数 `list()` 的函数，执行该测试函数

```java
// springboot整合的单元测试的注解  
@SpringBootTest  
class SpringbootMybatisQuickstartApplicationTests {  
    // 自动创建userMapper的bean对象  
    @Autowired  
    private UserMapper userMapper;  
  
    // 测试程序  
    @Test  
    public void testListUser() {  
        List<User> userlist = userMapper.list();  
        userlist.stream().forEach(user -> System.out.println(user));  
    }  
}
```

# 二、预编译SQL

sql语句有时候不能够写死，因为是跟随着程序运行的时候得到的值来确定执行的结果，所以可以通过使用占位符来替代一些固定的值，在运行时才确定

**预编译**就是将一些灵活的参数值以占位符?的形式给代替掉，我们把参数值给抽取出来，把SQL语句进行模板化。这样当sql执行相同语句（不同实际参数）时，可以不用重新编译，在执行时才传入实际参数值

![[dbb353bf1561a6b5924922e42ddd075.jpg]]

## 1. 日志输出

可在 `src/main/resources/application.properties` 中打开 `mybatis` 日志，指定输出到控制台

```properties
# 指定mybatis输出日志到控制台  
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

![[Pasted image 20231206105625.png]]

## 2. 参数占位符

1. `#{...}`：执行sql语句时会将 `#{...}` 替换为 `?`，生成预编译SQL，传参时会自动设置参数值
2. `${...}`：执行sql语句时会将参数值拼接在SQL语句中，存在SQL注入问题
	1. 当参数需要放到字符串里面，如 `like '%a%'`，这时候如果用 `'%#{name}%'` 会导致替换的 `?` 在字符串里面，不会被实际替换，所以需要用 `$`，即 `%${name}%`，因为可以被直接替换成参数值；但是这种方法不安全，所以又用 `concat('%', #{name}, '%')` 拼接字符串代替![[55620c11b13b785f487746850f10cb4.jpg]]

```java
@Delete("delete from tb_user where id = #{id}")  
public int delete(Integer id);

@Delete("delete from tb_user where id = ${id}")  
public int delete(Integer id);
```

# 三、基本操作

## 1. 查询

### 数据封装

由于数据库中列名可能使用下划线，而java中类的成员都是用驼峰命名法，不同名会导致mybatis不能够自动封装查询结果到java对象指定的成员中，可以通过在 `application.properties` 中配置开启自动命名映射

```properties
# 开启mybatis的驼峰命名自动映射开关，a_column -> aColumn  
mybatis.configuration.map-underscore-to-camel-case=true
```

![[f49ae50ef9d5b1ca8dc14dc36098434.jpg]]

```java
// 1.查询全部用户信息  
//  返回值是查询的所有元组  
@Select("select * from tb_user")  
public List<User> list();


// 1.查询测试程序  
@Test  
public void testListUser() {  
    List<User> userlist = userMapper.list();  
    userlist.stream().forEach(user -> {  
        System.out.println(user);  
    });  
}
```

## 2. 删除

可通过占位符 `#{...}` 来代表参数

```java
// 2.删除用户信息  
//  返回值表示影响/删除的记录数  
@Delete("delete from tb_user where id = #{id}")  
public int delete(Integer id);


// 2.删除测试程序  
@Test  
public void testDeleteUser() {  
    int delete = userMapper.delete(2);  
    System.out.println(delete);  
}
```

## 3. 新增

可增加 `@Options(useGeneratedKeys = true, keyProperty = "id")` 来获得插入后的主键id

```java
// 3.新增用户信息  
//  在不知道主键id的情况下,可通过@Options来将插入后得到的id赋值到变量中 
@Options(useGeneratedKeys = true, keyProperty = "id")  
@Insert("insert into tb_user values(#{id}, #{username}, #{name}, #{age}, #{gender});")  
public void insert(User user);


// 3.新增测试程序  
@Test  
public void testInsertUser() {  
    // 一般情况下都是不知道主键id值  
    User user = new User(null, "John", "重", 40, (short) 1);  
    userMapper.insert(user);  
    System.out.println(user.getId());  
}
```

## 4. 更新

```java
// 4.更新用户信息  
@Update("update tb_user set name=#{name}, age=#{age} where id=#{id}")  
public void update(User user);


// 4.更新测试程序  
@Test  
public void testUpdateUser() {  
    User user = new User(3, null, "哈哈", 30, (short) 1);  
    userMapper.update(user);  
}
```

## 5. 参数名说明

![[a7705217add7bd55609971268cec2ff.jpg]]

# 四、XML映射文件

对于简单的sql语句可以使用上面的**注解**的方法，但是对于一些复杂的语句，用XML文件更好

xml映射文件的开头需要添加[该网址](https://mybatis.net.cn/getting-started.html)上的“探究已映射的 SQL 语句”中的前面部分头代码：

```xml
<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE mapper  
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
```

* 注意：不能对同一个方法既有注解又有XML

![[060f78d65096bc058b5019238f06fbf.jpg]]

# 五、动态SQL

随着用户输入/外部条件变化而**变化的SQL语句**

## 1. <\if> 标签

1. `<if>` 标签用于条件判断，需要使用 `test` 属性进行判断，若条件为 true 就拼接SQL语句
2. 为了防止 `where` 在拼接多个条件的时候有最终导致开头为and报错，所以需要用 `<where> </where>` 标签来代替 `where` 关键字（`set`同理）

```xml
<select id="list2" resultType="com.itheima.pojo.User">  
    select * from tb_user  
    <where>  
        <if test="username != null">  
            username like concat(#{username}, '%')  
        </if>  
        <if test="gender != null">  
            and gender = #{gender}  
        </if>  
    </where>  
</select>


public List<User> list2(String username, Short gender);
```

## 2. <\foreach> 标签

```xml
<!--    <foreach>标签使用-->  
    <select id="list3" resultType="com.itheima.pojo.User">  
        select * from tb_user  
        where id in        
        <!--  
            collection：集合遍历的名字  
            item：每个元素的名字，可任取  
            separator：分隔符  
            open：起始字符串  
            close：结尾字符串  
        -->  
        <foreach collection="ids" item="id" separator="," open="(" close=")">  
            #{id}  
        </foreach>  
    </select>


public List<User> list3(List<Integer> ids);
```

## 3. <\sql> 和 <\include> 标签

```xml
<!--    定义复用代码-->
<sql id="commonSelect">  
    select * from tb_user  
</sql>

<select id="list4" resultType="com.itheima.pojo.User">  
	<!-- 使用复用代码 -->
    <include refid="commonSelect"></include>  
</select>
```