<h1 align="center"><a href="#" target="_blank">企业资源计划系统</a></h1>
<div align="center">
 <strong>
  下一代智能ERP系统
 </strong>
</div>
<br />

<div align="center">
  <!-- Crates version -->
  <a href="https://spring.io/projects/spring-boot#learn">
    <img alt="Static Badge" src="https://img.shields.io/badge/spring-boot?label=Spring%20Boot%203.1.3">
  </a>
 <a href="https://codecov.io/gh/wansenai/eairp" > 
    <img src="https://codecov.io/gh/wansenai/eairp/graph/badge.svg?token=6OO8JX0ZVV"/> 
 </a>
  <a href="#">
    <img alt="GitHub Workflow Status (with event)" src="https://img.shields.io/github/actions/workflow/status/wansenai/wansenerp/maven.yml">
  </a>
  <!-- commits -->
  <a href="#">
    <img alt="GitHub last commit (branch)" src="https://img.shields.io/github/last-commit/wansenai/wansenerp/master">
  </a>
   <a href="">
    <img src="https://img.shields.io/github/repo-size/wansenai/eairp"/>
  </a>
</div>
<br />

在ERP业务的基础上，我们扩展了GPT-3.5。个人或公司可以通过我们的系统对您的模型进行微调。
您可以通过简单的描述提供完全自动化的业务表单提交操作，还可以与GPT聊天、交互和查阅信息。
您可以通过Docker进行部署，以快速启动和使用。

它是完全免费的，如果这个项目对你有帮助，请点击Star。非常感谢。

## Project
[Enginsh](https://github.com/eairps/eairp/blob/master/README.md) / [简体中文](https://github.com/eairps/eairp/blob/master/README_ZH.md)

## Online preview
- 测试账号: admin
- 测试密码: 123456

## 快速开始
我们提供了一种更全面的Docker部署方法，可以在[Docker](https://github.com/eairps/eairp/blob/master/docker/README_ZH.md)文件夹中找到

### 先决条件
- Docker Engine 20.10+
- Docker Compose v2.17+

### 1. Docker Compose（推荐）

**适用场景**: 本地没有MySQL/Redis环境，需要快速启动完整的服务栈。

```bash
# Clone deployment repository
git clone https://github.com/eairps/eairp.git

cd eairp

# Start services
docker compose up -d
```

### 2. Docker 独立容器

**适用场景**: MySQL/Redis服务已经存在，需要自定义数据库配置。

**步骤 1**: 创建专用网络

```console
docker network create eairp-net
```

**步骤 2**: 启动 MySQL 容器

```console
docker run -d --name mysql-eairp \
  --network eairp-net \
  -p 3306:3306 \
  -v /path/to/mysql:/var/lib/mysql \
  -e MYSQL_ROOT_PASSWORD=123456 \
  -e MYSQL_USER=eairp \
  -e MYSQL_PASSWORD=123456 \
  mysql:8.3 \
  --character-set-server=utf8mb4 \
  --collation-server=utf8mb4_bin
```

**步骤 3**: 启动 Redis 容器

```console
docker run -d --name redis-eairp \
  --network eairp-net \
  -p 6379:6379 \
  -v /path/to/redis/data:/data \
  redis:7.0 \
  redis-server --requirepass 123456
```

**步骤 4**: 启动 Eairp 容器

配置参数：
|  环境变量	   | 说明  |  示例值  |  
|  ----  | ----  | ----  |
| SPRING_DATASOURCE_URL  | MySQL connection address | jdbc:mysql://mysql-eairp:3306/eairp |
| SPRING_REDIS_HOST	  | Redis host address | redis-eairp |
| API_BASE_URL		  | Front-end API basic path | http://your-domain.com/erp-api |

```console
docker run -d --name eairp \
  --network eairp-net \
  -p 3000:80 \
  -p 8088:8088 \
  -e SPRING_DATASOURCE_URL="jdbc:mysql://mysql-eairp:3306/eairp" \
  -e SPRING_DATASOURCE_USERNAME=eairp \
  -e SPRING_DATASOURCE_PASSWORD=123456 \
  -e SPRING_REDIS_HOST=redis-eairp \
  -e SPRING_REDIS_PASSWORD=123456 \
  wansenai/eairp:latest
```

### 部署常见问题

1. **eairp container /start.sh: no such file or directory**

此问题通常是由 `Windows` 对文本文件中行尾的处理引起的，这可能会影响容器中使用 `start.sh` 脚本。
在克隆存储库之前，请配置 Git 以防止自动转换行尾。
```bash
git config --global core.autocrlf false
```

## License

根据以下任一许可证之一，对本项目中的代码和文档进行许可：

- Apache License, Version 2.0, [LICENSE-APACHE](LICENSE-APACHE)
- MIT license [LICENSE-MIT](LICENSE-MIT)
根据您的选择。

## 社区
<img src="images/wechat-group-20250318.jpg" alt="WeChat Group" width="300" />

## 贡献
我们欢迎每一位贡献者，无论是在代码还是文档方面。

除非您另有明确说明，否则有意提交以纳入 根据Apache-2.0许可证的定义，您的作品应具有上述双重许可，没有任何附加条款或条件。
