package com.yob.bot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yob.bot") // Создает базовую конфигурацию
public class GrabAdapterApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrabAdapterApplication.class); // Инициализиурет spring контекст, запускает веб-сервер, стартует приложение
    }
}
