package com.yob.bot.language.actions;

import com.yob.bot.util.AiResponseParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TextFilter {
    private final AiRequester aiRequester;
    private final ThemeProvider themeProvider;

    public boolean checkTopic(String text) {
        String chatGPtResponse = AiResponseParser
                .parse(aiRequester.request(formatFilterRequest(themeProvider.getThemes(), text)))
                .trim()
                .toLowerCase();
        return chatGPtResponse.startsWith("да") || chatGPtResponse.startsWith("da") || chatGPtResponse.startsWith("yes");
    }

    private String formatFilterRequest(List<String> themes, String text) {
        String themesAsString = themes.toString();
        int lengthOfThemesAsString = themesAsString.length();
        String formattedThemes = themesAsString.substring(1, lengthOfThemesAsString-1);

        return String.format("Ответь одним словом ДА или НЕТ, cодержится ли информация хотя бы по одной из следующих тем: %s " +
                "в тексте ниже: %s. ", formattedThemes, text);
    }
}
