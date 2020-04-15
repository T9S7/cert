package com.aszz.cert.admin.dao;

import com.aszz.cert.admin.quartz.TaskEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskDao {
    /**
     * @return
     * @// TODO: 查询任务
     */
    List<TaskEntity> findTaskList(TaskEntity taskEntity);

    TaskEntity findByTaskName(@Param("jobName") String jobName);
}
