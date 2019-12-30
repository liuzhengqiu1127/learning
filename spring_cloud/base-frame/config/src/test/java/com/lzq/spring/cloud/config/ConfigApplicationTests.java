package com.lzq.spring.cloud.config;

import com.lzq.spring.cloud.config.rabbit.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigApplicationTests {

	@Autowired
	private HelloSender helloSender;

	@Test
	public void hello(){
		helloSender.send();
	}

	@Test
	public void oneToMany(){
		for (int i=0; i < 100; i++){
			helloSender.send(i);
		}
	}

	@Test
	public void send(){
		helloSender.send1();
		helloSender.send2();
	}
}
