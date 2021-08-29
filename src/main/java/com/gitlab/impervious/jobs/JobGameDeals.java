package com.gitlab.impervious.jobs;

import com.gitlab.impervious.reddit.RedditMain;
import com.gitlab.impervious.utils.Channels;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.awt.*;
import java.time.Instant;
import java.util.HashMap;

public class JobGameDeals implements Job {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Gson gson = new Gson();

        RedditMain reddit = new RedditMain();
        reddit = gson.fromJson(reddit.jsonURL, RedditMain.class);

        HashMap<String, String> dealsArray = new HashMap<>();

        for(int i = 0; i < reddit.getData().getChildren().size(); i++) {
            dealsArray.put(reddit.getData().getChildren().get(i).getData().getTitle(), reddit.getData().getChildren().get(i).getData().getUrl());
        }

        EmbedBuilder gameDeals = new EmbedBuilder()
                .setTitle("Game Deals")
                .setColor(new Color(20, 61, 113))
                .setTimestamp(Instant.now());

        for(String s : dealsArray.keySet()) {
            gameDeals.addField(s, dealsArray.get(s), false);
        }

        //Channels.NOTIFICATIONS.getChannel().sendMessage(gameDeals.build()).queue();
    }
}