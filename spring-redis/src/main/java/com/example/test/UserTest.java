package com.example.test;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.SpringRedisApplication;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(SpringRedisApplication.class)
@SpringBootTest(classes={SpringRedisApplication.class})
public class UserTest {
	
	@Inject
	@Named("stringRedisTemplate")
	private StringRedisTemplate srt;

	@Test
	public void test() {
		ValueOperations<String, String> vo = srt.opsForValue();
		vo.set("oo:xx:oo", "xx:oo");
	}
	
	@Test
	public void list() {
		ListOperations<String, String> lo = srt.opsForList();
		lo.leftPush("list", "a");
		lo.leftPush("list", "b");
		lo.leftPush("list", "c");
	}
	
	
	
}
