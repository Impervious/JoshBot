package com.gitlab.impervious;

import com.gitlab.impervious.commands.TestCommand;

import com.gitlab.impervious.events.BotMention;
import com.gitlab.impervious.events.MessageEvent;
import com.gitlab.impervious.utils.BotConfig;

import com.jagrosh.jdautilities.command.CommandClientBuilder;

import net.dv8tion.jda.client.JDAClient;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;

import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import javax.security.auth.login.LoginException;

import java.io.IOException;

public class Main {

    private static YamlFile yamlFile;
    private static BotConfig botConfig;
    private static Main instance;
    private static JDA jda;
    private static JDAClient client;
    public static Guild guild;

    private static final BotMention botMention = new BotMention();
    private static final MessageEvent messageEvent = new MessageEvent();

    public static void main(String[] args) throws IOException, LoginException {
        new Main();
    }

    private Main() throws IOException, LoginException {
        yamlFile = new YamlFile("settings.yaml");

        if(!yamlFile.exists()) {
            yamlFile.createNewFile(true);
        }

        try {
            yamlFile.load();
            loadConfig();
        } catch(InvalidConfigurationException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        CommandClientBuilder commandBuilder = new CommandClientBuilder();
        commandBuilder.setGame(Game.watching("ur mum"));
        commandBuilder.setOwnerId("73463573900173312");
        commandBuilder.setPrefix("!");
        commandBuilder.addCommand(new TestCommand());

        JDABuilder builder = new JDABuilder(AccountType.BOT)
                .setToken(botConfig.getBotToken())
                .addEventListener(messageEvent, botMention, commandBuilder.build());
        jda = builder.build();
    }

    public static Main getInstance() {
        return instance;
    }

    public static JDAClient getClient() {
        return client;
    }

    public static Guild getGuild() {
        return jda.getGuildById("247394948331077632");
    }

    private static void loadConfig() throws IOException, InvalidConfigurationException {
        yamlFile.save();
        yamlFile.load();

        String token = getAndSet(yamlFile);

        yamlFile.save();
        yamlFile.load();

        botConfig = new BotConfig(token);
    }

    private static String getAndSet(YamlFile file) {
        if(!file.isSet("token")) {
            file.set("token", "");
        }
        return file.getString("token");
    }
}