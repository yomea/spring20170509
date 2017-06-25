package com.example.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="spring-cloud-producer")
public interface HelloRemote {

	@RequestMapping("/hello")
	public String hello(@RequestParam("name") String name);
	
}
