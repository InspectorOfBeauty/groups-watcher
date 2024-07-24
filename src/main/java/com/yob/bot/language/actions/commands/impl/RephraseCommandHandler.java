package com.yob.bot.language.actions.commands.impl;

import com.yob.bot.language.actions.commands.CommandHandler;
import com.yob.bot.language.actions.AiRequester;
import com.yob.bot.language.actions.CommandNames;
import com.yob.bot.util.AiResponseParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RephraseCommandHandler implements CommandHandler {
    private final AiRequester aiRequester;

    @Override
    public boolean isAcceptable(String messageText) {
        return messageText.toLowerCase().startsWith(CommandNames.REPHRASE);
    }

    @Override
    public String handle(String messageText) {
        String croppedText = messageText.substring(CommandNames.REPHRASE.length());
        String requestText = String.format("В 5 вариантах перефразируй текст на русский язык так, чтобы смысл остался тот же: %s. " +
                "Ответ должен быть не больше 2000 символов", croppedText);
        return AiResponseParser.parse(aiRequester.request(requestText));
    }
}
