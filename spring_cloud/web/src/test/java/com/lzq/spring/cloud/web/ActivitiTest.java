package com.lzq.spring.cloud.web;

import com.lzq.spring.cloud.web.activi.ActivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class ActivitiTest {
    @Resource(name = "activityService2")
    private ActivityService activityService;

    @Test
    public void leaveProcess(){
        activityService.startActivity();
    }
}
