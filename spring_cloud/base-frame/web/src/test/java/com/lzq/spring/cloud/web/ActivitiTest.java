package com.lzq.spring.cloud.web;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 一个节点可以包含多个候选人
 * 或者一个节点也可以包括多个候选角色
 * 只要候选人属于候选角色，同样具有审批权限
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class ActivitiTest {
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private IdentityService identityService;

    @Test
    public void startProcessGroup(){
//        ProcessInstance instance = runtimeService.startProcessInstanceByKey("TestGroup");
//        System.out.println("Id:"+instance.getId());
//        List<Task> taskGroups = taskService.createTaskQuery().taskCandidateGroup("1").list();
//        taskGroups.forEach(task -> System.out.println(task.toString()));
        List<Task> taskUsers = taskService.createTaskQuery().taskCandidateUser("2").list();
        Task task = taskUsers.get(0);
        taskService.complete(task.getId());

        List<Task> taskUser1s = taskService.createTaskQuery().taskCandidateUser("1").list();
        taskUser1s.forEach(task1 -> System.out.println(task1.toString()));

        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser("4").list();
        taskList.forEach(task1 -> System.out.println(task1.toString()));
        task = taskList.get(0);
        Map<String,Object> map = new HashMap<>();
        map.put("pass",false);
        taskService.complete(task.getId(),map);

        List<Task> taskUser3s = taskService.createTaskQuery().taskCandidateUser("3").list();
        taskUser3s.forEach(task1 -> System.out.println(task1.toString()));

        taskUser1s = taskService.createTaskQuery().taskCandidateUser("1").list();
        taskUser1s.forEach(task1 -> System.out.println(task1.toString()));
//        taskUsers.forEach(task -> System.out.println(task.toString()));
//        List<Task> taskUser2s = taskService.createTaskQuery().taskCandidateUser("3").list();
//        taskUser2s.forEach(task -> System.out.println(task.toString()));
//        List<Task> taskUser1s = taskService.createTaskQuery().taskAssignee("1").list();
//        taskUser1s.forEach(task -> System.out.println(task.toString()));
    }

    @Test
    public void startProcess(){

        Map<String,Object> map = new HashMap<>();
        map.put("user1","test001");//设置用户1的处理人

        //启动流程
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("AskLeave",map);
        System.out.println("Id:"+instance.getId());

        //用户1获取任务
        List<Task> task1s = taskService.createTaskQuery().taskAssignee("test001").list();
        task1s.forEach(task-> System.out.println(task.toString()));
        map.put("user2","test002");//设置用户2的处理人
        taskService.complete(task1s.get(0).getId(),map);

        //用户2获取任务
        List<Task> task2s = taskService.createTaskQuery().taskAssignee("test002").list();
        task2s.forEach(task -> System.out.println(task.toString()));
        map.put("dPass",true);
        map.put("user3","test003");
        taskService.complete(task2s.get(0).getId(),map);

        //用户3获取任务
        List<Task> task3s = taskService.createTaskQuery().taskAssignee("test003").list();
        task3s.forEach(task -> System.out.println(task.toString()));
        map.put("zPass",true);
        taskService.complete(task3s.get(0).getId(),map);

        Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
        System.out.println(task==null);
    }

    @Test
    public void createGroup(){
        identityService.deleteGroup("1");
        identityService.deleteGroup("2");
        identityService.deleteGroup("3");
        Group group = identityService.newGroup("1");
        group.setName("研发部");
        Group group1 = identityService.newGroup("2");
        group1.setName("运营部");
        Group group2 = identityService.newGroup("3");
        group2.setName("商务部");
        identityService.saveGroup(group);
        identityService.saveGroup(group1);
        identityService.saveGroup(group2);
    }

    @Test
    public void createUser(){
        User user = identityService.newUser("1");
        user.setFirstName("三");
        user.setLastName("张");
        user.setEmail("zhangsan@qq.com");
        user.setPassword("zhangsan@123");

        User user1 = identityService.newUser("2");
        user1.setFirstName("四");
        user1.setLastName("李");
        user1.setEmail("lisi@qq.com");
        user1.setPassword("zhangsan@123");

        User user2 = identityService.newUser("3");
        user2.setFirstName("五");
        user2.setLastName("王");
        user2.setEmail("wangwu@qq.com");
        user2.setPassword("zhangsan@123");

        User user3 = identityService.newUser("4");
        user3.setFirstName("六");
        user3.setLastName("赵");
        user3.setEmail("zhaoliu@qq.com");
        user3.setPassword("zhangsan@123");

        identityService.saveUser(user);
        identityService.saveUser(user1);
        identityService.saveUser(user2);
        identityService.saveUser(user3);
    }

    @Test
    public void createUserAndGroupRelation()
    {
        //first = userId, second = groupId
        identityService.createMembership("1","1");
        identityService.createMembership("2","1");
        identityService.createMembership("3","2");
        identityService.createMembership("4","3");
    }
}
