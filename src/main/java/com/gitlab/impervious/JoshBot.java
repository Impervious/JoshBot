package com.gitlab.impervious;

import com.gitlab.impervious.commands.TestCommand;
import com.gitlab.impervious.jobs.JobDailyWeather;
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

    private JoshBot() throws LoginException {
        JoshBot instance = this;

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
        client.addCommand(new TestCommand());

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

        JobDetail jobPaymentReminders = newJob(JobPaymentReminders.class)
                .withIdentity("jobPay", "group1")
                .build();

        JobDetail jobDailyWeather = newJob(JobDailyWeather.class)
                .withIdentity("jobDailyWeather", "group1")
                .build();

        /*
         *  TRIGGERS
         */

        CronTrigger triggerPaymentReminders = TriggerBuilder.newTrigger()
                .withIdentity("triggerpay", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 0 12 2,7 * ?")) // FIRES AT 12PM ON THE 2ND AND 7TH OF EVERY MONTH
                .build();

        CronTrigger triggerDailyWeather = TriggerBuilder.newTrigger()
                .withIdentity("triggerDailyWeather", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 0 11 ? * *")) // FIRES EVERYDAY AT 11AM
                .build();
        try {
            if (sched != null) {
                sched.scheduleJob(jobPaymentReminders, triggerPaymentReminders);
                sched.scheduleJob(jobDailyWeather, triggerDailyWeather);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static Guild getGuild() {
        return jda.getGuildById("247394948331077632");
    }
}