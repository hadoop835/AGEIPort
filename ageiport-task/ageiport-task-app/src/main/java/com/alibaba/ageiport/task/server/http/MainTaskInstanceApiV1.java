package com.alibaba.ageiport.task.server.http;

import com.alibaba.ageiport.common.utils.BeanUtils;
import com.alibaba.ageiport.task.server.config.TaskServerConfig;
import com.alibaba.ageiport.task.server.model.*;
import com.alibaba.ageiport.task.server.repository.query.TenantAppQuery;
import com.alibaba.ageiport.task.server.service.MainTaskInstanceService;
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
@RestController
@RequestMapping(value = "/v1")
@Slf4j
public class MainTaskInstanceApiV1 {

    private MainTaskInstanceService mainTaskInstanceService;

    @Autowired
    public MainTaskInstanceApiV1(MainTaskInstanceService mainTaskInstanceService) {
        this.mainTaskInstanceService = mainTaskInstanceService;
    }

    @PostMapping("/CreateMainTaskInstance")
    public CreateMainTaskInstanceResponse createMainTaskInstance(@RequestBody CreateMainTaskInstanceRequest request) {
        Objects.requireNonNull(request);
      String  mainTaskId =  this.mainTaskInstanceService.save(request);
        if(Objects.nonNull(mainTaskId)){
            CreateMainTaskInstanceResponse response = new CreateMainTaskInstanceResponse();
            response.setSuccess(true);
            CreateMainTaskInstanceResponse.Data data = new CreateMainTaskInstanceResponse.Data();
            data.setMainTaskId(mainTaskId);
            response.setData(data);
            return response;
        }else{
            log.error("MainTaskInstanceApiV1#create failed, request:{}", request);
            CreateMainTaskInstanceResponse response = new CreateMainTaskInstanceResponse();
            response.setSuccess(false);
            response.setCode("SERVER_EXCEPTION");
            response.setMessage("create task failed");
            return response;
        }

    }

    @PostMapping("/GetMainTaskInstance")
    public GetMainTaskInstanceResponse getMainTaskInstance(@RequestBody GetMainTaskInstanceRequest request) {
        Objects.requireNonNull(request);
        try{
            return this.mainTaskInstanceService.findByMainTaskId(request);
        }catch (Exception ex){
            log.error("MainTaskInstanceApiV1#getMainTaskInstance failed, request:{}", request, ex);
            GetMainTaskInstanceResponse response = new GetMainTaskInstanceResponse();
            response.setSuccess(false);
            response.setCode("SERVER_EXCEPTION");
            response.setMessage("get main task instance failed");
            return response;
        }
    }


    @PostMapping("/UpdateMainTaskInstance")
    public UpdateMainTaskInstanceResponse updateMainTaskInstance(@RequestBody UpdateMainTaskInstanceRequest request) {
        Objects.requireNonNull(request);
        GetMainTaskInstanceRequest getMainTaskInstanceRequest = BeanUtils.cloneProp(request,GetMainTaskInstanceRequest.class);
        GetMainTaskInstanceResponse getMainTaskInstanceResponse =  this.mainTaskInstanceService.findByMainTaskId(getMainTaskInstanceRequest);
        if(Objects.isNull(getMainTaskInstanceResponse)){
            UpdateMainTaskInstanceResponse response = new UpdateMainTaskInstanceResponse();
            response.setSuccess(false);
            response.setCode("NOT_FOUND");
            return response;
        }
        try{

            return this.mainTaskInstanceService.update(request,getMainTaskInstanceResponse);
        }catch (Exception ex){
            log.error("MainTaskInstanceApiV1#updateMainTaskInstance failed, request:{}", request, ex);
            UpdateMainTaskInstanceResponse response = new UpdateMainTaskInstanceResponse();
            response.setSuccess(false);
            response.setCode("SERVER_EXCEPTION");
            response.setMessage("get main task instance failed, ");
            return response;
        }
    }


}
