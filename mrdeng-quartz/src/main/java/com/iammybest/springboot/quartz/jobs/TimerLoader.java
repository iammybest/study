package com.iammybest.springboot.quartz.jobs;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.UUID;

/**
 * @DESCRIBE:
 * @TIME: 2020/11/10 16:55
 * @AUTHOR: qinghai.deng
 **/
@Component
public class TimerLoader {
    @Resource
    private Scheduler scheduler;
    @PostConstruct
    private void load() throws SchedulerException {
//任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = TestTimer.class.getName();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(TestTimer.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
