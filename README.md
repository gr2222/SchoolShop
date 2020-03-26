# 基于Spring SpringMVC Mybatis Mysql 5.7 的校园商铺

--------------------------

## 主要用到的技术：

---------------------

-  使用maven构建项目处理依赖。
- 使用Spring SpringMVC Mybatis 搭建整个系统。
- 前端后端之间通过ajax 传输信息。
- 前端 使用 SUI moblie 构建整体UI，使用ECharts构建图表，jQuery写逻辑。
- 使用thumbnailator开源组件帮助处理图片。
- 使用MySQL存储用户信息、商铺结构信息、商铺信息、购买记录。
- 使用Redis存储高频信息如：商铺分类、商品分类、首页轮播图等 

## 项目主要模块：

--------

- **用户登录模块：** 用户的登录注册，用户分为三种身份 顾客 店主 超级管理员
- **前台展示模块：** 包括首页展示、商铺列表、商品列表、商品详情、购买后二维码兑换。
- **店主管理模块：** 包括商店的添加、修改、删除。商品分类信息的添加、修改、删除。商品信息的添加、修改、删除。销量统计图表。通过扫描二维码完成兑换。
- **超级管理员模块(TODO)：** 商店的分类信息的添加、修改、删除。首页轮播图的添加、修改、删除。商铺审核。

## 启动系统：

--------

1. 打开src/main/resources/jdbc.perprotis 修改 MySQL 信息，修改 redis信息。
2. 创建数据库schoolshop 运行src/main/resources/schoolshop.sql。
3. 将src/main/resources/image.zip 解压到电脑任意目录下，修改src/main/Java/com/gr/util/PathUtil.java 按照不同操作系统将解压后的image 目录填入。![image-20200326110252591](https://github.com/gr2222/image/blob/master/QQ20200326-111644%402x.png)
4. 在tomcat中将image目录下的item设置项目目录。
5. 启动tomcat
6. 访问http://localhost:8080/SchoolShop/reception/index

## 功能展示：
- 用户模块
![image-20200326110252591](https://github.com/gr2222/image/blob/master/%E7%99%BB%E5%BD%95%E6%B3%A8%E5%86%8C.png)
- 前端展示
![image-20200326110252591](https://github.com/gr2222/image/blob/master/%E9%A6%96%E9%A1%B5.png)
![image-20200326110252591](https://github.com/gr2222/image/blob/master/%E5%95%86%E5%BA%97%E9%A1%B5%E9%9D%A2.png)
- 商铺管理
![image-20200326110252591](https://github.com/gr2222/image/blob/master/QQ20200326-112359%402x.png)
![image-20200326110252591](https://github.com/gr2222/image/blob/master/QQ20200326-112410%402x.png)
![image-20200326110252591](https://github.com/gr2222/image/blob/master/QQ20200326-112421%402x.png)
![image-20200326110252591](https://github.com/gr2222/image/blob/master/QQ20200326-112513%402x.png)
![image-20200326110252591](https://github.com/gr2222/image/blob/master/QQ20200326-112444%402x.png)


