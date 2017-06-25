package com.example.service;

import org.springframework.cache.annotation.Cacheable;  
import org.springframework.stereotype.Service;

import com.example.entity.Address;
import com.example.entity.User;  
  
 
@Service  
public class DemoService {  
	//这里的value会储存keyGenerator生成的key，然后通过这个key去查找到相应的对象
    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")  
    public User findUser(Long id,String firstName,String lastName){  
        System.out.println("无缓存的时候调用这里"); 
        return new User(id,firstName,lastName);  
    }  
    @Cacheable(value = "addresscache",keyGenerator = "wiselyKeyGenerator")  
    public Address findAddress(Long id,String province,String city){  
        System.out.println("无缓存的时候调用这里");  
        return new Address(id,province,city);  
    }  
}  