package com.alibaba.ageiport.task.server.http;

import com.alibaba.ageiport.common.utils.BeanUtils;
import com.alibaba.ageiport.task.server.model.*;
import com.alibaba.ageiport.task.server.repository.query.TenantAppQuery;
import com.alibaba.ageiport.task.server.service.SubTaskInstanceService;
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
public class SubTaskInstanceApiV1 {


    private SubTaskInstanceService subTaskInstanceService;


    @Autowired
    public SubTaskInstanceApiV1(SubTaskInstanceService subTaskInstanceService) {
        this.subTaskInstanceService = subTaskInstanceService;
    }

    @PostMapping("/CreateSubTaskInstances")
    public  CreateSubTaskInstancesResponse createMainTaskInstances(@RequestBody CreateSubTaskInstancesRequest request) {
        Objects.requireNonNull(request);
        try{
            return this.subTaskInstanceService.save(request);
        }catch (Exception ex){
            log.error("SubTaskInstanceApiV1#createMainTaskInstances failed, request:{}", request, ex);
            CreateSubTaskInstancesResponse response = new CreateSubTaskInstancesResponse();
            response.setSuccess(false);
            response.setCode("SERVER_EXCEPTION");
            response.setMessage("get main task instance failed, ");
            return response;
        }

    }

    @PostMapping("/GetSubTaskInstance")
    public GetSubTaskInstanceResponse getSubTaskInstance(@RequestBody GetSubTaskInstanceRequest request) {
        Objects.requireNonNull(request);

        try{
            return this.subTaskInstanceService.findBySubTaskId(request);
        }catch (Exception ex){
            log.error("MainTaskInstanceApiV1#getMainTaskInstance failed, request:{}", request, ex);
            GetSubTaskInstanceResponse response = new GetSubTaskInstanceResponse();
            response.setSuccess(false);
            response.setCode("SERVER_EXCEPTION");
            response.setMessage("get sub task instance failed");
            return response;
        }
    }


    @PostMapping("/UpdateSubTaskInstance")
    public UpdateSubTaskInstanceResponse updateMainTaskInstance(@RequestBody UpdateSubTaskInstanceRequest request) {
        Objects.requireNonNull(request);
        GetSubTaskInstanceRequest getSubTaskInstanceRequest = BeanUtils.cloneProp(request,GetSubTaskInstanceRequest.class);
        GetSubTaskInstanceResponse getSubTaskInstanceResponse =  this.subTaskInstanceService.findBySubTaskId(getSubTaskInstanceRequest);
        if(Objects.isNull(getSubTaskInstanceResponse)){
            UpdateSubTaskInstanceResponse response = new UpdateSubTaskInstanceResponse();
            response.setSuccess(false);
            response.setCode("NOT_FOUND");
            return response;
        }
        try{
            return this.subTaskInstanceService.update(request);
        }catch (Exception ex){
            log.error("SubTaskInstanceApiV1#updateMainTaskInstance failed, request:{}", request, ex);
            UpdateSubTaskInstanceResponse response = new UpdateSubTaskInstanceResponse();
            response.setSuccess(false);
            response.setCode("SERVER_EXCEPTION");
            response.setMessage("get sub task instance failed");
            return response;
        }

    }


}
