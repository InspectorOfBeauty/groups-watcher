package com.yob.bot.app;

public class YobException extends RuntimeException {
    public YobException(String message) {
        super(message);
    }

    public YobException(String message, Throwable cause) {
        super(message, cause);
    }
}
