package com.gitlab.impervious.jobs;

import com.gitlab.impervious.utils.Util;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.awt.*;
import java.util.Calendar;

public class JobPaymentReminders implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Color spotifyColor = new Color(30, 215, 96);
        Color amazonColor = new Color(246, 155, 5);

        Calendar cal = Calendar.getInstance();
        int monthDay = cal.get(Calendar.DAY_OF_MONTH);

        /*if(monthDay == 2) {
            Util.notifyPayment("Amazon Prime auto-renewal is today.", amazonColor);
        } else if(monthDay == 7) {
            Util.notifyPayment("Spotify Premium auto-renewal is today.", spotifyColor);
        }*/
    }
}
