package com.yob.bot.language.actions;

import com.yob.bot.util.AiResponseParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextRewriter {
    private final AiRequester aiRequester;

    public String rephrase(String text) {
        String newtText = String.format("В 5 вариантах перефразируй текст на русский язык так, чтобы смысл остался тот же: %s. " +
                "Ответ должен быть не больше 2000 символов", text);
        return AiResponseParser.parse(aiRequester.request(newtText));
    }
}
