package com.gitlab.impervious.commands;

import com.gitlab.impervious.FIABot;
import com.gitlab.impervious.utils.Channels;
import com.gitlab.impervious.utils.Util;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.time.Instant;
import java.util.Calendar;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
        this.aliases = new String[]{"remind"};
    }

    @SneakyThrows
    protected void execute( CommandEvent event) {

        User venohm = FIABot.getLNRGuild().getJDA().getUserById(289669713787748352L);
        User acct4Loss = FIABot.getLNRGuild().getJDA().getUserById(309112716948275200L);
        User xEntric = FIABot.getLNRGuild().getJDA().getUserById(272790899757678593L);
        User impervious = FIABot.getLNRGuild().getJDA().getUserById(73463573900173312L);

        Calendar cal = Calendar.getInstance();
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);

        event.getMessage().delete().queue();

        if(venohm != null && acct4Loss != null && xEntric != null && impervious != null) {
            if (weekDay == 3) {
                //Util.sendMessage(Channels.NOTIFICATIONS.getChannel(), xEntric.getAsTag() + " " + impervious.getAsTag());
                Util.sendEmbed("FIA Overlord", "https://docs.google.com/spreadsheets/d/1RapkZM9ajQ9fFggAvPP4RThHGYitNSkBNed474wwKRU/edit?usp=sharing", "https://i.imgur.com/ph47q0g.jpg", "test <:PepeHappy:772354625776386078>", Channels.BOTTING.getLNRChannel().getName());
            } else if (weekDay == 6) {
                Util.sendMessage(Channels.BOTTING.getLNRChannel(), impervious.getName() + " " + impervious.getAsMention());
                Util.sendEmbed("FIA Overlord", "https://docs.google.com/spreadsheets/d/1RapkZM9ajQ9fFggAvPP4RThHGYitNSkBNed474wwKRU/edit?usp=sharing", "https://i.imgur.com/ph47q0g.jpg", "test <:PepeHappy:772354625776386078>", Channels.BOTTING.getLNRChannel().getName());
            } else {
                //Util.sendMessage(Channels.PROFIADISCUSSION.getLNRChannel(), impervious.getName() + " " + impervious.getName());
                Util.sendEmbed("FIA Overlord", "https://docs.google.com/spreadsheets/d/1RapkZM9ajQ9fFggAvPP4RThHGYitNSkBNed474wwKRU/edit?usp=sharing", "https://i.imgur.com/ph47q0g.jpg", "e", Channels.BOTTING.getLNRChannel().getName());
            }
        } else {
            Util.sendMessage(event.getTextChannel(), "nice try, nerd.");
        }

        /*if(event.getGuild() == FIABot.getHomeGuild()) {
            User user = event.getGuild().getJDA().getUserById(73463573900173312L);

            Calendar cal = Calendar.getInstance();
            int weekDay = cal.get(Calendar.DAY_OF_WEEK);

            if (user != null) {
                if (weekDay == 3) {
                    System.out.println(weekDay);
                    //Util.sendMessage(Channels.DEVFIADISCUSSION.getLNRChannel(), user.getAsMention() + " " + user.getAsMention());
                    Util.sendEmbed("FIA Overlord", "https://docs.google.com/spreadsheets/d/1RapkZM9ajQ9fFggAvPP4RThHGYitNSkBNed474wwKRU/edit?usp=sharing", "https://i.imgur.com/ph47q0g.jpg", "Reminder to submit your verdicts if you haven't already ", Channels.NOTIFICATIONS.getChannel().getName());
                } else if (weekDay == 6) {
                    System.out.println(weekDay);
                    Util.sendMessage(Channels.TESTROOM.getChannel(), user.getAsMention() + " " + user.getAsMention());
                    Util.sendEmbed("FIA Overlord", "https://docs.google.com/spreadsheets/d/1RapkZM9ajQ9fFggAvPP4RThHGYitNSkBNed474wwKRU/edit?usp=sharing", "https://i.imgur.com/ph47q0g.jpg", "Reminder to submit your verdicts if you haven't already ", Channels.NOTIFICATIONS.getChannel().getName());
                } else {
                    System.out.println("it's " + weekDay);
                }
            }

            //Util.sendEmbed("FIA Overlord", "https://docs.google.com/spreadsheets/d/1RapkZM9ajQ9fFggAvPP4RThHGYitNSkBNed474wwKRU/edit?usp=sharing", "https://i.imgur.com/ph47q0g.jpg", "Reminder to submit your verdicts if you haven't already ", Channels.NOTIFICATIONS.getChannel().getName());
        }*/

        /*EmbedBuilder gameDeals = new EmbedBuilder()
                .setTitle("Game Deals")
                .addField("rtehrehwerherherherherhreherhershbrtmrherhretjehgrtehrehwerherherherherhreherhershbrtmrherhretjehgrtehrehwerherherherherhreherhershbrtmrherhretjehgrtehrehwerherherherherhreherhershbrtmrherhretjehgrtehrehwerherherherherhreherhershbrtmrherhretjehgsdsdsdsds21w", "", false)
                .setColor(new Color(20, 61, 113))
                .setTimestamp(Instant.now());

        Channels.NOTIFICATIONS.getChannel().sendMessage(gameDeals.build()).queue();*/
    }
}