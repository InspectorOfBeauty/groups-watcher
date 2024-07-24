package com.yob.bot.mailing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSender {
    private final VkLongPollApi vkLongPollApi;
    private final VkUserIdProvider userIdProviderImpl;

    public void sendTextMessage(String text) {
        for (String id : userIdProviderImpl.getVkUserIds()) {
            vkLongPollApi.sendMessage(Long.valueOf(id), text);
        }
    }
}
