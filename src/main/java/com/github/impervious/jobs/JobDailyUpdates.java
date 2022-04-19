package com.github.impervious.jobs;

import com.github.impervious.weather.ForecastMain;
import com.github.impervious.utils.Channels;
import com.github.impervious.utils.Util;

import com.google.gson.Gson;

import lombok.SneakyThrows;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

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
        } catch(Exception e) {
            //Util.sendMessage(Channels.NOTIFICATIONS.getChannel(), "An error occured. Head on over to " + Channels.ERRORS.getChannel().getAsMention());
            Util.errorLog("**OH NO AN ERROR**", e);
        }
    }
}