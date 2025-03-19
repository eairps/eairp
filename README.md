<h1 align="center"><a href="#" target="_blank">Enterprise Resource Plan</a></h1>
<div align="center">
 <strong>
  Next generation artificial intelligent ERP system
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

On the basis of ERP business, we have expanded GPT-3.5. individually or company can fine tune your model through our system. 
You can provide fully automated business form submission operations through your simple description, and you can chat, interact, and consult information with GPT.
You can deploy through Docker to quickly start and use.

It's completely free, if this project is helpful to you, please click on Star. Thank you.

## Project
[Enginsh](https://github.com/wansenai/eairp/blob/master/README.md) / [简体中文](https://github.com/wansenai/eairp/blob/master/README_ZH.md)

## Online preview
- test account: admin
- test password: 123456

## Quick Start

We provide a more comprehensive Docker deployment method, which can be found in [docker folder](https://github.com/eairps/eairp/blob/master/docker/README.md)

### Prerequisites
- Docker Engine 20.10+
- Docker Compose v2.17+

### 1. Docker Compose (Recommended)

**Applicable scenarios**: There is no MySQL/Redis environment locally, and a complete service stack needs to be started quickly.

```bash
# Clone deployment repository
git clone https://github.com/eairps/eairp.git

cd eairp

# Start services
docker compose up -d
```

### 2. Docker standalone container

**Applicable scenarios**: MySQL/Redis service already exists, and custom database configuration is required.

**Step 1**: Create a Private Network

```console
docker network create eairp-net
```

**Step 2**: Start the MySQL container

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

**Step 3**: Start the Redis container

```console
docker run -d --name redis-eairp \
  --network eairp-net \
  -p 6379:6379 \
  -v /path/to/redis/data:/data \
  redis:7.0 \
  redis-server --requirepass 123456
```

**Step 4**: Start the Eairp container

Configuration parameters:
|  Environment variables   | Explanation  |  Example Value  |  
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

### Deployment FAQ

1. **eairp container /start.sh: no such file or directory**

This issue is commonly caused by Windows' handling of line endings in text files, which can affect scripts like `start.sh` used in Docker containers.

Before cloning the repository, configure Git to prevent automatic conversion of line endings. 
```bash
git config --global core.autocrlf false
```

## License

Licensed under either of

- Apache License, Version 2.0, [LICENSE-APACHE](LICENSE-APACHE)
- MIT license [LICENSE-MIT](LICENSE-MIT)

at your option.

## Community
<img src="images/wechat-group-20250318.jpg" alt="WeChat Group" width="300" />

## Contribution
We welcome every contributor, both in terms of code and documentation.

Unless you explicitly state otherwise, any contribution intentionally submitted for inclusion in the
work by you, as defined in the Apache-2.0 license, shall be dual licensed as above, without any
additional terms or conditions.
