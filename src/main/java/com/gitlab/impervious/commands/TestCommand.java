package com.gitlab.impervious.commands;

import com.gitlab.impervious.JoshBot;
import com.gitlab.impervious.covid.COVIDMain;
import com.gitlab.impervious.utils.Channels;
import com.gitlab.impervious.utils.Util;
import com.gitlab.impervious.weather.ForecastMain;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.google.gson.Gson;

import org.apache.commons.text.WordUtils;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import lombok.SneakyThrows;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
    }

    @SneakyThrows
    protected void execute(CommandEvent event) {
        Gson gson = new Gson();

        ForecastMain forecast = new ForecastMain();
        forecast = gson.fromJson(forecast.jsonURL, ForecastMain.class);

        COVIDMain covid = new COVIDMain();
        covid = gson.fromJson(covid.jsonURL, COVIDMain.class);

        try {
            Util.notifyWeather("Daily Forecast:" + "\n"
                            + WordUtils.capitalize(forecast.getDaily().get(0).getWeather().get(0).getDescription()),
                    "It will be " + Math.round(forecast.getDaily().get(0).getTemp().getDay()) + "°C and feel like " + Math.round(forecast.getDaily().get(0).getFeelsLike().getDay()) + "°C" + "\n"
                            + "Wind will blow " + Util.headingToDirection(Float.valueOf(forecast.getDaily().get(0).getWindDeg())) + " at " + Math.round(forecast.getDaily().get(0).getWindSpeed() * 3.6) + " kph" + "\n"
                            + covid.getData().get(0).getConfirmed() + " total COVID-19 cases",
                    "https://openweathermap.org/img/wn/" + forecast.getDaily().get(0).getWeather().get(0).getIcon() + ".png");
        } catch(Exception e) {
            Util.sendMessage(Channels.NOTIFICATIONS.getChannel(), "An error occured. Head on over to " + Channels.ERRORS.getChannel().getAsMention() + " for more info.");
            Util.errorLog("**OH NO AN ERROR**" ,e);
        }
    }
}