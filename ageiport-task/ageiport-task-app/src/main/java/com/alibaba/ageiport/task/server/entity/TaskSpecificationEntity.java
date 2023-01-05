package com.alibaba.ageiport.task.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lingyi
 */
@Getter
@Setter
@Entity
@Table(name = "agei_task_specification",
        indexes = {
                @Index(name = "tenant_namespace_app_env_task_code", columnList = "tenant,namespace,app,env,taskCode")
        }
)
@TableName(value = "agei_task_specification")
public class TaskSpecificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id")
    public Long id;

    /**
     * 租户
     */
    @Column(length = 64, updatable = false, nullable = false)
    @TableField(value = "tenant")
    private String tenant;

    /**
     * 命名空间
     */
    @Column(length = 64, updatable = false, nullable = false)
    @TableField(value = "namespace")
    private String namespace;

    /**
     * 应用
     */
    @Column(length = 64, updatable = false, nullable = false)
    @TableField(value = "app")
    private String app;

    /**
     * 环境
     */
    @Column(length = 64, updatable = false, nullable = false)
    @TableField(value = "env")
    private String env;

    /**
     * 任务编码
     */
    @Column(length = 64, updatable = false, nullable = false)
    @TableField(value = "task_code")
    private String taskCode;

    /**
     * 任务名称
     */
    @Column(length = 64, nullable = false)
    @TableField(value = "task_name")
    private String taskName;

    /**
     * 任务描述
     */
    @Column(length = 128, nullable = false)
    @TableField(value = "task_desc")
    private String taskDesc;

    /**
     * 任务类型
     */
    @Column(length = 64, nullable = false)
    @TableField(value = "task_type")
    private String taskType;

    /**
     * 执行类型
     */
    @Column(length = 64, nullable = false)
    @TableField(value = "task_execute_type")
    private String taskExecuteType;

    /**
     * 任务处理器
     */
    @Column(length = 512, nullable = false)
    @TableField(value = "task_handler")
    private String taskHandler;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    @TableField(value = "gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @Column(nullable = false)
    @TableField(value = "gmt_modified")
    private Date gmtModified;

    /**
     * 创建人ID
     */
    @Column(length = 64)
    @TableField(value = "creator_id")
    private String creatorId;

    /**
     * 创建人姓名
     */
    @Column(length = 64)
    @TableField(value = "creator_name")
    private String creatorName;

    /**
     * 修改人id
     */
    @Column(length = 64)
    @TableField(value = "modifier_id")
    private String modifierId;

    /**
     * 修改人姓名
     */
    @Column(length = 64)
    @TableField(value = "modifier_name")
    private String modifierName;

    /**
     * 拥有者id
     */
    @Column(length = 64)
    @TableField(value = "owner_id")
    private String ownerId;

    /**
     * 拥有者姓名
     */
    @Column(length = 64)
    @TableField(value = "owner_name")
    private String ownerName;

    /**
     * 状态
     */
    @Column(length = 64, nullable = false)
    @TableField(value = "status")
    private String status;

    /**
     * rowStatus
     */
    @Column(length = 64, nullable = false)
    @TableField(value = "row_status")
    private String rowStatus;

    /**
     * rowVersion
     */
    @Column(nullable = false)
    @TableField(value = "row_version")
    private Integer rowVersion;

    /**
     * feature
     */
    @Column(columnDefinition = "text default null")
    @TableField(value = "feature")
    private String feature;
}