package com.alibaba.ageiport.task.server.service;

import com.alibaba.ageiport.task.server.entity.MainTaskInstanceEntity;
import com.alibaba.ageiport.task.server.model.*;
import com.alibaba.ageiport.task.server.repository.query.TenantAppQuery;

public interface MainTaskInstanceService {

    String  save(CreateMainTaskInstanceRequest request);

    GetMainTaskInstanceResponse findByMainTaskId(GetMainTaskInstanceRequest request);

    UpdateMainTaskInstanceResponse update(UpdateMainTaskInstanceRequest request,GetMainTaskInstanceResponse response);
}
