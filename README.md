# Typora图床服务

## 项目介绍

两个模块的jdk版本均为11

- typora-upload-client:客户端程序
  > 1.只提供图片上传功能
- typora-upload-service:服务端程序
  > 1. 图片上传功能
  > 2. 图片预览功能
  > 3. 图片下载功能
  > 4. ~~图片修改功能~~

## 客户端的配置

### 配置文件

| 名称                 | 描述                                 |
|--------------------|------------------------------------|
| client.username    | 调用typora-upload-service服务时的用户名     |
| client.accessToken | 调用typora-upload-service服务时用户的token |
|client.http.timeout| 调用typora-upload-service服务接口的超时时间   |
|server.hosts| typora-upload-service服务部署ip和端口     |
|server.uploadPath| 上传接口路径                             |

### 打包

克隆项目到本地，根据实际配置好application.properties文件后，
终端进入typora-upload-client目录执行以下命令

```bash
mvn clean package -Dmaven.test.skip=true
```

## 服务端的配置

### 描述

内置H2数据库,存储用户的访问Token。

h2数据库监控面板访问地址:http://localhost:9508/h2-console

h2数据库建表语句再doc/sql目录下

```yaml
server:
  port: 9508 # 端口号
spring:
  servlet:
    multipart:
      max-file-size: 20971520 # 文件大小
  datasource:
    driver-class-name: org.h2.Driver # h2数据库驱动
    url: jdbc:h2:~/h2-database/typora # h2数据库url。~表示系统家目录
    username: dev # 连接数据库用户名
    password: dev # 连接数据密码
  h2:
    console:
      enabled: true # h2数据库监控面板
      path: /h2-console # 地址前缀 http://localhost:9508/h2-console
      settings:
        web-admin-password: dev # 管理员密码
        trace: true
# mybatis plus相关配置        
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    cache-enabled: false
  mapper-locations: classpath:/Mapper/**/*Mapper.xml
  global-config:
    db-config:
      logic-not-delete-value: 0
      logic-delete-value: 1
      id-type: auto
```

### 打包

克隆项目到本地，根据实际情况修改application.yml配置文件后，终端进入typora-upload-service目录执行以下命令

```bash
mvn clean package -Dmaven.test.skip=true
```


## 部署
先启动服务端
- 服务端
    > java -jar 打包后的jar包
    
    访问管理端,添加访问接口的用户。将配置的用户信息配置到客户端的application.properties文件中
- 客户端
打开Typora,找到【文件】-【偏好设置】-【图像】

    上传服务:自定义命令
    命令: java -jar 客户端jar包
    



