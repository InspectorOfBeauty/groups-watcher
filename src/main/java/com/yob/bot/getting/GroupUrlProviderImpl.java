package com.yob.bot.getting;

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
public class GroupUrlProviderImpl implements GroupUrlProvider, CommandLineRunner {
    private List<String> groupUrls;

    @Override
    public void run(String... args) {
        List<String> groupUrls = FileReader.readLines("config/urls.txt");

        if (this.groupUrls != null) {
            throw new YobException("GroupUrls is already initialized");
        }

        this.groupUrls = List.copyOf(groupUrls);
    }
}
