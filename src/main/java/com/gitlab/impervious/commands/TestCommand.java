package com.gitlab.impervious.commands;

import com.gitlab.impervious.utils.Util;

import com.gitlab.impervious.utils.WeatherManager;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
        this.help = "just a test :)";
    }

    private WeatherManager wM = new WeatherManager();

    @Override
    protected void execute(CommandEvent commandEvent) {
        Util.notifyTest("Current Forecast",  "Current Temp: " + wM.getTemperature());

        commandEvent.getMessage().delete().queue();
    }
}