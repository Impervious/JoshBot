package com.gitlab.impervious.jobs;

import com.gitlab.impervious.utils.Util;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobDailyWeather implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //Util.notifyTest("Daily Weather", "");
    }
}
