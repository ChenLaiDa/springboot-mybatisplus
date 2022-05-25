package com.example.quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/11/13 15:16
 */
public class HelloQuartz implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail detail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = detail.getJobDataMap();



        String name = detail.getJobDataMap().getString("name");


        SimpleDateFormat format = new SimpleDateFormat("YYYY年MM月dd日");
        Date date = new Date();
        String dateStr = format.format(date);
        System.out.println("say hello to " + name + " at " + dateStr);

    }
}
