package com.alibaba.ageiport.task.server.service.impl;

import com.alibaba.ageiport.common.constants.RowStatus;
import com.alibaba.ageiport.common.constants.SubTaskStatus;
import com.alibaba.ageiport.common.utils.BeanUtils;
import com.alibaba.ageiport.common.utils.JsonUtil;
import com.alibaba.ageiport.common.utils.TaskIdUtil;
import com.alibaba.ageiport.task.server.config.TaskServerConfig;
import com.alibaba.ageiport.task.server.entity.MainTaskInstanceEntity;
import com.alibaba.ageiport.task.server.entity.SubTaskInstanceEntity;
import com.alibaba.ageiport.task.server.model.*;
import com.alibaba.ageiport.task.server.repository.MainTaskInstanceRepository;
import com.alibaba.ageiport.task.server.repository.SubTaskInstanceRepository;
import com.alibaba.ageiport.task.server.repository.query.TenantAppQuery;
import com.alibaba.ageiport.task.server.service.SubTaskInstanceService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SubTaskInstanceServiceImpl implements SubTaskInstanceService {
    protected TaskServerConfig taskServerConfig;
    protected SubTaskInstanceRepository subTaskInstanceRepository;
    protected MainTaskInstanceRepository mainTaskInstanceRepository;

    @Autowired
    public SubTaskInstanceServiceImpl(TaskServerConfig taskServerConfig,MainTaskInstanceRepository mainTaskInstanceRepository,SubTaskInstanceRepository subTaskInstanceRepository){
        this.taskServerConfig =taskServerConfig;
        this.subTaskInstanceRepository =subTaskInstanceRepository;
        this.mainTaskInstanceRepository = mainTaskInstanceRepository;
    }
    @Override
    public CreateSubTaskInstancesResponse save(CreateSubTaskInstancesRequest request) {
        QueryWrapper<MainTaskInstanceEntity> wrapper = Wrappers.query();
        wrapper.eq("tenant",request.getTenant());
        wrapper.eq("namespace",request.getNamespace());
        wrapper.eq("app",request.getApp());
        wrapper.eq("env",this.taskServerConfig.getEnv());
        wrapper.eq("main_task_id",request.getMainTaskId());
        CreateSubTaskInstancesResponse response = new CreateSubTaskInstancesResponse();
        MainTaskInstanceEntity mainTaskInstanceEntity =  this.mainTaskInstanceRepository.selectOne(wrapper);
        if(Objects.nonNull(mainTaskInstanceEntity)){
            String mainTaskId = request.getMainTaskId();
            List<String> subTaskIds = new ArrayList<>();
            List<CreateSubTaskInstancesRequest.SubTaskInstance> subTaskInstances = request.getSubTaskInstances();
            List<SubTaskInstanceEntity> entities = new ArrayList<>(subTaskInstances.size());
            for (CreateSubTaskInstancesRequest.SubTaskInstance subTaskInstance : subTaskInstances) {
                Integer subTaskNo = subTaskInstance.getSubTaskNo();
                String subTaskId = TaskIdUtil.genSubTaskId(mainTaskId, subTaskNo);
                subTaskIds.add(subTaskId);
                SubTaskInstanceEntity subEntity = BeanUtils.cloneProp(mainTaskInstanceEntity, SubTaskInstanceEntity.class);
                subEntity.setId(null);
                subEntity.setMainTaskId(mainTaskId);
                subEntity.setSubTaskId(subTaskId);
                subEntity.setSubTaskNo(subTaskNo);
                subEntity.setTenant(request.getTenant());
                subEntity.setNamespace(request.getNamespace());
                subEntity.setRowStatus(RowStatus.VALID);
                subEntity.setRowVersion(1);
                subEntity.setStatus(SubTaskStatus.NEW.getCode());
                subEntity.setApp(request.getApp());
                subEntity.setEnv(this.taskServerConfig.getEnv());
                subEntity.setBizQuery(subTaskInstance.getBizQuery());
                subEntity.setRuntimeParam(subTaskInstance.getRuntimeParam());
               int success =  this.subTaskInstanceRepository.insert(subEntity);
            }
            CreateSubTaskInstancesResponse.Data data = new CreateSubTaskInstancesResponse.Data();
            data.setSubTaskIds(subTaskIds);
            response.setSuccess(true);
            response.setData(data);
        }else{
            response.setSuccess(false);
            response.setCode("NOT_FOUND");
        }
        return response;
    }

    @Override
    public GetSubTaskInstanceResponse findBySubTaskId(GetSubTaskInstanceRequest request) {
        QueryWrapper<SubTaskInstanceEntity> wrapper = Wrappers.query();
        wrapper.eq("tenant",request.getTenant());
        wrapper.eq("namespace",request.getNamespace());
        wrapper.eq("app",request.getApp());
        wrapper.eq("env",this.taskServerConfig.getEnv());
        wrapper.eq("sub_task_id",request.getSubTaskId());
        SubTaskInstanceEntity entity = this.subTaskInstanceRepository.selectOne(wrapper);
        GetSubTaskInstanceResponse response = new GetSubTaskInstanceResponse();
        response.setSuccess(true);
        if(Objects.nonNull(entity)){
            GetSubTaskInstanceResponse.Data data = BeanUtils.cloneProp(entity, GetSubTaskInstanceResponse.Data.class);
            response.setData(data);
        }
        return response;
    }

    @Override
    public UpdateSubTaskInstanceResponse update(UpdateSubTaskInstanceRequest request,GetSubTaskInstanceResponse getSubTaskInstanceResponse) {
        SubTaskInstanceEntity subTaskInstanceEntity =  modifyEntity(request,getSubTaskInstanceResponse,this.taskServerConfig.getEnv());
        int success =  this.subTaskInstanceRepository.updateById(subTaskInstanceEntity);
        UpdateSubTaskInstanceResponse response = new UpdateSubTaskInstanceResponse();
        if(success > 0){
            response.setSuccess(true);
        }else {
            response.setSuccess(false);
            response.setCode("NOT_FOUND");
        }
        return response;
    }


    private static SubTaskInstanceEntity modifyEntity(UpdateSubTaskInstanceRequest request,GetSubTaskInstanceResponse getSubTaskInstanceResponse, String env) {
        SubTaskInstanceEntity entity = BeanUtils.cloneProp(getSubTaskInstanceResponse.getData(),SubTaskInstanceEntity.class);
        entity.setEnv(env);
        if (request.getDataTotalCount() != null) {
            entity.setDataTotalCount(request.getDataTotalCount());
        }
        if (request.getDataProcessedCount() != null) {
            entity.setDataProcessedCount(request.getDataProcessedCount());
        }
        if (request.getDataSuccessCount() != null) {
            entity.setDataSuccessCount(request.getDataSuccessCount());
        }
        if (request.getDataFailedCount() != null) {
            entity.setDataFailedCount(request.getDataFailedCount());
        }
        if (request.getGmtStart() != null) {
            entity.setGmtStart(request.getGmtStart());
        }
        if (request.getGmtDispatch() != null) {
            entity.setGmtDispatch(request.getGmtDispatch());
        }
        if (request.getGmtExecute() != null) {
            entity.setGmtExecute(request.getGmtExecute());
        }
        if (request.getGmtFinished() != null) {
            entity.setGmtFinished(request.getGmtFinished());
        }
        if (request.getGmtExpired() != null) {
            entity.setGmtExpired(request.getGmtExpired());
        }
        if (request.getRetryTimes() != null) {
            entity.setRetryTimes(request.getRetryTimes());
        }
        if (request.getResultCode() != null) {
            entity.setResultCode(request.getResultCode());
        }
        if (request.getResultMessage() != null) {
            entity.setResultMessage(request.getResultMessage());
        }

        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
        if (request.getLog() != null) {
            entity.setLog(request.getLog());
        }
        if (request.getHost() != null) {
            entity.setHost(request.getHost());
        }
        if (request.getTraceId() != null) {
            entity.setTraceId(request.getTraceId());
        }

        if (JsonUtil.isJson(request.getRuntimeParam())) {
            String runtimeParam = JsonUtil.merge(entity.getRuntimeParam(), request.getRuntimeParam());
            entity.setRuntimeParam(runtimeParam);
        }

        if (JsonUtil.isJson(request.getFeature())) {
            String feature = JsonUtil.merge(entity.getFeature(), request.getFeature());
            entity.setFeature(feature);
        }
        entity.setRowVersion(entity.getRowVersion() + 1);
        return entity;
    }
}
