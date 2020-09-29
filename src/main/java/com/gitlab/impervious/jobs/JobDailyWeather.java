package com.gitlab.impervious.jobs;

import com.gitlab.impervious.utils.Util;
import com.gitlab.impervious.weather.ForecastMain;

import com.google.gson.Gson;

import lombok.SneakyThrows;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class JobDailyWeather implements Job {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Gson gson = new Gson();

        ForecastMain forecast = new ForecastMain();
        forecast = gson.fromJson(forecast.jsonURL, ForecastMain.class);

        Util.notifyWeather("Daily Forecast",
                "It is " + Math.round(forecast.getCurrent().getTemp()) + "°C and feels like " + Math.round(forecast.getCurrent().getFeelsLike()) + "°C" + "\n"
                        + "Wind is blowing at " + Math.round(forecast.getCurrent().getWindSpeed() * 3.6) + " kph " + Util.headingToDirection(Float.valueOf(forecast.getCurrent().getWindDeg())),
                "https://openweathermap.org/img/wn/" + forecast.getCurrent().getWeather().get(0).getIcon() + ".png");
    }
}
