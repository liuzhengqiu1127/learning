package com.lzq.spring.cloud.web.activi;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("activityService")
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;

    @Override
    public boolean startActivity() {
        log.info("工作流启动...");
        Map<String,Object> map = new HashMap<>();
        map.put("apply","liuzhengqiu");
        map.put("approve","lisi");

        //流动启动
        ExecutionEntity executionEntity = (ExecutionEntity) runtimeService.startProcessInstanceByKey("leave2",map);

        String processId = executionEntity.getId();
        Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();

        log.info("task 第一步:{}",task);
        taskService.complete(task.getId(),map);//完成第一步申请

        task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        log.info("task 第二步:{}",task);
        String taskId2 = task.getId();
        map.put("pass",false);
        taskService.complete(taskId2,map);//驳回申请

        task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        log.info("task 第三步:{}",task);
        log.info("工作流结束");
        return false;
    }
}
