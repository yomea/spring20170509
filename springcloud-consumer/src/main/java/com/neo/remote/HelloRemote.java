package com.neo.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neo.remote.impl.HelloRemoteHystrix;

/**
 * Created by summer on 2017/5/11.
 */
//@FeignClient(name= "spring-cloud-producer")指定要到注册中心寻找的服务名
//当服务可用时条用fallback指定的类
@FeignClient(name= "spring-cloud-producer", fallback=HelloRemoteHystrix.class)
public interface HelloRemote {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);


}
