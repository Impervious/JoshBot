package com.gitlab.impervious.utils;

import com.gitlab.impervious.JoshBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.*;
import java.util.Hashtable;
import java.util.Optional;

public class Util {

    private static File botPath;

    static {
        try {
            File jarPath = new File(JoshBot.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
            botPath = new File(jarPath, "data");
            botPath.mkdir();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static Optional<String> getBotToken() {
        try {
            File tokenFile = new File(botPath, "settings.yaml");
            if(tokenFile.exists()) {
                String token = FileUtils.readFileToString(tokenFile, (String) null);
                if(!token.equalsIgnoreCase("TOKEN") && !token.isEmpty()) {
                    return Optional.of(token);
                } else {
                    return Optional.empty();
                }
            } else {
                FileUtils.writeStringToFile(tokenFile, "TOKEN", (String) null);
                return Optional.empty();
            }
        } catch(IOException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    public static String headingToDirection(Float heading) {
        String strHeading = "?";
        Hashtable<String, Float> cardinal = new Hashtable<>();
        cardinal.put("North_1", (float) 0);
        cardinal.put("Northeast", 45f);
        cardinal.put("East", 90f);
        cardinal.put("Southeast", 135f);
        cardinal.put("South", 180f);
        cardinal.put("Southwest", 225f);
        cardinal.put("West", 270f);
        cardinal.put("Northwest", 315f);
        cardinal.put("North_2", 360f);

        for (String key: cardinal.keySet()) {
            Float value = cardinal.get(key);
            if (Math.abs(heading - value) < 30) {
                strHeading = key;
                if (key.contains("North_")) {
                    strHeading = "North";
                }
                break;
            }
        }
        return strHeading;
    }

    /*
     *  NOTIFICATIONS
     */

    public static void sendMessage(MessageChannel channel, String message) {
        try {
            channel.sendMessage(message).queue();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void notifyPayment(String msg, Color color) {
        Channels.NOTIFICATIONS.getChannel().sendMessage(new EmbedBuilder()
                .setColor(color)
                .setAuthor("JoshBot" + "#" + "9856", "http://google.com", "https://i.imgur.com/YsZxQjO.jpg")
                .setDescription(msg)
                .setFooter("The Hoob" + "/#" + "notifications", null)
                .setTimestamp(Instant.now())
                .build()).queue();
    }

    public static void notifyWeather(String title, String msg, String iconUrl) {
        Channels.NOTIFICATIONS.getChannel().sendMessage(new EmbedBuilder()
                .setColor(Color.YELLOW)
                .setAuthor("JoshBot" + "#" + "9856", "http://google.com", iconUrl)
                .setTitle(title)
                .setDescription(msg)
                .setFooter("The Hoob" + "/#" + "notifications", null)
                .setTimestamp(Instant.now())
                .build()).queue();
    }

    public static void errorLog(Message message, Exception e) {
        e.printStackTrace();
        EmbedBuilder bld = new EmbedBuilder();

        StringBuilder stack = new StringBuilder();
        for(StackTraceElement s : e.getStackTrace()) {
            stack.append(s.toString());
            stack.append("\n");
        }

        String stackString = stack.toString();
        if(stackString.length() > 1024) {
            stackString = stackString.substring(0, 1024);
        }

        Channels.ERRORS.getChannel().sendMessage(bld
                .setColor(Color.RED)
                .setTimestamp(Instant.now())
                .setAuthor(message.getAuthor().getName() + "#" + message.getAuthor().getDiscriminator(), null, message.getAuthor().getEffectiveAvatarUrl())
                .setDescription(message.getContentRaw())
                .addField("\u200B", "\u200B", false)
                .addField("Exception:", e.toString(), false)
                .addField("Stack:", stackString, false)
                .build()).queue();
    }

    public static void errorLog(String msg, Exception e) {
        e.printStackTrace();
        EmbedBuilder bld = new EmbedBuilder();

        StringBuilder stack = new StringBuilder();
        for(StackTraceElement s : e.getStackTrace()) {
            stack.append(s.toString());
            stack.append("\n");
        }

        String stackString = stack.toString();
        if(stackString.length() > 1024) {
            stackString = stackString.substring(0, 1024);
        }

        Channels.ERRORS.getChannel().sendMessage(bld
                .setColor(Color.RED)
                .setTimestamp(Instant.now())
                .setAuthor("JoshBot" + "#" + "9856", "http://google.com", "https://i.imgur.com/YsZxQjO.jpg")
                .setDescription(msg)
                .addField("\u200B", "\u200B", false)
                .addField("Exception:", e.toString(), false)
                .addField("Stack:", stackString, false)
                .build()).queue();
    }

    public static void errorLog(Exception e) {
        e.printStackTrace();
        EmbedBuilder bld = new EmbedBuilder();

        StringBuilder stack = new StringBuilder();
        for(StackTraceElement s : e.getStackTrace()) {
            stack.append(s.toString());
            stack.append("\n");
        }

        String stackString = stack.toString();
        if(stackString.length() > 1024) {
            stackString = stackString.substring(0, 1024);
        }

        Channels.ERRORS.getChannel().sendMessage(bld
                .setColor(Color.RED)
                .setTimestamp(Instant.now())
                .setAuthor("JoshBot" + "#" + "9856", "http://google.com", "https://i.imgur.com/YsZxQjO.jpg")
                .addField("\u200B", "\u200B", false)
                .addField("Exception:", e.toString(), false)
                .addField("Stack:", stackString, false)
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