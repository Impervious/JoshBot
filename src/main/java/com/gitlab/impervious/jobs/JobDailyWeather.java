package com.gitlab.impervious.jobs;

import com.gitlab.impervious.utils.Util;
import com.gitlab.impervious.weather.daily.DailyMain;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class JobDailyWeather implements Job {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Gson gson = new Gson();

        DailyMain dailyForecast = new DailyMain();
        dailyForecast = gson.fromJson(dailyForecast.jsonURL, DailyMain.class);

        //DAILY FORECAST
        Util.notifyWeather("Daily Forecast", "It is " + Math.round(dailyForecast.getMain().getTemp()) + "°C and feels like " + Math.round(dailyForecast.getMain().getFeelsLike()) + "°C" + "\n"
                + "Wind is blowing at " + Math.round(dailyForecast.getWind().getSpeed() * 3.6) + " kph " + Util.headingToDirection(Float.valueOf(dailyForecast.getWind().getDeg())));

        //LONGTERM FORECAST

    }
}
