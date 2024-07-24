package com.yob.bot;

import com.vk.api.sdk.objects.messages.Message;
import com.yob.bot.language.actions.commands.CommandHandler;
import com.yob.bot.mailing.MessageSender;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PrivateMessageHandler {
    private static final Logger log = LoggerFactory.getLogger(PrivateMessageHandler.class);
    private final List<CommandHandler> handlers;
    private final MessageSender messageSender;

    public void handle(Message message) {
        for (CommandHandler handler : handlers) {
            try {
                if (handler.isAcceptable(message.getText())) {
                    String answer = handler.handle(message.getText());
                    messageSender.sendTextMessage(answer);
                    return;
                }
            } catch (Exception e) {
                log.error("Error occurred while processing message", e);
                try {
                    messageSender.sendTextMessage("Response from ai-server couldn't be parsed, sorry!");
                } catch (Exception fe) {
                    log.error("Error occurred while sending fallback response", fe);
                }
            }
        }

    }
}
