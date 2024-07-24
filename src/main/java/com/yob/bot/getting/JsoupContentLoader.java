package com.yob.bot.getting;

import com.yob.bot.util.JsoupPostman;
import com.yob.bot.util.ResponseBodyReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsoupContentLoader {
    private final Map<String, String> HEADER = Collections.unmodifiableMap(createHeaders());

    public String loadContentFrom(String url) throws IOException {
        Connection.Response response = JsoupPostman.executeGetRequest(url, HEADER);
        return ResponseBodyReader.getResponseBody(response);
    }

    public static Map<String, String> createHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        headers.put("accept-language", "ru,en;q=0.9,en-GB;q=0.8,en-US;q=0.7");
        headers.put("cache-control", "max-age=0");
        headers.put("cookie", "remixlang=0; remixstlid=9061971051082016991_hHMKNPkzIKldMuhH9vP82zFBPuLLs0bMhrRcqEQ5DzL; remixstid=1146191393_64zDUcJc84VZ6o1tGxQWTpORZ5wKdLPOBnCmZPZHbzH; remixlgck=3b44b66ce34c667a3c; remixscreen_width=2560; remixscreen_height=1440; remixscreen_dpr=1.5; remixscreen_depth=24; remixdark_color_scheme=1; remixcolor_scheme_mode=auto; tmr_lvid=3760ef0e865a1f545049935a1203c054; tmr_lvidTS=1708364572750; remixua=-1%7C-1%7C206%7C1612666529; remixscreen_orient=1; remixsf=1; remixgp=25e4040003fb79d4318274520647b39a; remixdt=-3600; domain_sid=7EOw08od71JFV6fxHa5NU%3A1715262540156; remixscreen_winzoom=1.49; tmr_detect=0%7C1715264456997");
        headers.put("priority", "u=0, i");
        headers.put("sec-ch-ua", "\"Chromium\";v=\"124\", \"Microsoft Edge\";v=\"124\", \"Not-A.Brand\";v=\"99\"");
        headers.put("sec-ch-ua-mobile", "?0");
        headers.put("sec-ch-ua-platform", "\"Windows\"");
        headers.put("sec-fetch-dest", "document");
        headers.put("sec-fetch-mode", "navigate");
        headers.put("sec-fetch-site", "same-origin");
        headers.put("sec-fetch-user", "?1");
        headers.put("upgrade-insecure-requests", "1");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36 Edg/124.0.0.0");
        return headers;
    }
}
