package com.gitlab.impervious;

import com.gitlab.impervious.commands.TestCommand;
import com.gitlab.impervious.commands.WeatherCommand;
import com.gitlab.impervious.jobs.JobDailyUpdates;
import com.gitlab.impervious.jobs.JobPaymentReminders;
import com.gitlab.impervious.utils.Util;

import java.util.Optional;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;

import com.jagrosh.jdautilities.command.CommandClientBuilder;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.security.auth.login.LoginException;

public class JoshBot {

    private static JDA jda;

    private final SchedulerFactory factory = new StdSchedulerFactory();
    private Scheduler sched;

    {
        try {
            sched = factory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws LoginException {
        new JoshBot();
    }

    public JoshBot() throws LoginException {
        Optional<String> token = Util.getBotToken();
        if (token.isEmpty()) {
            System.out.println("Add your token to settings.yaml");
            System.out.println("Shutting down...");
            System.exit(0);
            return;
        }

        CommandClientBuilder client = new CommandClientBuilder();
        client.useDefaultGame();
        client.setOwnerId("73463573900173312");
        client.setPrefix("!");
        client.addCommands(new TestCommand(), new WeatherCommand());

        jda = JDABuilder.createDefault(token.get())
                .addEventListeners(client.build())
                .build();

        try {
            if (sched != null) {
                sched.start();
            }
        } catch (SchedulerException e) {
            Util.errorLog(e.getMessage(), e);
        }

        /*
         *  JOBS
         */

        JobDetail dailyUpdateJob = newJob(JobDailyUpdates.class)
                .withIdentity("jobDailyWeather", "group1")
                .build();

        JobDetail paymentReminderJob = newJob(JobPaymentReminders.class)
                .withIdentity("jobPay", "group1")
                .build();

        /*
         *  TRIGGERS
         */

        CronTrigger dailyUpdateTrigger = TriggerBuilder.newTrigger()
                .withIdentity("dailyUpdateTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 0 11 ? * * *")) // FIRES EVERYDAY AT 11AM
                .build();

        CronTrigger dailyPaymentTrigger = TriggerBuilder.newTrigger()
                .withIdentity("dailyPaymentTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule("2 0 11 ? * * *")) // FIRES EVERYDAY AT 11AM + 2 SECONDS
                .build();
        try {
            if (sched != null) {
                sched.scheduleJob(dailyUpdateJob, dailyUpdateTrigger);
                sched.scheduleJob(paymentReminderJob, dailyPaymentTrigger);

            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static Guild getGuild() {
        return jda.getGuildById("247394948331077632");
    }
}