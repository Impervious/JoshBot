package com.gitlab.impervious.commands;

import com.gitlab.impervious.utils.Util;
import com.gitlab.impervious.weather.ForecastMain;

import com.google.gson.Gson;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import lombok.SneakyThrows;

import org.apache.commons.text.WordUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherCommand extends Command {

    public WeatherCommand() {
        this.name = "weather";
        this.aliases = new String[]{"wea"};
    }

    @SneakyThrows
    protected void execute(CommandEvent event) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println(date.format(dateFormatter));
        Gson gson = new Gson();

        ForecastMain forecast = new ForecastMain();
        forecast = gson.fromJson(forecast.jsonURL, ForecastMain.class);

        Util.notifyWeather("Current Forecast:" + "\n"
                        + WordUtils.capitalize(forecast.getCurrent().getWeather().get(0).getDescription()),
                "Currently it's " + Math.round(forecast.getCurrent().getTemp()) + "°C and feels like " + Math.round(forecast.getCurrent().getFeelsLike()) + "°C" + "\n"
                        + "Wind is blowing at " + Math.round(forecast.getCurrent().getWindSpeed() * 3.6) + " kph " + Util.headingToDirection(Float.valueOf(forecast.getCurrent().getWindDeg())),
                "https://openweathermap.org/img/wn/" + forecast.getCurrent().getWeather().get(0).getIcon() + ".png");
    }
}