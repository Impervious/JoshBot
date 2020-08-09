package com.gitlab.impervious.events;

import com.gitlab.impervious.utils.Util;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotMention extends ListenerAdapter {

    public void onMessageRecieved(MessageReceivedEvent e) {
        Message msg = e.getMessage();

        if(msg.isMentioned(e.getJDA().getSelfUser())) {
            Util.botLog(msg);
        }
    }
}
