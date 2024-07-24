package com.yob.bot.language.actions.commands.impl;

import com.yob.bot.language.actions.AiRequester;
import com.yob.bot.language.actions.CommandNames;
import com.yob.bot.language.actions.commands.CommandHandler;
import com.yob.bot.util.AiResponseParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiAppealCommandHandler implements CommandHandler {
    private final AiRequester aiRequester;

    @Override
    public boolean isAcceptable(String messageText) {
        return messageText.toLowerCase().startsWith(CommandNames.AI);
    }

    @Override
    public String handle(String messageText) {
        String croppedText = messageText.substring(CommandNames.AI.length());
        return AiResponseParser.parse(aiRequester.request(croppedText));
    }
}
