package com.gitlab.impervious;

import com.gitlab.impervious.events.BotMention;
import com.gitlab.impervious.events.MessageEvent;
import com.gitlab.impervious.jobs.Job420;
import com.gitlab.impervious.jobs.JobDailyWeather;
import com.gitlab.impervious.jobs.JobPaymentReminders;
import com.gitlab.impervious.utils.Util;

import java.util.Optional;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.managers.GuildManager;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.security.auth.login.LoginException;

public class Main {

    private static JDA jda;
    public static Guild guild;
    private static Main instance;

    private static final BotMention botMention = new BotMention();
    private static final MessageEvent messageEvent = new MessageEvent();

    //private WeatherManager wM = new WeatherManager();

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
        new Main();
    }

    private Main() throws LoginException {
        Optional<String> token = Util.getBotToken();
        if (token.isEmpty()) {
            System.out.println("Add your token to settings.yaml");
            System.out.println("Shutting down...");
            System.exit(0);
            return;
        }

        JDABuilder builder = JDABuilder.createDefault(token.get());
        builder.setActivity(Activity.watching("you."));
        builder.build();
        System.out.println("logged in");

        try {
            if (sched != null) {
                sched.start();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        /*
         *  JOBS
         */

        JobDetail job420 = newJob(Job420.class)
                .withIdentity("job420", "group1")
                .build();

        JobDetail jobPaymentReminders = newJob(JobPaymentReminders.class)
                .withIdentity("jobPay", "group1")
                .build();

        JobDetail jobDailyWeather = newJob(JobDailyWeather.class)
                .withIdentity("jobDailyWeather", "group1")
                .build();

        /*
         *  TRIGGERS
         */

        CronTrigger triggerPay = TriggerBuilder.newTrigger()
                .withIdentity("triggerpay", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 0 12 2,7 * ?")) // FIRES AT 12PM(NOON) ON THE 2ND AND 7TH OF EVERY MONTH
                .build();

        CronTrigger trigger420 = TriggerBuilder.newTrigger()
                .withIdentity("trigger420", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 20 16 * * ?")) // FIRES AT 4:20PM EVERYDAY
                .build();

        CronTrigger triggerDailyWeather = TriggerBuilder.newTrigger()
                .withIdentity("triggerDailyWeather", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 45 8 ? * *"))
                .build();
        try {
            if (sched != null) {
                sched.scheduleJob(job420, trigger420);
                sched.scheduleJob(jobPaymentReminders, triggerPay);
                sched.scheduleJob(jobDailyWeather, triggerDailyWeather);
                System.out.println("scheduler started");
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public static Guild getGuild() {
        return jda.getGuildById("247394948331077632");
    }
}