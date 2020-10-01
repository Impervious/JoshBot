package com.gitlab.impervious.jobs;

import com.gitlab.impervious.covid.COVIDMain;
import com.gitlab.impervious.utils.Util;
import com.gitlab.impervious.weather.ForecastMain;

import com.google.gson.Gson;

import lombok.SneakyThrows;

import org.apache.commons.text.WordUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class JobDailyWeather implements Job {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Gson gson = new Gson();

        ForecastMain forecast = new ForecastMain();
        forecast = gson.fromJson(forecast.jsonURL, ForecastMain.class);

        COVIDMain covid = new COVIDMain();
        covid = gson.fromJson(covid.jsonURL, COVIDMain.class);

        Util.notifyWeather("Daily Forecast:" + "\n"
                        + WordUtils.capitalize(forecast.getDaily().get(0).getWeather().get(0).getDescription()),
                "It will be " + Math.round(forecast.getDaily().get(0).getTemp().getDay()) + "°C and feel like " + Math.round(forecast.getDaily().get(0).getFeelsLike().getDay()) + "°C" + "\n"
                        + "Wind will blow " + Util.headingToDirection(Float.valueOf(forecast.getDaily().get(0).getWindDeg())) + " at " + Math.round(forecast.getDaily().get(0).getWindSpeed() * 3.6) + " kph" + "\n"
                        + covid.getCases().get(0).getCases().toString() + " new COVID-19 cases",
                "https://openweathermap.org/img/wn/" + forecast.getDaily().get(0).getWeather().get(0).getIcon() + ".png");
    }
}