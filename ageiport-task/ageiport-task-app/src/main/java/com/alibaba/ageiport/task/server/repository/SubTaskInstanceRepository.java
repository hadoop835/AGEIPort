package com.alibaba.ageiport.task.server.repository;

import com.alibaba.ageiport.task.server.entity.SubTaskInstanceEntity;
import com.alibaba.ageiport.task.server.repository.query.BaseQuery;
import com.alibaba.ageiport.task.server.repository.query.TenantAppQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lingyi
 */
@Repository
public interface SubTaskInstanceRepository extends BaseMapper<SubTaskInstanceEntity> {

}
