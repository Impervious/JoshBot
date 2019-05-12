package com.gitlab.impervious.utils;

import com.gitlab.impervious.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.time.Instant;

public class Util {

    private static Main main = Main.getInstance();
    private static JDA jda;
    private static Guild guild;
    private static User user;

    @SuppressWarnings("unused")
    public void deleteMessage(MessageChannel messge) {
        try {
            messge.deleteMessageById(messge.getLatestMessageId());
        } catch(Exception ignored) {}

    }

    public static void notify420(String msg) {

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