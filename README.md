# Getting Started

## Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/maven-plugin/)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-security)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#using-boot-devtools)

## Guides
The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)



## 一、`Spring Security`的学习

### 1）、环境在`Spring Boot 2.2.6.RELEASE` 和 `Spring Security 2.2.6.RELEASE`的时候无论配置没配置对应的安全拦截类，都会有默认的安全验证和访问控制，即需要先登录在进入web

> 解决方案：
>   1. 在`Spring Boot`的配置文件中添加`Spring Security`的用户信息。在进入web时使用此账户进行登录
>   2. 在启动类处禁用`spring Security`的安全设置,禁用设置如下：
```
 package com.yq;
 
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
 
 @SpringBootApplication(scanBasePackages = {"com.yq"})
 @EnableAutoConfiguration(exclude = {
 org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
 })
 public class WebSecurityDemoApp {
     private static final Logger log = LoggerFactory.getLogger(WebSecurityDemoApp.class);
 
     public static void main(String[] args) {
         SpringApplication.run(WebSecurityDemoApp.class, args);
     }
 
 }
```

### 2）、在`Spring Boot 2.2.6.RELEASE`版本不支持`thymeleaf`和`Spring Security`的整合
> 最高支持 `Spring Boot 2.0.9.RELEASE`版本

<!--角  色：<span sec:authentication="principal.getAuthorities()"></span>-->


