package com.alibaba.ageiport.task.server.http;

import com.alibaba.ageiport.task.server.model.CreateTaskSpecificationRequest;
import com.alibaba.ageiport.task.server.model.CreateTaskSpecificationResponse;
import com.alibaba.ageiport.task.server.model.GetTaskSpecificationRequest;
import com.alibaba.ageiport.task.server.model.GetTaskSpecificationResponse;
import com.alibaba.ageiport.task.server.service.TaskSpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author lingyi
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1")
public class TaskSpecificationApiV1 {


    private TaskSpecificationService taskSpecificationService;

    @Autowired
    public TaskSpecificationApiV1(TaskSpecificationService TaskSpecificationService) {
        this.taskSpecificationService = TaskSpecificationService;
    }

    @PostMapping("/CreateTaskSpecification")
    public CreateTaskSpecificationResponse createTaskSpecification(@RequestBody CreateTaskSpecificationRequest request) {
        Objects.requireNonNull(request);
        try{
            return this.taskSpecificationService.save(request);
        }catch (Exception ex){
            log.error("TaskSpecificationApiV1#create failed, request:{}", request, ex);
            CreateTaskSpecificationResponse response = new CreateTaskSpecificationResponse();
            response.setSuccess(false);
            response.setCode("SERVER_EXCEPTION");
            response.setMessage("create task specification failed");
            return response;
        }
    }

    @PostMapping("/GetTaskSpecification")
    public GetTaskSpecificationResponse getTaskSpecification(@RequestBody GetTaskSpecificationRequest request) {
        Objects.requireNonNull(request);
        try {
           return this.taskSpecificationService.findByTaskCode(request);
        }catch (Exception ex){
            log.error("TaskSpecificationApiV1#getTaskSpecification failed, request:{}", request, ex);
            GetTaskSpecificationResponse response = new GetTaskSpecificationResponse();
            response.setSuccess(false);
            response.setCode("SERVER_EXCEPTION");
            response.setMessage("get task specification failed");
            return response;
        }
    }


}
