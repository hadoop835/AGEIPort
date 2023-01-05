package com.alibaba.ageiport.task.server.service;

import com.alibaba.ageiport.task.server.model.*;
import com.alibaba.ageiport.task.server.repository.query.TenantAppQuery;

/**
 * @author Administrator
 */
public interface SubTaskInstanceService {

    CreateSubTaskInstancesResponse  save(CreateSubTaskInstancesRequest request);

    GetSubTaskInstanceResponse findBySubTaskId(GetSubTaskInstanceRequest request);

    UpdateSubTaskInstanceResponse update(UpdateSubTaskInstanceRequest request,GetSubTaskInstanceResponse getSubTaskInstanceResponse);
}
