package com.gitlab.impervious;

import com.gitlab.impervious.commands.TestCommand;
import com.gitlab.impervious.commands.WeatherCommand;
import com.gitlab.impervious.jobs.*;
import com.gitlab.impervious.utils.Util;

import java.util.Optional;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;

import com.jagrosh.jdautilities.command.CommandClientBuilder;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;

import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.security.auth.login.LoginException;

public class FIABot {

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
        new FIABot();
    }

    public FIABot() throws LoginException {
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
        client.setActivity(null);
        client.setPrefix("$");
        client.setAlternativePrefix("!!");
        client.addCommands(new TestCommand(), new WeatherCommand());

        jda = JDABuilder.createDefault(token.get(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_EMOJIS)
                .addEventListeners(client.build())
                .setChunkingFilter(ChunkingFilter.ALL)
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

        JobDetail gameDealsJob = newJob(JobGameDeals.class)
                .withIdentity("jobGameDeals", "group1")
                .build();

        //TO-DO
        JobDetail weatherAlertJob = newJob(JobWeatherAlert.class)
                .withIdentity("hourlyWeatherAlert", "group1")
                .build();

        //LNR FIA Reminder Job
        JobDetail fiaReminder = newJob(JobFIAReminder.class)
                .withIdentity("jobFIAReminder", "group1")
                .build();

        /*
         *  TRIGGERS
         */

        CronTrigger dailyUpdateTrigger = TriggerBuilder.newTrigger()
                .withIdentity("dailyUpdateTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 0 11 ? * * *")) // FIRES EVERYDAY AT 11AM
                .build();

        CronTrigger gameDealsTrigger = TriggerBuilder.newTrigger()
                .withIdentity("gameDealsTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 0 16 ? * * *")) // FIRES EVERYDAY AT 4PM
                .build();

        CronTrigger dailyPaymentTrigger = TriggerBuilder.newTrigger()
                .withIdentity("dailyPaymentTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule("2 0 11 ? * * *")) // FIRES EVERYDAY AT 11AM + 2 SECONDS
                .build();

        //LNR FIA Remionder Trigger
        CronTrigger fiaReminderTrigger = TriggerBuilder.newTrigger()
                .withIdentity("fiaReminderTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 0 18 ? * TUE,FRI *")) // FIRES EVERY TUESDAY & FRIDAY(2 DAYS AFTER EACH TIERS RACE) AT 6PM EST
                .build();

        try {
            if (sched != null) {
                sched.scheduleJob(dailyUpdateJob, dailyUpdateTrigger);
                sched.scheduleJob(paymentReminderJob, dailyPaymentTrigger);
                sched.scheduleJob(gameDealsJob, gameDealsTrigger);
                //sched.scheduleJob(fiaReminder, fiaReminderTrigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /*public static Guild getHomeGuild() {
        return jda.getGuildById("247394948331077632");
    }*/

    public static Guild getLNRGuild() {
        return jda.getGuildById("739893045994061946");
    }
}