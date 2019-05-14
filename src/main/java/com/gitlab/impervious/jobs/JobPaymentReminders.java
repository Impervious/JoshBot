package com.gitlab.impervious.jobs;

import com.gitlab.impervious.utils.Util;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class JobPaymentReminders implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Util.notifyPayment();
    }
}
