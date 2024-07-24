package com.yob.bot.mailing;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import com.yob.bot.app.YobException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class VkLongPollApi {
    private Integer groupTs;
    private int messageCount;
    private final VkApiClient vkApiClient;
    private final GroupActor groupActor;

    public VkLongPollApi(@Value("${group.id}") String groupId,
                         @Value("${group.token}") String groupToken) {
        this.vkApiClient = new VkApiClient(new HttpTransportClient());
        this.groupActor = new GroupActor(Long.valueOf(groupId), groupToken);
        this.messageCount = 0;
    }

    public void sendMessage(Long userId, String text) {
        try {
            vkApiClient.messages()
                    .sendDeprecated(groupActor)
                    .userId(userId)
                    .message(text)
                    .randomId(messageCount++)
                    .execute();
        } catch (ApiException | ClientException e) {
            throw new YobException("Error occurred while sending private message", e);
        }
    }

    public List<Message> getNewMessages() {
        try {
            if (groupTs == null) {
                groupTs = getTs();
            }

            MessagesGetLongPollHistoryQuery historyQuery = vkApiClient.messages()
                    .getLongPollHistory(groupActor)
                    .ts(groupTs);
            List<Message> newMessages = historyQuery.execute().getMessages().getItems();
            groupTs = getTs();
            return newMessages;
        } catch (ApiException | ClientException e) {
            throw new YobException("Error occurred while getting new private messages", e);
        }
    }

    public Integer getTs() throws ClientException, ApiException {
        return vkApiClient.messages()
                .getLongPollServer(groupActor)
                .execute()
                .getTs();
    }

}
