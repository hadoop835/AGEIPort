package com.alibaba.ageiport.task.server.service.impl;

import com.alibaba.ageiport.common.constants.RowStatus;
import com.alibaba.ageiport.common.constants.TaskSpecificationStatus;
import com.alibaba.ageiport.common.utils.BeanUtils;
import com.alibaba.ageiport.common.utils.TaskIdUtil;
import com.alibaba.ageiport.task.server.config.TaskServerConfig;
import com.alibaba.ageiport.task.server.entity.MainTaskInstanceEntity;
import com.alibaba.ageiport.task.server.entity.TaskSpecificationEntity;
import com.alibaba.ageiport.task.server.model.CreateTaskSpecificationRequest;
import com.alibaba.ageiport.task.server.model.CreateTaskSpecificationResponse;
import com.alibaba.ageiport.task.server.model.GetTaskSpecificationRequest;
import com.alibaba.ageiport.task.server.model.GetTaskSpecificationResponse;
import com.alibaba.ageiport.task.server.repository.TaskSpecificationRepository;
import com.alibaba.ageiport.task.server.service.TaskSpecificationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author Administrator
 */
@Service
public class TaskSpecificationServiceImpl implements TaskSpecificationService {
    protected TaskSpecificationRepository taskSpecificationRepository;
    protected TaskServerConfig taskServerConfig;
    @Autowired
    public  TaskSpecificationServiceImpl(TaskServerConfig taskServerConfig,TaskSpecificationRepository taskSpecificationRepository){
        this.taskServerConfig = taskServerConfig;
        this.taskSpecificationRepository = taskSpecificationRepository;
    }
    @Override
    public CreateTaskSpecificationResponse save(CreateTaskSpecificationRequest request) {
        TaskSpecificationEntity entity = BeanUtils.cloneProp(request, TaskSpecificationEntity.class);
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());
        entity.setRowStatus(RowStatus.VALID);
        entity.setRowVersion(1);
        entity.setStatus(TaskSpecificationStatus.ENABLE.getCode());
        entity.setEnv(this.taskServerConfig.getEnv());
        int success = this.taskSpecificationRepository.insert(entity);
        CreateTaskSpecificationResponse response = new CreateTaskSpecificationResponse();
        if(success>0){
            CreateTaskSpecificationResponse.Data data = new CreateTaskSpecificationResponse.Data();
            data.setId(entity.getId());
            response.setData(data);
            response.setSuccess(true);
            return response;
        }else{
            response.setSuccess(false);
            response.setCode("SERVER_EXCEPTION");
            response.setMessage("create task specification failed");
        }
        return response;
    }

    @Override
    public GetTaskSpecificationResponse findByTaskCode(GetTaskSpecificationRequest request) {
        QueryWrapper<TaskSpecificationEntity> wrapper = Wrappers.query();
        wrapper.eq("tenant",request.getTenant());
        wrapper.eq("namespace",request.getNamespace());
        wrapper.eq("app",request.getApp());
        wrapper.eq("env",this.taskServerConfig.getEnv());
        wrapper.eq("task_code",request.getTaskCode());
        TaskSpecificationEntity taskSpecificationEntity =  this.taskSpecificationRepository.selectOne(wrapper);
        GetTaskSpecificationResponse response = new GetTaskSpecificationResponse();
        response.setSuccess(true);
        if(Objects.nonNull(taskSpecificationEntity)){
            GetTaskSpecificationResponse.Data data = BeanUtils.cloneProp(taskSpecificationEntity, GetTaskSpecificationResponse.Data.class);
            response.setData(data);
        }
        return response;
    }
}
