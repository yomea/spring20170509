package com.neo.remote.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.neo.remote.HelloRemote;

/**
 * 设置fallback，如果熔断，也就是远程服务不可用时，将会调用fallback
 * @author may
 *
 */
@Component
public class HelloRemoteHystrix implements HelloRemote{

    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "hello" +name+", this messge send failed ";
    }
}
