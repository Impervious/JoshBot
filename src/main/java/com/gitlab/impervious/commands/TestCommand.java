package com.gitlab.impervious.commands;

import com.gitlab.impervious.weather.forecast.ForecastMain;
import com.google.gson.Gson;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import lombok.SneakyThrows;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
    }

    @SneakyThrows
    @Override
    protected void execute(CommandEvent event) {
        Gson gson = new Gson();

        ForecastMain forecast = new ForecastMain();
        forecast = gson.fromJson(forecast.jsonURL, ForecastMain.class);

        System.out.println(forecast.getDaily().get(1).getTemp().getMax());

        //Util.notifyWeather("Test", forecast.getDaily().toString());
    }

}