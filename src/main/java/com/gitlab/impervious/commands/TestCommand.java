package com.gitlab.impervious.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
    }

    @Override
    protected void execute(CommandEvent event) {
        System.out.println("test");
        try {
            URL yahoo = new URL("http://api.openweathermap.org/data/2.5/weather?id=6094578&appid=0aa063ceb85171fa3a9b1e0882758357&units=metric");
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}