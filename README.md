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
 ```
 ...
 // component:() => import(`@/views/${url}`),
 component:resolve => require([`@/views/${url}`], resolve),
 ...
 ```


