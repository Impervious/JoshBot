package com.github.impervious.utils;

import com.github.impervious.JoshBot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.TextChannel;

@RequiredArgsConstructor
public enum Channels {

    // HOME GUILD CHANNELS
    LOG("473993256372404243"),
    NOTIFICATIONS("576886075994275841"),
    TESTROOM("373117816817057793"),
    ERRORS("581194394288390154");

    @Getter
    private final String id;

    public TextChannel getChannel() {
        return JoshBot.getGuild().getTextChannelById(id);
    }
}