## 1. 创建Vue项目

1. 在项目文件夹中打开cmd执行 `vue ui` 开启图像化界面
2. 创建Vue新项目
3. vscode打开项目，在NPM脚本中执行 `server ***` 运行
	1. npm脚本可以通过点击 `package.json` 脚本，然后在项目栏的EXPLORE一行的三个点处选上 `npm scripts`
4. 打开对应的端口号地址在浏览器中显示
	1. 可以在 `vue.config.js` 中的 defineConfig里面添加 `devServer: {port: 7000, }` 来修改端口号

![[a08a702c1b5868285ae52d108fddbc4.jpg]]

## 2. 安装ElementUI组件库

1. 在当前项目文件夹下安装ElementUI组件库：`npm install element-ui@2.15.3`
2. 在`main.js`中引入ElementUI库：`import ElementUI from 'element-ui';`，`import 'element-ui/lib/theme-chalk/index.css';`，`Vue.use(ElementUI);`
3. 访问[组件官网](https://element.eleme.cn/2.13/#/zh-CN/component/installation)，复制组件代码到自己创建的 `views/element/ElementView.vue`文件内的 `<template>` 中
4. 在 `App.vue` 文件内的 `template` 中使用 `<element-view></element-view>`

每个组件文件 .vue 都包含三部分：

1. HTML：`<template></template>`
2. JavaScript：`<script></script>`
3. CSS：`<style></style>`

![[f317b61543dd560ec6ee6bb1935a432.jpg]]

1. 根组件 `src/App.vue` 主要的页面展示组件部分（一般都是展示其他在 `src/views/`下的组件）
2. 路由配置 `src/router/index.js` 定义了多种路由，即可以展示的组件

## 3. 使用Axios

1. 项目文件夹下安装axios：`npm install axios;`
2. 在对应的 .vue 文件中的 `<script>` 后导入：`import axios from 'axios';`

## 4. 打包

Nginx是轻量级的Web服务器

1. npm脚本中点击 `build`，打包后的文件夹为 `dist/`
2. 部署：将项目得到的 `dist/` 文件夹下的所有文件放到 `ngnix` 安装目录的 `html/` 目录下
3. 执行：双击 `nginx.exe` 文件执行，Nginx服务器默认占用80号端口
	1. 在任务管理器中可以查看nginx是否运行
	2. 可在 `ngnix**/conf/nginx.conf` 文件下的 `server {listen 80; }` 改成其他端口
4. 访问：`http://localhost:端口号`