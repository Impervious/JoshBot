package com.gitlab.impervious.utils;

import com.gitlab.impervious.Main;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.core.entities.TextChannel;

@RequiredArgsConstructor
public enum Channels {
    LOG("473993256372404243"),
    NOTIFICATIONS("576886075994275841");

    @Getter
    private final String id;

    public TextChannel getChannel() {
        return Main.getGuild().getTextChannelById(id);
    }
}
