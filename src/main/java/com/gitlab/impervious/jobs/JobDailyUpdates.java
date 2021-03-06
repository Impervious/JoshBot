package com.gitlab.impervious.jobs;

import com.gitlab.impervious.covid.COVIDMain;
import com.gitlab.impervious.utils.Channels;
import com.gitlab.impervious.utils.Util;
import com.gitlab.impervious.weather.ForecastMain;

import com.google.gson.Gson;

import lombok.SneakyThrows;

import org.apache.commons.text.WordUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.awt.*;
import java.time.Instant;

public class JobDailyUpdates implements Job {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Gson gson = new Gson();

        ForecastMain forecast = new ForecastMain();
        forecast = gson.fromJson(forecast.jsonURL, ForecastMain.class);

        /*COVIDMain covid = new COVIDMain();
        covid = gson.fromJson(covid.jsonURL, COVIDMain.class);*/

        try {
            Util.notifyWeather("Daily Forecast:" + "\n"
                            + WordUtils.capitalize(forecast.getDaily().get(0).getWeather().get(0).getDescription()),
                    "It will be **" + Math.round(forecast.getDaily().get(0).getTemp().getDay()) + "°C** and feel like **" + Math.round(forecast.getDaily().get(0).getFeelsLike().getDay()) + "°C**" + "\n"
                            + "Wind will blow **" + Util.headingToDirection(Float.valueOf(forecast.getDaily().get(0).getWindDeg())) + " at " + Math.round(forecast.getDaily().get(0).getWindSpeed() * 3.6) + " kph**",
                    "https://openweathermap.org/img/wn/" + forecast.getDaily().get(0).getWeather().get(0).getIcon() + ".png", Instant.now(), Color.YELLOW);
        } catch(Exception e) {
            Util.sendMessage(Channels.NOTIFICATIONS.getChannel(), "An error occured. Head on over to " + Channels.ERRORS.getChannel().getAsMention());
            Util.errorLog("**OH NO AN ERROR**", e);
        }
    }
}