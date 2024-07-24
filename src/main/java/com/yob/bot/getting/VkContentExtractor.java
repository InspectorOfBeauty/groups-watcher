package com.yob.bot.getting;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VkContentExtractor {

    public static String extractLastWallPostTextFrom(String page) {
        String xPath = "//div[@id = 'page_wall_posts']//div[@class = 'wall_post_text']";

        Elements elements = Jsoup.parse(page).selectXpath(xPath);

        if (!elements.isEmpty()) {
            return Objects.requireNonNull(elements.first()).text();
        } else {
            return "";
        }
    }
}
