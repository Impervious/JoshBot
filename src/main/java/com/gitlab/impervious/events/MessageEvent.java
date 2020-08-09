package com.gitlab.impervious.events;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageEvent extends ListenerAdapter {

    public void onMessageReceieved(MessageReceivedEvent e) {
        Message message = e.getMessage();
        String text = message.getContentRaw().toLowerCase();
        Guild guild;

        boolean isPrivate = message.isFromType(ChannelType.PRIVATE);

        if(!isPrivate) guild = message.getGuild();
        e.getMessage().getAuthor().isBot();
    }
}
