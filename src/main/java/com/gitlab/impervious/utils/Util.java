package com.gitlab.impervious.utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.awt.*;
import java.time.Instant;

public class Util {

    @SuppressWarnings("unused")
    public static void deleteMessage(MessageChannel messge) {
        try {
            messge.deleteMessageById(messge.getLatestMessageId());
        } catch(Exception ignored) {}

    }

    public static void botLog(Message msg) {
        Channels.LOG.getChannel().sendMessage(new EmbedBuilder()
                .setColor(Color.CYAN)
                .setAuthor(msg.getAuthor().getName() + "#" + msg.getAuthor().getDiscriminator(), null, msg.getAuthor().getEffectiveAvatarUrl())
                .setDescription(msg.getContentDisplay())
                .setFooter(msg.getGuild().getName() + "/#" + msg.getTextChannel().getName(), msg.getGuild().getIconUrl())
                .setTimestamp(Instant.now())
                .build())
                .queue();
    }
}
