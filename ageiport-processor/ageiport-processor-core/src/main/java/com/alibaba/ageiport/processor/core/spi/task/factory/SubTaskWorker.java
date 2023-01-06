package com.alibaba.ageiport.processor.core.spi.task.factory;

import com.alibaba.ageiport.processor.core.Context;
import com.alibaba.ageiport.processor.core.constants.TaskStatus;
import com.alibaba.ageiport.processor.core.model.core.impl.MainTask;
import com.alibaba.ageiport.processor.core.model.core.impl.SubTask;

import java.util.Date;

/**
 * @author lingyi
 */
public interface SubTaskWorker extends Runnable, Context {
    SubTask getSubTask();

    void doMappingProcess();

    @Override
    default void run() {
        doMappingProcess();
    }


    default void onFinished(TaskContext context,SubTask subTask) {
        SubTask contextSubTask = context.getSubTask();
        contextSubTask.setStatus(TaskStatus.FINISHED);
        contextSubTask.setGmtFinished(new Date());
        contextSubTask.setDataTotalCount(subTask.getDataTotalCount());
        contextSubTask.setDataSuccessCount(subTask.getDataSuccessCount());
        context.save();
    }
}
