package com.yob.bot.language.actions;

import com.yob.bot.app.YobException;
import com.yob.bot.util.FileReader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
@Getter
public class ThemeProviderImpl implements ThemeProvider, CommandLineRunner {
    private List<String> themes;

    @Override
    public void run(String... args) {
        Collection<String> themes = FileReader.readLines("config/themes.txt");

        if (this.themes != null) {
            throw new YobException("Themes are already initialized");
        }

        this.themes = List.copyOf(themes);
    }
}
