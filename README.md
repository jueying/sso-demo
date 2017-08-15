# sso-demo

## 简述
   可跨域的单点登录实现。原理参考自[http://www.cnblogs.com/ywlaker/p/6113927.html](http://www.cnblogs.com/ywlaker/p/6113927.html)

## 运行
1. 将代码clone下来并导入Eclipse.
2. 在mysql中运行项目的初始化脚本User.sql.
3. 配置sso-server的数据库连接和redis连接，在jdbc.properties中.
4. 通过mvn tomcat7:run启动sso-server,sso-client1和sso-client2项目.

## 访问
sso-client1 : http:localhost:8081/client1/user/info

sso-client2 : http:localhost:8082/client2/user/info
    