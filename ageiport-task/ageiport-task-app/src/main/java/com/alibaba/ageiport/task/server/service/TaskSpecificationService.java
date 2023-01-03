package com.alibaba.ageiport.task.server.service;

import com.alibaba.ageiport.task.server.model.*;

/**
 * @author Administrator
 */
public interface TaskSpecificationService {

    CreateTaskSpecificationResponse save(CreateTaskSpecificationRequest request);

    GetTaskSpecificationResponse findByTaskCode(GetTaskSpecificationRequest request);

}
