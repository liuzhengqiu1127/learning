package com.lzq.spring.cloud.web;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ActivitiTest extends WebApplicationTests {

    @Autowired
    RuntimeService runtimeService;

    @Test
    public void testStartProcess(){
        Map<String, Object> variables = new HashMap<>();
        variables.put("applicantName", "John Doe");
        variables.put("email", "john.doe@activiti.com");
        variables.put("phoneNumber", "123456789");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", variables);
        System.out.println(processInstance.getName());
    }
}
