Vue是一个基于MVVM模型的前端js框架，为了能够简化[[1.JS基础部分#6. DOM|DOM]]，直接在视图上将变量展示出来，使得在html的标签中直接使用Vue定义的变量和方法（也可用各种控制语句）

## 1. Vue的语法使用

```html
<!DOCTYPE html>
<html lang="en">
  
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vue基础</title>
    <!-- 一、添加Vue文件 -->
    <script src="../js/vue.js"></script>
</head>
  
<body>
	<!-- 三、使用Vue指令 -->
    <div id="app">
        <!-- 1.v-bind: -->
        <a v-bind:href="url">链接1</a>
        <a :href="url">链接2</a>
        <!-- 2.v-model= -->
        <input type="text" v-model="url">
        {{url}}
        <br>
        <!-- 3.v-on: -->
        <input type="button" value="按钮" v-on:click="handle()">
        <input type="button" value="按钮" @click="handle()">
        <br>
        <!-- 4.v-if=, v-else-if=, v-else= -->
        年龄<input type="text" v-model="age">为：
        <span v-if="age <= 35">年轻人</span>
        <span v-else-if="age > 35 && age < 60">中年人</span>
        <span v-else="age >= 60">老年人</span>
        <br>
        <!-- 5.v-show= -->
        <span v-show="age <= 35"> 年轻人 </span>
        <br>
        <!-- 6.v-for= -->
        <div v-for="addr in addrs">{{addr}}</div>
        <div v-for="(addr, index) in addrs">{{index + 1}} : {{addr}}</div>
    </div>
</body>

<script>
	<!-- 二、创建Vue对象 -->
    // 定义Vue对象
    new Vue({
        // 接管区域
        el: "#app",
        // 数据
        data: {
            url: "www.baidu.com",
            age: 20,
            addrs: ["北京", "上海", 1, true, null],
        },
        // 函数
        methods: {
            handle: function () {
                alert("点击了一下...");
            }
        }
        // 生命周期的一个函数
        mounted() {
            alert("Vue挂载完成，发送请求到服务端");
        }
    })
</script>
  
</html>
```

1. 添加`Vue.js`文件在`<head>`中：`<script src="../js/vue.js"></script>`
2. 在`<body>`后面定义Vue对象：`new Vue({el:**, data:**, methods:**})`
3. 在`<body>`主体部分的各种标签中使用对应的Vue指令

### 1. 常用指令

![[25228276be81db1d0da02ef5b783f70.jpg]]

1. `v-bind`：绑定html标签的属性
2. `v-model`：将表单元素与数据模型进行双向绑定
3. `v-on`：事件绑定
4. `v-if, v-else-if, v-else`：条件渲染
5. `v-show`：条件渲染（display与否）
6. `v=for`：循环遍历

### 2. 生命周期

每一个生命周期阶段都会自动执行对应的函数

![[4d7dd604c642fea7319b091f5e1d12f.jpg]]


## 2. Vue 路由

可以通过路由来显示不同的页面/组件

![[445897b867b4e616ca15d33484256cc.jpg]]