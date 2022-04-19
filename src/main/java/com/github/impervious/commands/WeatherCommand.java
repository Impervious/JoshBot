package com.github.impervious.commands;

import com.github.impervious.weather.ForecastMain;
import com.github.impervious.utils.Util;

import com.google.gson.Gson;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import lombok.SneakyThrows;

public class WeatherCommand extends Command {

    public WeatherCommand() {
        this.name = "weather";
        this.aliases = new String[]{"wea"};
    }

    @SneakyThrows
    protected void execute(CommandEvent event) {
        Gson gson = new Gson();

        ForecastMain forecast = new ForecastMain();
        forecast = gson.fromJson(forecast.jsonURL, ForecastMain.class);

        /*if((forecast.getAlerts() != null) && !forecast.getAlerts().isEmpty()) {
            Util.notifyWeather("Current Forecast:" + "\n"
                            + WordUtils.capitalize(forecast.getCurrent().getWeather().get(0).getDescription()),
                    "Currently it's " + Math.round(forecast.getCurrent().getTemp()) + "°C and feels like " + Math.round(forecast.getCurrent().getFeelsLike()) + "°C" + "\n"
                            + "Wind is blowing **" + Util.headingToDirection(Float.valueOf(forecast.getDaily().get(0).getWindDeg())) + " at " + Math.round(forecast.getDaily().get(0).getWindSpeed() * 3.6) + " kph**",
                    "https://openweathermap.org/img/wn/" + forecast.getCurrent().getWeather().get(0).getIcon() + ".png", Instant.now(), Color.YELLOW);

            Util.notifyWeather("Alert:", forecast.getAlerts().get(0).getDescription(), "https://openweathermap.org/img/wn/" + forecast.getCurrent().getWeather().get(0).getIcon() + ".png", Instant.ofEpochSecond(forecast.getAlerts().get(0).getEnd()), Color.RED);

        } else {
            Util.notifyWeather("Current Forecast:" + "\n"
                            + WordUtils.capitalize(forecast.getCurrent().getWeather().get(0).getDescription()),
                    "Currently it's " + Math.round(forecast.getCurrent().getTemp()) + "°C and feels like " + Math.round(forecast.getCurrent().getFeelsLike()) + "°C" + "\n"
                            + "Wind is blowing at **" + Util.headingToDirection(Float.valueOf(forecast.getDaily().get(0).getWindDeg())) + " at " + Math.round(forecast.getDaily().get(0).getWindSpeed() * 3.6) + " kph**",
                    "https://openweathermap.org/img/wn/" + forecast.getCurrent().getWeather().get(0).getIcon() + ".png", Instant.now(), Color.YELLOW);
        }*/
    }
}