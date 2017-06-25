package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer//使用注册中心，感觉比dubbo牛逼
//启动后输入http://localhost:8000/
public class SpringcloudStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudStartApplication.class, args);
	}
}
