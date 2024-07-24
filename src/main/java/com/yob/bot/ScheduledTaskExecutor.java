package com.yob.bot;

import com.vk.api.sdk.objects.messages.Message;
import com.yob.bot.getting.NewWallPostFinder;
import com.yob.bot.getting.NewWallPostHandler;
import com.yob.bot.getting.WallPost;
import com.yob.bot.mailing.VkLongPollApi;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ScheduledTaskExecutor {
    private final NewWallPostFinder newWallPostFinder;
    private final NewWallPostHandler newWallPostHandler;
    private final VkLongPollApi vkLongPollApi;
    private final PrivateMessageHandler privateMessageHandler;

    @Scheduled(fixedDelay = 30000)
    public void findAndHandleNewPosts() {
        List<WallPost> newWallPosts = newWallPostFinder.findNewAndModifiedWallPosts();
        newWallPostHandler.handleNewWallPosts(newWallPosts);
    }

    @Scheduled(fixedDelay = 500)
    public void getAndHandlePrivateMessages() {
        try {
            List<Message> newMessages = vkLongPollApi.getNewMessages();

            for (Message message : newMessages) {
                log.info(message.getText());
                privateMessageHandler.handle(message);
            }
        } catch (Exception e) {
            log.error("Error occurred while getting and handling private messages", e);
        }
    }
}
