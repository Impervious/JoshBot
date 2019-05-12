package com.gitlab.impervious.utils;

import net.dv8tion.jda.client.entities.Application;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.time.Instant;

public class Util {
    private static JDA jda;
    private static Guild guild;
    private static User user;

    @SuppressWarnings("unused")
    public static void deleteMessage(MessageChannel messge) {
        try {
            messge.deleteMessageById(messge.getLatestMessageId());
        } catch(Exception ignored) {}

    }

    public static void notify420(Message msg) {

        Channels.NOTIFICATIONS.getChannel().sendMessage(new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setAuthor("JDATest" + "#" + "0593", null, user.getJDA().getSelfUser().getEffectiveAvatarUrl())
                .setDescription("jublz")
                //.setFooter(guild.getName() + "/#" + guild.getTextChannelById(Channels.NOTIFICATIONS.getId()), guild.getIconUrl())
                .setTimestamp(Instant.now())
                .build()).queue();
    }

    public static void botLog(Message msg) {
        Channels.LOG.getChannel().sendMessage(new EmbedBuilder()
                .setColor(Color.CYAN)
                .setAuthor(msg.getAuthor().getName() + "#" + msg.getAuthor().getDiscriminator(), null, msg.getAuthor().getEffectiveAvatarUrl())
                .setDescription(msg.getContentDisplay())
                .setFooter(msg.getGuild().getName() + "/#" + msg.getTextChannel().getName(), msg.getGuild().getIconUrl())
                .setTimestamp(Instant.now())
                .build()).queue();
    }
}
