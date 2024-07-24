package com.yob.bot.getting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class NewWallPostFinder {
    private final GroupUrlProvider groupUrlProviderImpl;
    private final JsoupContentLoader jsoupContentLoader;
    private final WallPostRepository wallPostRepository;

    public List<WallPost> findNewAndModifiedWallPosts() {
        List<WallPost> currentLastWallPosts = findAllCurrentLastWallPosts();
        List<WallPost> newWallPosts = new ArrayList<>();

        for (WallPost lastWallPost : currentLastWallPosts) {
            if (isWallPostNew(lastWallPost)) {
                newWallPosts.add(lastWallPost);
            } else if (isWallPostModified(lastWallPost)) {
                WallPost wallPostFromBd = wallPostRepository.getByGroupUrl(lastWallPost.getGroupUrl());
                lastWallPost.setId(wallPostFromBd.getId());
                newWallPosts.add(lastWallPost);
            }
        }

        return newWallPosts;
    }

    private List<WallPost> findAllCurrentLastWallPosts() {
        List<String> groupUrls = groupUrlProviderImpl.getGroupUrls();
        List<WallPost> currentLastPosts = new ArrayList<>();

        for (String groupUrl : groupUrls) {
            try {
                String pageContent = jsoupContentLoader.loadContentFrom(groupUrl);
                String lastWallPostText = VkContentExtractor.extractLastWallPostTextFrom(pageContent);
                currentLastPosts.add(new WallPost(groupUrl, lastWallPostText));
            } catch (Exception e) {
                log.error("Error occurred while reading wall post {}", groupUrl, e);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("Error occurred while sleeping", e);
                return Collections.emptyList();
            }
        }

        return currentLastPosts;
    }

    private boolean isWallPostNew(WallPost lastWallPost) {
        WallPost wallPostFromBd = wallPostRepository.getByGroupUrl(lastWallPost.getGroupUrl());
        return wallPostFromBd == null;
    }

    private boolean isWallPostModified(WallPost lastWallPost) {
        WallPost wallPostFromBd = wallPostRepository.getByGroupUrl(lastWallPost.getGroupUrl());
        return (wallPostFromBd != null) && !lastWallPost.getText().equals(wallPostFromBd.getText());
    }
}
