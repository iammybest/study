package com.iammybest.springboot.quartz.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @DESCRIBE:
 * @TIME: 2020/11/10 15:37
 * @AUTHOR: qinghai.deng
 **/
@Slf4j
public class TestTimer extends QuartzJobBean {

    private boolean isExecuting =false;
    /**
     * 定时任务逻辑实现方法
     * 每当触发器触发时会执行该方法逻辑
     *
     * @param jobExecutionContext 任务执行上下文
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if(isExecuting){
            log.info("分布式节点执行中，任务时间：{}", new Date());
        }else{
            isExecuting=true;
            log.info("分布式节点开始执行业务，任务时间：{}", new Date());
            for (int i=0;i<20;i++){
                try {
                    log.info("任务执行中：{}……",i);
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isExecuting=false;
        }
    }
}