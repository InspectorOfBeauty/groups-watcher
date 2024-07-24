package com.yob.bot.mailing;

import com.yob.bot.app.YobException;
import com.yob.bot.util.FileReader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Getter
public class VkUserIdProviderImpl implements VkUserIdProvider, CommandLineRunner {
    private List<String> vkUserIds;


    public List<String> getVkUserIds() {
        return List.copyOf(vkUserIds);
    }

    @Override
    public void run(String... args) {
        List<String> vkUserIds = FileReader.readLines("config/ids.txt");

        if (this.vkUserIds != null) {
            throw new YobException("VkUserIds is already initialized");
        }

        this.vkUserIds = List.copyOf(vkUserIds);
    }
}
