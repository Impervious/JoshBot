package com.gitlab.impervious.utils;

import com.gitlab.impervious.FIABot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.TextChannel;

@RequiredArgsConstructor
public enum Channels {

    // HOME GUILD CHANNELS
    LOG("473993256372404243"),
    NOTIFICATIONS("576886075994275841"),
    TESTROOM("373117816817057793"),
    ERRORS("581194394288390154"),

    //LNR CHANNELS
    BOTTING("788105798743752715"),
    PROFIADISCUSSION("774505083600896041"),
    DEVFIADISCUSSION("774504983768072202");

    @Getter
    private final String id;

    /*public TextChannel getChannel() {
        return FIABot.getHomeGuild().getTextChannelById(id);
    }*/

    public TextChannel getLNRChannel() {
        return FIABot.getLNRGuild().getTextChannelById(id);
    }
}
