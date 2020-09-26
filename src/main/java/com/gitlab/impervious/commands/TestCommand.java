package com.gitlab.impervious.commands;

import com.gitlab.impervious.utils.Util;
import com.gitlab.impervious.weather.WeatherRoot;
import com.google.gson.*;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import lombok.SneakyThrows;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
    }

    @SneakyThrows
    @Override
    protected void execute(CommandEvent event) {

        Gson gson = new Gson();

        WeatherRoot weatherRoot = new WeatherRoot();
        weatherRoot = gson.fromJson(weatherRoot.jsonURL, WeatherRoot.class);
        Util.notifyWeather("Current Forecast", "It is " + Math.round(weatherRoot.getMain().getTemp()) + " in " + weatherRoot.getWind().getSpeed() * 3.6);
    }
}