package com.gitlab.impervious.utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.awt.*;
import java.time.Instant;

public class Util {

    @SuppressWarnings("unused")
    public void deleteMessage(MessageChannel messge) {
        try {
            messge.deleteMessageById(messge.getLatestMessageId());
        } catch(Exception ignored) {}

    }

    public static void notifyPayment() {
        Channels.NOTIFICATIONS.getChannel().sendMessage(new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setAuthor("JoshBot" + "#" + "9856", "http://google.com", "https://i.imgur.com/YsZxQjO.jpg")
                .setDescription("You have an upcoming credit card payment")
                .setFooter("The Hoob" + "/#" + "notifications", null)
                .setTimestamp(Instant.now())
                .build()).queue();
    }

    public static void notify420() {

        Channels.NOTIFICATIONS.getChannel().sendMessage(new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setAuthor("JoshBot" + "#" + "9856", "http://google.com", "https://i.imgur.com/YsZxQjO.jpg")
                .setDescription("jublz")
                .setFooter("The Hoob" + "/#" + "notifications", null)
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