package com.gitlab.impervious.commands;

import com.gitlab.impervious.weather.WeatherRoot;
import com.google.gson.*;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import lombok.SneakyThrows;

import java.io.*;
import java.net.URL;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
    }

    private static String readURL(String urlString) throws Exception {
        BufferedReader reader = null;

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?id=6094578&appid=0aa063ceb85171fa3a9b1e0882758357&units=metric");
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder builder = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                builder.append(chars, 0, read);
            }
            return builder.toString();
        } finally {
            if(reader != null) {
                reader.close();
            }
        }
    }

    @SneakyThrows
    @Override
    protected void execute(CommandEvent event) {
        String json = readURL("http://api.openweathermap.org/data/2.5/weather?id=6094578&appid=0aa063ceb85171fa3a9b1e0882758357&units=metric");

        Gson gson = new Gson();

        WeatherRoot weatherRoot = gson.fromJson(json, WeatherRoot.class);
        System.out.println("It's currently " + weatherRoot.getMain().getTemp());
    }
}