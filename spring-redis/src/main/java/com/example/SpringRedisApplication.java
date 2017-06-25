package com.example;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRedisApplication {

	public static void main(String[] args) {
	//	SpringApplication.run(SpringRedisApplication.class, args);
		
		
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("延时1s中执行！！！");
				
			}
		}, 1000);
		
	}
}
