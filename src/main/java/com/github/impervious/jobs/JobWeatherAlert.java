package com.github.impervious.jobs;

import com.github.impervious.weather.ForecastMain;
import com.github.impervious.utils.Util;
import com.google.gson.Gson;

import lombok.SneakyThrows;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class JobWeatherAlert implements Job {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Gson gson = new Gson();

        ForecastMain forecast = new ForecastMain();
        forecast = gson.fromJson(forecast.jsonURL, ForecastMain.class);

        if((forecast.getAlerts() != null) && !forecast.getAlerts().isEmpty()) {

            //Util.notifyWeather("Alert:", forecast.getAlerts().get(0).getDescription(), "https://openweathermap.org/img/wn/" + forecast.getCurrent().getWeather().get(0).getIcon() + ".png", Instant.ofEpochSecond(forecast.getAlerts().get(0).getEnd()), Color.RED);

        }
    }
}