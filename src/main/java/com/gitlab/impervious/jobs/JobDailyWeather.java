package com.gitlab.impervious.jobs;

import com.gitlab.impervious.utils.Util;
import com.gitlab.impervious.weather.WeatherRoot;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class JobDailyWeather implements Job {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        Gson gson = new Gson();

        WeatherRoot weatherRoot = new WeatherRoot();
        weatherRoot = gson.fromJson(weatherRoot.jsonURL, WeatherRoot.class);

        Util.notifyWeather("Currently", "It is " + weatherRoot.getMain().getTemp() + " in " + weatherRoot.getName());
    }
}
