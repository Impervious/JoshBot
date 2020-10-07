package com.gitlab.impervious.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import lombok.SneakyThrows;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.InputStream;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
    }

    @SneakyThrows
    protected void execute(CommandEvent event) {

        /*String url = "https://www.reddit.com/r/gamedeals/search.rss?q=steam&sort=top&restrict_sr=1&t=day";
        try(CloseableHttpClient client = HttpClients.createMinimal()) {
            HttpUriRequest request = new HttpGet(url);
            try(CloseableHttpResponse response = client.execute(request)) {
                InputStream stream = response.getEntity().getContent();
                
            }
        }*/

    }
}