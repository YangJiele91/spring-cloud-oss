# Spring Cloud 脚手架

#### 项目介绍
基于Spring Cloud + Nacos构建的后端服务框架

#### 软件架构
1. 每个应用基于Spring Boot构建，Maven做构建与依赖工具
2. 采用 Spring Cloud Gateway 作为微服务网关
3. 采用 Spring Cloud Feign 作为微服务之间调用工具，调用方启动类应增加 @EnableFeignClients 注解
4. 采用 Spring Boot Cache 作为执行结果缓存工具，需要使用启动类增加 @EnableCaching 注解
5. 采用 Spring Cloud Hystrix 作为降级断路器，需要配置文件增加 feign.hystrix.enabled=true
6. 统一采用 Nacos 作为注册中心及外部化配置中心
7. Druid做数据库连接池，Mybatis-Plus 作为ORM框架及其自带分页插件做数据查询分页。 参考文档:https://baomidou.com/pages/24112f/
8. 引入调用链路监测框架 (暂未实现)

#### 安装依赖
1. 项目依赖于lombok，对应IDE环境中提前安装lombok插件
2. 工具集成有HuTool，具体使用方法网上查阅 (暂未实现)
3. 项目已集成基于Redis的分布式锁，具体使用参考封装工具类RedisUtils (暂未实现)
4. 项目集成二维码识别与生成功能，实现主流实现方案，参考QRCodeUtils( 暂未实现)
5. admin项目集成权限安全框架为Shiro，框架已集成，可直接使用 (暂未实现)
6. 项目已集成RabbitMQ，可直接参考TestRabbitSender使用 (暂未实现)
7. 通用拦截器中已添加对外部请求参数的打印，留意项目日志。若需打印某函数方法的入参出参等信息，在方法上加注解@Log (暂未实现)
8. 推荐Mybatis-Plus代码生成工具,代码根目录已包含Demo程序，参考文档：https://baomidou.com/pages/779a6e/
9. 接口文档默认集成Knife4j可视化文档工具(编码规则与Swagger相同)，参考文档：https://doc.xiaominfo.com/knife4j/documentation/ ，接口文档地址： http://xxx:port/doc.html
10. 集成JavaMelody监控平台，启动应用后访问http://xxx:8080/monitoring 
11. 集成Druid数据库监控平台，启动应用后访问微服务地址 http://xxx:port/druid 
12. 集成 EasyExcel 文件导出工具，参考文档: https://www.yuque.com/easyexcel/doc/write

#### 使用说明
1. 项目启动依赖MySQL、Redis，推荐Redis可视化客户端：RedisStudio3
2. Nacos注册中心启动脚本位于：_nacos/bin,项目运行需要先启动注册中心Nacos
3. SQL导入脚本位于：_sql/init.sql
4. 项目启动脚本位于：_sh/*
5. 项目启动命令参考：nohup java -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms1024m -Xmx1024m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=prod -jar hah-api.jar > nohup.out 2>&1 &

#### 数据库表结构、字段设计规范
1. 所有字段不可为空，必须要有默认值。varchar类型默认空字符串，timestamp默认当前时间戳，int类型默认1或0；
2. 所有表中关于时间的字段create_time和update_time字段为必有字段，创建时间一经创建不得更新，之后每次表数据变更，更新update_time字段；
3. 合适的存储引擎，一般InnoDB，合适的字符集，强制utf8mdb4，兼容emoji
4. 添加索引、字段注释
5. 字段类型尽可能小、可读性、避免Null
