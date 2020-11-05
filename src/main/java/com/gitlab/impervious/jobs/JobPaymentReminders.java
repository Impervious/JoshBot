package com.gitlab.impervious.jobs;

import com.gitlab.impervious.utils.Util;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class JobPaymentReminders implements Job {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final String todaysDate = dateFormat.format(new Date());

    private static final String[] insurancePaymentDates = {
            "26/10/2020",   //OCTOBER 27 2020 <-- ACTUAL WITHDRAWL DATE
            "26/11/2020",   //NOVEMEBER 27 2020 <-- ACTUAL WITHDRAWL DATE
            "28/12/2020",   //DECEMBER 29 2020 <-- ACTUAL WITHDRAWL DATE
            "26/01/2021",   //JANUARY 27 2021 <-- ACTUAL WITHDRAWL DATE
            "28/02/2021",   //MARCH 01 2021 <-- ACTUAL WITHDRAWL DATE
            "28/03/2021",   //MARCH 29 2021 <-- ACTUAL WITHDRAWL DATE
            "26/04/2021",   //APRIL 27 2021 <-- ACTUAL WITHDRAWL DATE
            "26/05/2021",   //MAY 27 2021 <-- ACTUAL WITHDRAWL DATE
            "27/06/2021",   //JUNE 28 2021 <-- ACTUAL WITHDRAWL DATE
    };

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        Color insuranceColor = new Color(0, 79, 182);
        Color spotifyColor = new Color(30, 215, 96);
        Color amazonColor = new Color(246, 155, 5);

        Calendar cal = Calendar.getInstance();
        int monthDay = cal.get(Calendar.DAY_OF_MONTH);

        boolean containsDate = Arrays.asList(insurancePaymentDates).contains(todaysDate);

        if(containsDate) {
            Util.notifyPayment("Car insurance payment tomorrow", insuranceColor);
        }

        if(monthDay == 2) {
            Util.notifyPayment("Amazon Prime auto-renewal is today.", amazonColor);
        } else if(monthDay == 7) {
            Util.notifyPayment("Spotify Premium auto-renewal is today.", spotifyColor);
        }
    }
}