![[1904a2eac35c2a1a9d8cfea55a116f5.jpg]]

# 一、DDL 数据定义语言

## 1. 数据库操作

1. 查询数据库：
	1. `show databases;` 查看所有数据库
	2. `select database();` 查看当前正在使用的数据库
2. 创建数据库：
	1. `create databse [if not exists] <数据库名>;`
3. 删除数据库：
	1. `delete database [if exists] <数据库名>;`
4. 切换数据库使用：
	1. `use <数据库名>;

## 2. 表操作

![[c2ebf34c6f613cd59582dbb8c44f5a9.jpg]]

1. 创建表：
	1. `create table <表名> (字段名 类型 [约束] [comment '注释'], ..);`
2. 删除表：
	1. `drop table [if exists] <表名>;`
3. 查询表：
	1. `show tables;` 查询当前数据库下的表
	2. `desc <表名>;` 查询表结构
	3. `show create table <表名>;` 查询建表语句
4. 修改：
	1. ![[1afaa0b0e6782b8b9ad681edc6d0bd4.jpg]]

# 二、DML 数据库操作语言

1. 添加数据：
	1. ![[40c4d5d688bfcaba0851642aee52eb2.jpg]]
2. 修改数据：
	1. `update <表名> set 字段名1=值1, 字段名2=值2 [where 条件];`
3. 删除数据：
	1. `delete from <表名> [where 条件];`

# 三、DQL 数据库查询语言

```sql
select [distinct] 字段1 [as 别名1], 字段2 [as 别名2], ...
from 表名1, 表名2, ...
where 条件
group by 需要分组的字段列表
having 分组后列表的条件
order by 排序字段1 ASC/DESC, 排序字段2 ASC/DESC
limit 起始索引（第几条数据）, 查询记录数（选择多少条）
```

1. `select` 后面的字段名也可以用聚合函数：
	1. `count(字段名)` 
	2. `max(字段名)`
	3. `min(字段名)`
	4. `avg(字段名)`
	5. `sum(字段名)
2. 分组查询的 `select` 中只能用 分组字段 / 聚合函数
3. `where` 中不能使用聚合函数，因为where是一种约束声明，是在遍历数据库的时候进行约束的；而聚合函数是针对结果进行过滤的；having是一种过滤声明，是对查询结果进行过滤的，所以可用聚合函数
4. `limit` 查询中起始索引从0开始，表示第几条数据；查询页码从1开始；起始索引 = （查询页码数 - 1）* 每页显示记录数

# 四、多表查询

## 1. 内连接

左表和右表的交集

```sql
-- 隐式内连接
select 字段列表 from 表1，表2 where 条件;

-- 显示内连接
select 字段列表 from 表1 [inner] join 表2 on 连接条件;
```

## 2. 外连接

交集 + 左表/右表剩余部分

```sql
-- 左外连接
select 字段列表 from 表1 left [outer] join 表2 on 连接条件 [where ];

-- 右外连接
select 字段列表 from 表1 right [outer] join 表2 on 连接条件 [where];
```

# 五、事务控制

每条DML语句都是一条事务，但是多条语句需要同时成功/失败就需要手动指定成为事务

```sql
-- 一、开启事务
start transaction;

-- 二、业务操作
...

-- 三、提交 / 回滚事务
-- 1.提交事务
commit;

-- 2.回滚事务
rollback;
```

![[0d59b53b89f54fff4ca5dff1fa1db11.jpg]]

# 六、索引

1. 创建索引：`create index <索引名> on <表名>(<列名>);`
	1. 主键、唯一约束 都会创建索引
2. 查看索引：`show index from <表名>;`
3. 删除索引：`drop index <索引名> on <表名>;`