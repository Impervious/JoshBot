package com.gitlab.impervious.commands;

import com.gitlab.impervious.utils.Util;
import com.gitlab.impervious.weather.forecast.ForecastMain;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import lombok.SneakyThrows;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
    }

    @SneakyThrows
    @Override
    protected void execute(CommandEvent event) {
        Gson gson = new Gson();

        ForecastMain forecast = new ForecastMain();
        forecast = gson.fromJson(forecast.jsonURL, ForecastMain.class);

        ZoneId zoneId = ZoneId.of("America/New_York");
        //String instant = Instant.ofEpochSecond(0L, Long.parseLong(forecast.getDaily().getDt().toString())).atZone(ZoneId.of(zoneId.getId())).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(zoneId));

        Util.notifyWeather("Test", forecast.getDaily().toString());
    }

}