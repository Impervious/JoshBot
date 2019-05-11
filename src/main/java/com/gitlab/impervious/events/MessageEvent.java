package com.gitlab.impervious.events;


import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageEvent extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String text = message.getContentRaw().toLowerCase();
        Guild guild;

        boolean isPrivate = message.isFromType(ChannelType.PRIVATE);

        if(!isPrivate) guild = message.getGuild();
        event.getMessage().getAuthor().isBot();
    }
}
