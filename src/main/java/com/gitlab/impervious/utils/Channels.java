package com.gitlab.impervious.utils;

import com.gitlab.impervious.Main;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.core.entities.TextChannel;

@RequiredArgsConstructor
public enum Channels {
    TEMPLOGS("473993256372404243");

    @Getter
    private final String id;

    public TextChannel getChannel() {
        return Main.getGuild().getTextChannelById(id);
    }
}
