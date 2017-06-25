package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Address;
import com.example.entity.User;
import com.example.service.DemoService;  
  
@Controller  
public class DemoController {  
  
    @Autowired  
    DemoService demoService;  
  
    @RequestMapping("/test")  
    @ResponseBody
    public String putCache(HttpSession session){  
        demoService.findUser(1l,"wu","zhenhong");  
        demoService.findAddress(1l,"jiangxi","jiujiang");  
        session.setAttribute("sesssion", "session");
        return "";  
    }  
    @RequestMapping("/test2")  
    @ResponseBody  
    public String testCache(){  
        User user = demoService.findUser(1l,"wu","zhenhong");  
        Address address =demoService.findAddress(1l,"jiangxi","jiujiang");  
        System.out.println("user:"+"/"+user.getFirstName()+"/"+user.getLastName());  
        System.out.println("address:"+"/"+address.getProvince()+"/"+address.getCity());  
        return "";  
    }  
}  
