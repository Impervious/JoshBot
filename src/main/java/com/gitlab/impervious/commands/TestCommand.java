package com.gitlab.impervious.commands;

import com.gitlab.impervious.utils.Util;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
        this.help = "just a test :)";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        commandEvent.getMessage().delete().queue();
        commandEvent.reply("hello");
        Util.notify420("h");
    }
}