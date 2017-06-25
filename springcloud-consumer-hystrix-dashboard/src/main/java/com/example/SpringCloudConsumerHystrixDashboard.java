package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard//打开熔断监控，可以实施监控请求的状态
@EnableFeignClients
@EnableCircuitBreaker
public class SpringCloudConsumerHystrixDashboard {
	
	public static void main(String[] args) {	
		
		SpringApplication.run(SpringCloudConsumerHystrixDashboard.class, args);
		
	}

}
