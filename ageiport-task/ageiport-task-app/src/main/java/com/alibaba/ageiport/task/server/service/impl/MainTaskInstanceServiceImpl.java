package com.alibaba.ageiport.task.server.service.impl;

import com.alibaba.ageiport.common.constants.RowStatus;
import com.alibaba.ageiport.common.constants.TaskStatus;
import com.alibaba.ageiport.common.utils.BeanUtils;
import com.alibaba.ageiport.common.utils.JsonUtil;
import com.alibaba.ageiport.common.utils.StringUtils;
import com.alibaba.ageiport.common.utils.TaskIdUtil;
import com.alibaba.ageiport.task.server.config.TaskServerConfig;
import com.alibaba.ageiport.task.server.entity.MainTaskInstanceEntity;
import com.alibaba.ageiport.task.server.entity.SubTaskInstanceEntity;
import com.alibaba.ageiport.task.server.model.*;
import com.alibaba.ageiport.task.server.repository.MainTaskInstanceRepository;
import com.alibaba.ageiport.task.server.repository.query.TenantAppQuery;
import com.alibaba.ageiport.task.server.service.MainTaskInstanceService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author Administrator
 */
@Service
public class MainTaskInstanceServiceImpl implements MainTaskInstanceService {

   protected TaskServerConfig taskServerConfig;

   private MainTaskInstanceRepository mainTaskInstanceRepository;

   public  MainTaskInstanceServiceImpl(TaskServerConfig taskServerConfig,MainTaskInstanceRepository mainTaskInstanceRepository){
       this.taskServerConfig = taskServerConfig;
       this.mainTaskInstanceRepository = mainTaskInstanceRepository;
   }

    @Override
    public String save(CreateMainTaskInstanceRequest request) {
        String mainTaskId = TaskIdUtil.genMainTaskId();
        MainTaskInstanceEntity entity = BeanUtils.cloneProp(request, MainTaskInstanceEntity.class);
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());
        entity.setMainTaskId(mainTaskId);
        entity.setRowStatus(RowStatus.VALID);
        entity.setRowVersion(1);
        entity.setRetryTimes(0);
        entity.setStatus(TaskStatus.NEW.getCode());
        entity.setEnv(taskServerConfig.getEnv());
        int insert= this.mainTaskInstanceRepository.insert(entity);
        if(insert>0){
            return mainTaskId;
        }
        return null;
    }

    @Override
    public GetMainTaskInstanceResponse findByMainTaskId(GetMainTaskInstanceRequest request) {
        QueryWrapper<MainTaskInstanceEntity> wrapper = Wrappers.query();
        wrapper.eq("tenant",request.getTenant());
        wrapper.eq("namespace",request.getNamespace());
        wrapper.eq("app",request.getApp());
        wrapper.eq("env",this.taskServerConfig.getEnv());
        wrapper.eq("id",request.getMainTaskId());
        MainTaskInstanceEntity entity = this.mainTaskInstanceRepository.selectOne(wrapper);
        GetMainTaskInstanceResponse response = new GetMainTaskInstanceResponse();
        response.setSuccess(true);
        if(Objects.nonNull(entity)){
            GetMainTaskInstanceResponse.Data data = BeanUtils.cloneProp(entity, GetMainTaskInstanceResponse.Data.class);
            response.setData(data);
        }
       return response;
    }

    @Override
    public UpdateMainTaskInstanceResponse update(UpdateMainTaskInstanceRequest request) {
        MainTaskInstanceEntity mainTaskInstanceEntity =  modifyEntity(request,this.taskServerConfig.getEnv());
       int success =  this.mainTaskInstanceRepository.updateById(mainTaskInstanceEntity);
        UpdateMainTaskInstanceResponse response = new UpdateMainTaskInstanceResponse();
       if(success > 0){
           response.setSuccess(true);
       }else {
           response.setSuccess(false);
           response.setCode("NOT_FOUND");
       }
         return response;
    }


    private static MainTaskInstanceEntity modifyEntity(UpdateMainTaskInstanceRequest request,String env) {
        MainTaskInstanceEntity entity = BeanUtils.cloneProp(request,MainTaskInstanceEntity.class);
        entity.setEnv(env);
        if (StringUtils.isNotBlank(request.getBizTaskName())) {
            entity.setBizTaskName(request.getBizTaskName());
        }
        if (StringUtils.isNotBlank(request.getBizKey())) {
            entity.setBizKey(request.getBizKey());
        }
        if (StringUtils.isNotBlank(request.getBizQuery())) {
            entity.setBizQuery(request.getBizQuery());
        }
        if (request.getSubTotalCount() != null) {
            entity.setSubTotalCount(request.getSubTotalCount());
        }
        if (request.getSubFinishedCount() != null) {
            entity.setSubFinishedCount(request.getSubFinishedCount());
        }
        if (request.getSubSuccessCount() != null) {
            entity.setSubSuccessCount(request.getSubSuccessCount());
        }
        if (request.getSubFailedCount() != null) {
            entity.setSubFailedCount(request.getSubFailedCount());
        }
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
        if (request.getLog() != null) {
            entity.setResultMessage(request.getResultMessage());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
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
