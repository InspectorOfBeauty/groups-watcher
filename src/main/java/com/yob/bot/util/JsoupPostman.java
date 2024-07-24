package com.yob.bot.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsoupPostman {
    /**
     * @throws HttpStatusException status != 200
     * @throws IOException any other network problem
     */
    public static Connection.Response executeGetRequest(String url, Map<String, String> headers) throws IOException {
        Connection connection = initBaseConnection(url)
                .method(Connection.Method.GET)
                .headers(headers);
        return connection.execute();
    }

    /**
     * @throws HttpStatusException status != 200
     * @throws IOException any other network problem
     */
    public static Connection.Response executePostRequest(String url, String body) throws IOException {
        Connection connection = initBaseConnection(url)
                .method(Connection.Method.POST)
                .header("Content-Type", "application/json")
                .requestBody(body);
        return connection.execute();

    }

    private static Connection initBaseConnection(String url) {
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .followRedirects(true)
                .timeout(30000);
    }
}
