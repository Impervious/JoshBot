package com.gitlab.impervious.jobs;

import com.gitlab.impervious.utils.Util;
import com.gitlab.impervious.weather.daily.DailyMain;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Hashtable;

public class JobDailyWeather implements Job {

    private static String headingToString(Float heading) {
        String strHeading = "?";
        Hashtable<String, Float> cardinal = new Hashtable<>();
        cardinal.put("North_1", (float) 0);
        cardinal.put("Northeast", 45f);
        cardinal.put("East", 90f);
        cardinal.put("Southeast", 135f);
        cardinal.put("South", 180f);
        cardinal.put("Southwest", 225f);
        cardinal.put("West", 270f);
        cardinal.put("Northwest", 315f);
        cardinal.put("North_2", 360f);

        for (String key: cardinal.keySet()) {
            Float value = cardinal.get(key);
            if (Math.abs(heading - value) < 30) {
                strHeading = key;
                if (key.contains("North_")) {
                    strHeading = "North";
                }
                break;
            }
        }
        return strHeading;
    }

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Gson gson = new Gson();

        DailyMain dailyForecast = new DailyMain();
        dailyForecast = gson.fromJson(dailyForecast.jsonURL, DailyMain.class);

        Util.notifyWeather("Daily Forecast", "It is " + Math.round(dailyForecast.getMain().getTemp()) + "°C and feels like " + Math.round(dailyForecast.getMain().getFeelsLike()) + "°C" + "\n"
                + "Wind is blowing at " + Math.round(dailyForecast.getWind().getSpeed() * 3.6) + " kph " + headingToString(Float.valueOf(dailyForecast.getWind().getDeg())));
    }
}
