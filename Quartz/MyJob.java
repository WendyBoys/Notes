package com.xuan.Quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(System.currentTimeMillis());
        String tiggerdata = jobExecutionContext.getTrigger().getJobDataMap().getString("tiggerdata");
        System.out.println(tiggerdata);
        Map<String, Object> wrappedMap = jobExecutionContext.getJobDetail().getJobDataMap().getWrappedMap();
        System.out.println(wrappedMap.get("key")+"     \r\n   "+wrappedMap.get("data"));
    }
}
