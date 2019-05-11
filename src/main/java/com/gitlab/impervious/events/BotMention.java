package com.gitlab.impervious.events;

import com.gitlab.impervious.utils.Util;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotMention extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();

        if(msg.isMentioned(event.getJDA().getSelfUser())) {
            Util.botLog(msg);
        }
    }
}