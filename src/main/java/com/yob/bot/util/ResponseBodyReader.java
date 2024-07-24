package com.yob.bot.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseBodyReader {
    public static String getResponseBody(Connection.Response response) throws IOException {
        InputStream bodyStream = response.bodyStream();

        if (isVkResponse(response)) {
            return readBodyStream(bodyStream, "windows-1251");
        } else {
            return readBodyStream(bodyStream, "UTF8");
        }
    }

    private static String readBodyStream(InputStream responseInputStreamBody, String charset) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseInputStreamBody, charset))) {
            StringBuilder responseBody = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                responseBody.append(line).append("\n");
            }

            return responseBody.toString();
        }
    }

    private static boolean isVkResponse(Connection.Response response) {
        return response.url().toString().startsWith("https://vk.com/");
    }
}
