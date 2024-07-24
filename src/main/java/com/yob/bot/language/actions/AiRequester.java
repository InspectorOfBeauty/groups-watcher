package com.yob.bot.language.actions;

import com.yob.bot.app.YobException;
import com.yob.bot.util.JsoupPostman;
import com.yob.bot.util.ResponseBodyReader;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AiRequester {
    private final String aiUrl;

    public AiRequester(@Value("${ai.local.server.url}") String aiUrl) {
        this.aiUrl = aiUrl;
    }

    public String request(String text) {
        String body = formatBody(text);
        try {
            Connection.Response response = JsoupPostman.executePostRequest(aiUrl, body);
            return ResponseBodyReader.getResponseBody(response);
        } catch (Exception e) {
            throw new YobException("Error occurred while requesting ai server", e);
        }
    }

    private String formatBody(String text) {
        return String.format("""
                {\s
                   "model": "TheBloke/Mixtral-8x7B-Instruct-v0.1-GGUF",\s
                   "messages": [\s
                     { "role": "system", "content": "Ты очень вежливый ассистент и всегда отвечаешь на русском языке." },
                     { "role": "user", "content": "%s" }
                   ],\s
                   "temperature": 0.7,\s
                   "max_tokens": -1,
                   "stream": true
                 }
                """, text);
    }
}
