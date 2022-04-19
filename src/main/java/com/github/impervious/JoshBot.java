package com.github.impervious;

import com.github.impervious.commands.WeatherCommand;
import com.github.impervious.jobs.JobDailyUpdates;
import com.github.impervious.jobs.JobGameDeals;
import com.github.impervious.jobs.JobWeatherAlert;
import com.github.impervious.utils.Util;

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
        client.setActivity(null);
        client.setPrefix("$");
        client.setAlternativePrefix("!!");
        client.addCommand(new WeatherCommand());

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

        JobDetail gameDealsJob = newJob(JobGameDeals.class)
                .withIdentity("jobGameDeals", "group1")
                .build();

        //TO-DO
        /*JobDetail weatherAlertJob = newJob(JobWeatherAlert.class)
                .withIdentity("hourlyWeatherAlert", "group1")
                .build();*/

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

        try {
            if (sched != null) {
                sched.scheduleJob(dailyUpdateJob, dailyUpdateTrigger);
                sched.scheduleJob(gameDealsJob, gameDealsTrigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static Guild getGuild() {
        return jda.getGuildById("247394948331077632");
    }
}