package com.gitlab.impervious.commands;

import com.gitlab.impervious.jobs.TestJob;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;

public class TestCommand extends Command {

    private SchedulerFactory factory = new StdSchedulerFactory();
    private Scheduler sched;

    {
        try {
            sched = factory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public TestCommand() {
        this.name = "test";
        this.help = "just a test :)";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        commandEvent.getMessage().delete().queue();
        commandEvent.reply("hello");

        /*try {
            sched.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        JobDetail job = newJob(TestJob.class)
                .withIdentity("job", "group1")
                .build();

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 20 16 * * ?"))
                .build();
        try {
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }*/
    }
}
