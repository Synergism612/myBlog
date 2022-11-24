package com.synergism.blog.quartz;

import com.synergism.blog.quartz.job.LikeQuartzJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    private static final String LIKE_QUARTZ_IDENTITY = "LikeQuartz";

    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(LikeQuartzJob.class).withIdentity(LIKE_QUARTZ_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)  //设置时间周期单位秒
//                .withIntervalInHours(2)  //两个小时执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(LIKE_QUARTZ_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }
}
