# 📋 项目概览
# 【项目名称】Patient Management System | 患者管理系统
分布式微服务架构，5个独立服务模块
分布式微服务管理平台

 
## 1. 🎯项目描述
开发了分布式微服务架构的患者管理系统，包含认证、患者、账单、分析等5个核心服务模块，
采用API网关统一入口，实现了系统的模块化、可扩展的架构设计。
## 2. 核心模块
<img width="1920" height="1080" alt="Development Architecture" src="https://github.com/user-attachments/assets/65272f73-6313-43dd-8a64-b574866eb3d8" />

功能模块	说明
Patient Service	患者信息管理（增删改查）、JPA数据持久化、PostgreSQL数据库
Auth Service	基于JWT的身份认证、Spring Security权限控制、用户角色管理
Billing Service	账单管理、gRPC微服务通信
Analytics Service	数据分析、Kafka消息队列集成、protobuf协议
API Gateway	采用Spring Cloud Gateway，使用WebFlux异步处理请求
## 3. 技术栈（按重点顺序）
**后端框架**：

Spring Boot 4.0（最新版本）+ Spring Security + Spring Data JPA
Spring Cloud Gateway（API网关层）

  **通信与协议：**
  
REST API + 文档化（SpringDoc OpenAPI/Swagger）
gRPC + Protocol Buffers（微服务间高效通信）
Apache Kafka（消息驱动）

**数据库：**

PostgreSQL（生产数据库）
H2（单元测试内存数据库）

**开发工具：**

Java 21（最新LTS版本）
Maven 3.9.9（构建管理）
Docker（容器化部署，多层构建优化）

**其他：**

JWT Token认证
数据验证（Jakarta Validation）
异步反应式编程（Project Reactor）


【技术栈】Java 21 | Spring Boot 4.0 | gRPC | Kafka | PostgreSQL | Docker

【项目描述】
设计并开发了一套完整的分布式医疗管理系统，从零搭建微服务架构：
• 核心模块：患者信息服务、用户认证服务、账单管理、数据分析服务
• 采用API网关模式，统一管理服务路由和请求分发
• 实现了服务间的异步通信（gRPC + Kafka）

【技术实现】
• 后端框架：Spring Boot + Spring Security + Spring Data JPA
  - 使用Spring Security + JWT Token实现身份认证和权限控制
  - 自定义过滤器和拦截器处理跨服务调用
  
• 微服务架构：
  - API Gateway（Spring Cloud Gateway）：异步WebFlux处理，支持高并发
  - gRPC通信：高效的二进制协议，用于服务间同步调用
  - Kafka消息队列：Analytics Service异步处理数据分析任务
  
• 数据持久化：PostgreSQL + Spring Data JPA + Hibernate
  - 设计规范化数据模型，支持UUID主键和业务约束
  - 单元测试使用H2内存数据库实现快速验证
  
• 容器化与部署：Docker多层构建
  - Maven构建层优化依赖缓存，加快镜像构建速度
  - 支持一键部署多个服务实例
