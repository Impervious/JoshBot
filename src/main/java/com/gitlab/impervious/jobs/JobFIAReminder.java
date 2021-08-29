package com.gitlab.impervious.jobs;

import com.gitlab.impervious.FIABot;
import com.gitlab.impervious.utils.Channels;
import com.gitlab.impervious.utils.Util;

import net.dv8tion.jda.api.entities.User;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Calendar;

public class JobFIAReminder implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        User venohm = FIABot.getLNRGuild().getJDA().getUserById(289669713787748352L);
        User acct4Loss = FIABot.getLNRGuild().getJDA().getUserById(309112716948275200L);
        User xEntric = FIABot.getLNRGuild().getJDA().getUserById(272790899757678593L);
        User impervious = FIABot.getLNRGuild().getJDA().getUserById(73463573900173312L);

        Calendar cal = Calendar.getInstance();
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);

        if (venohm != null && acct4Loss != null && xEntric != null && impervious != null) {
            if (weekDay == 3) {
                Util.sendMessage(Channels.DEVFIADISCUSSION.getLNRChannel(), xEntric.getAsTag() + " " + impervious.getAsTag());
                Util.sendEmbed("FIA Overlord", "https://docs.google.com/spreadsheets/d/1RapkZM9ajQ9fFggAvPP4RThHGYitNSkBNed474wwKRU/edit?usp=sharing", "https://i.imgur.com/ph47q0g.jpg", "test <:PepeHappy:772354625776386078>", Channels.DEVFIADISCUSSION.getLNRChannel().getName());
            } else if (weekDay == 6) {
                Util.sendMessage(Channels.BOTTING.getLNRChannel(), venohm.getAsTag() + " " + acct4Loss.getAsTag());
                Util.sendEmbed("FIA Overlord", "https://docs.google.com/spreadsheets/d/1RapkZM9ajQ9fFggAvPP4RThHGYitNSkBNed474wwKRU/edit?usp=sharing", "https://i.imgur.com/ph47q0g.jpg", "test <:PepeHappy:772354625776386078>", Channels.BOTTING.getLNRChannel().getName());
            }
        }
    }
}