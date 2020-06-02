package com.xuan.Quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.xml.validation.SchemaFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MyTigger {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        HashMap hashMap=new HashMap();
        hashMap.put("key","value");
        hashMap.put("data","xiaoxiaoxuan");
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() + 2000);
        Date endDate = new Date();
        endDate.setTime(startDate.getTime() + 11000);

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("test", "Demo")
                .usingJobData(new JobDataMap(hashMap))
                .build();
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group")
                .usingJobData("tiggerdata","gggggg")
                .startAt(startDate)              //startAt startNow 修改的是同一变量 所以以最后一个设置为准
                .startNow()
                .withSchedule
                (SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();


     /*   CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group1")
                .usingJobData("tiggerdata","gggggg")
                .startNow()
                .startAt(startDate)    //cron   如果endAt 没到cron时间 会报错 因为永远执行不了
                .withSchedule(CronScheduleBuilder.cronSchedule("0 14 * * * ? *"))
                .build();*/

        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
        System.out.println("start...");
      /*  TimeUnit.SECONDS.sleep(15);
        scheduler.shutdown();*/
        System.out.println("shutdown...");

    }
}
