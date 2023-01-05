package com.alibaba.ageiport.task.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 主任务
 *
 * @author lingyi
 */
@Entity
@Table(name = "agei_main_task_instance",
        indexes = {
                @Index(name = "uk_mainTaskId", columnList = "mainTaskId", unique = true),
                @Index(name = "idx_main_task", columnList = "tenant,namespace,app,env,code"),
                @Index(name = "idx_bizUser", columnList = "bizUserTenant,bizUserOrg,bizUserId,bizUserKey"),
                @Index(name = "idx_gmt_create", columnList = "gmtCreate"),
                @Index(name = "idx_flowTaskId", columnList = "flowTaskId")
        }
)
@TableName(value = "agei_main_task_instance")
@Getter
@Setter
public class MainTaskInstanceEntity {

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
     * 业务任务名称
     */
    @Column(length = 64)
    @TableField(value = "biz_task_name")
    private String bizTaskName;

    /**
     * 业务Key
     */
    @Column(length = 64)
    @TableField(value = "biz_key")
    private String bizKey;

    /**
     * 业务用户查询参数
     */
    @Column(columnDefinition = "text default null")
    @TableField(value = "biz_query")
    private String bizQuery;

    /**
     * 业务用户ID
     */
    @Column(length = 64)
    @TableField(value = "biz_user_id")
    private String bizUserId;

    /**
     * 业务用户姓名
     */
    @Column(length = 64)
    @TableField(value = "biz_user_name")
    private String bizUserName;

    /**
     * 业务用户所属租户
     */
    @Column(length = 64)
    @TableField(value = "biz_user_tenant")
    private String bizUserTenant;

    /**
     * 业务用户所属组织
     */
    @Column(length = 64)
    @TableField(value = "biz_user_org")
    private String bizUserOrg;

    /**
     * 业务用户key
     */
    @Column(length = 64)
    @TableField(value = "biz_user_key")
    private String bizUserKey;

    /**
     * 业务用户自定义扩展属性
     */
    @Column(length = 512)
    @TableField(value = "biz_user_feature")
    private String bizUserFeature;

    /**
     * 任务组ID
     */
    @Column(length = 64)
    @TableField(value = "flow_task_id")
    private String flowTaskId;

    /**
     * 流程顺序
     */
    @TableField(value = "flow_order")
    private Integer flowOrder;

    /**
     * 任务ID
     */
    @Column(length = 64, updatable = false, nullable = false)
    @TableField(value = "main_task_id")
    private String mainTaskId;

    /**
     * 任务编码
     */
    @Column(length = 64, updatable = false, nullable = false)
    @TableField(value = "code")
    private String code;

    /**
     * 任务名称
     */
    @Column(length = 64)
    @TableField(value = "name")
    private String name;

    /**
     * 任务类型
     */
    @Column(length = 64, updatable = false, nullable = false)
    @TableField(value = "type")
    private String type;

    /**
     * 任务执行类型
     */
    @Column(length = 64, updatable = false, nullable = false)
    @TableField(value = "execute_type")
    private String executeType;

    /**
     * 子任务处理总量
     */
    @TableField(value = "sub_total_count")
    private Integer subTotalCount;

    /**
     * 子任务已处理量
     */
    @TableField(value = "sub_finished_count")
    private Integer subFinishedCount;

    /**
     * 子任务处理成功量
     */
    @TableField(value = "sub_success_count")
    private Integer subSuccessCount;

    /**
     * 子任务处理失败量
     */
    @TableField(value = "sub_failed_count")
    private Integer subFailedCount;

    /**
     * 数据处理总量
     */
    @TableField(value = "data_total_count")
    private Integer dataTotalCount;

    /**
     * 数据已处理量
     */
    @TableField(value = "data_processed_count")
    private Integer dataProcessedCount;

    /**
     * 数据处理成功量
     */
    @TableField(value = "data_success_count")
    private Integer dataSuccessCount;

    /**
     * 数据处理失败量
     */
    @TableField(value = "data_failed_count")
    private Integer dataFailedCount;

    /**
     * 任务状态
     */
    @Column(length = 64, nullable = false)
    @TableField(value = "status")
    private String status;

    /**
     * 任务执行节点
     */
    @Column(length = 64)
    @TableField(value = "host")
    private String host;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified")
    private Date gmtModified;

    /**
     * 任务开始时间
     */
    @TableField(value = "gmt_start")
    private Date gmtStart;

    /**
     * 任务开始分发时间
     */
    @TableField(value = "gmt_dispatch")
    private Date gmtDispatch;

    /**
     * 任务开始执行时间
     */
    @TableField(value = "gmt_execute")
    private Date gmtExecute;

    /**
     * 任务结束时间
     */
    @TableField(value = "gmt_finished")
    private Date gmtFinished;

    /**
     * 任务过期时间
     */
    @TableField(value = "gmt_expired")
    private Date gmtExpired;

    /**
     * Trace
     */
    @Column(length = 64)
    @TableField(value = "trace_id")
    private String traceId;

    /**
     * 重试次数
     */
    @TableField(value = "retry_times")
    private Integer retryTimes;

    /**
     * 错误Code
     */
    @Column(length = 64)
    @TableField(value = "result_code")
    private String resultCode;

    /**
     * 错误信息
     */
    @Column(length = 128)
    @TableField(value = "result_message")
    private String resultMessage;

    /**
     * rowStatus
     */
    @Column(length = 64)
    @TableField(value = "row_status")
    private String rowStatus;

    /**
     * rowVersion
     */
    @TableField(value = "row_version")
    private Integer rowVersion;

    /**
     * 任务日志
     */
    @Column(length = 4096)
    @TableField(value = "log")
    private String log;

    /**
     * 扩展字段，JSON格式
     */
    @Column(columnDefinition = "text default null")
    @TableField(value = "feature")
    private String feature;

    /**
     * 任务运行时参数
     */
    @Column(columnDefinition = "text default null")
    @TableField(value = "runtime_param")
    private String runtimeParam;
}