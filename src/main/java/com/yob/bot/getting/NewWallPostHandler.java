package com.yob.bot.getting;

import com.yob.bot.language.actions.TextFilter;
import com.yob.bot.language.actions.TextRewriter;
import com.yob.bot.mailing.MessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class NewWallPostHandler {
    private final WallPostRepository wallPostRepository;
    private final TextFilter textFilter;
    private final TextRewriter textRewriter;
    private final MessageSender messageSender;


    public void handleNewWallPosts(List<WallPost> newWallPosts) {
        wallPostRepository.saveAll(newWallPosts);
        List<WallPost> matchedNewWallPostsWithThemes = findMatchedNewWallPostsWithThemesIn(newWallPosts);
        List<RephrasedWallPost> rephrasedNewWallPosts = rephraseWallPostTextIn(matchedNewWallPostsWithThemes);
        sendRephrasedNewWallPosts(rephrasedNewWallPosts);
    }

    private List<WallPost> findMatchedNewWallPostsWithThemesIn(List<WallPost> newWallPosts) {
        List<WallPost> matchedNewWallPosts = new ArrayList<>();

        for (WallPost newWallPost : newWallPosts) {
            try {
                if (isNewWallPostMatchedWithThemes(newWallPost)) {
                    matchedNewWallPosts.add(newWallPost);
                }
            } catch (Exception e) {
                log.error("Error occurred while processing wall post", e);
            }
        }

        return matchedNewWallPosts;
    }

    private boolean isNewWallPostMatchedWithThemes(WallPost newWallPost) {
        return textFilter.checkTopic(newWallPost.getText());
    }

    private List<RephrasedWallPost> rephraseWallPostTextIn(List<WallPost> wallPosts) {
        List<RephrasedWallPost> rephrasedWallPosts = new ArrayList<>();

        for (WallPost wallPost : wallPosts) {
            try {
                String rephrasedWallPostText = textRewriter.rephrase(wallPost.getText());
                rephrasedWallPosts.add(new RephrasedWallPost(wallPost.getGroupUrl(), wallPost.getText(), rephrasedWallPostText));
            } catch (Exception e) {
                log.error("Error occurred while rephrase wall post", e);
            }
        }

        return rephrasedWallPosts;
    }

    private void sendRephrasedNewWallPosts(List<RephrasedWallPost> rephrasedNewWallPosts) {
        for (RephrasedWallPost rephrasedWallPost : rephrasedNewWallPosts) {
            messageSender.sendTextMessage(String.format("""
                    Ссылка на группу: %s
                    Оригинальный текст: "%s"
                    Перефразированные варианты:
                    %s""", rephrasedWallPost.getGroupUrl(), rephrasedWallPost.getOriginalText(), rephrasedWallPost.getRephrasedText()));
        }
    }
}
