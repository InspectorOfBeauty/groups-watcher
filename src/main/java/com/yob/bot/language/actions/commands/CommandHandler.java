package com.yob.bot.language.actions.commands;

public interface CommandHandler {
    boolean isAcceptable(String messageText);

    String handle(String messageText);
}
