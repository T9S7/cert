package com.aszz.cert.admin.quartz;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TaskEntity implements Serializable {
    private Long id;
    private String jobName; //任务名称
    private String jobGroup; //任务分组
    private String jobStatus; //任务状态
    private String jobClass;//任务执行方法
    private String cronExpression; // cron 表达式
    private String jobDescription; //任务描述
    private String timeZoneId; // 时区
    private Long startTime;
    private Long endTime;
    private String state; //状态
    private Long userId;

}
