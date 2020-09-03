# problem-records
日常问题记录
### 2020年7月24日
- tomcat shutdown有时无法关闭tomcat进程原因，tomcat的startup和shutdown实现流程 
### 2020年7月25日
- liunx 有台liunx服务器图形界面卡,用不卡的机器执行命令:ssh -X 用户名@ip
- oracle|java java中jdbc连接oracle的url写法问题,:和/的区别,SID和ServerName的概念，集群的url写法等
  ```
  1.普通SID方式 jdbc:oracle:thin:username/password@x.x.x.1:1521:SID
  2.普通ServerName方式 服务名：abcd jdbc:oracle:thin:username/password@//x.x.x.1:1521/ABCD
  ```
### 2020年7月30日
- nodejs|webpack|vue 路由动态加载，使用import方式会把路径下所有文件全部引入导致启动重载都非常慢，改为require,import和require写法及区别
 ```diff 
 + component:resolve => require([`@/views/${url}`], resolve),
 - component:() => import(`@/views/${url}`), 
 ```
 - vue组件scoped不生效问题
  ```
  1.scoped实现组件的私有化，不对全局造成样式污染，表示当前style属性只属于当前模块，不生效的原因可能是
    给另一个组件添加样式,无法起到穿透效果，可使用以下写法 >>>或者/deep/
  ```
### 2020年8月17日
- 前端 pwa(Progressive Web App),Service worker,Lavas PWA,增强浏览器或者原生app的效果，页面或者接口缓存，通知推送等
### 2020年9月3日
- nodejs打包，遇到caniuse-lite is outdated. Please run next command `npm update`
  ```
    突如其来的打包问题：caniuse-lite is outdated. Please run next command npm update
    npm update之后并没有解决---网上查找解决方案解决
    1.直接删除该项目node_modules下面的caniuse-lite和browserslist这两个文件夹，然后npm i caniuse-lite browserslist
  ```

