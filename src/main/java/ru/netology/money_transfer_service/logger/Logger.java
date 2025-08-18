package ru.netology.money_transfer_service.logger;

public interface Logger {
    void log(String msg);

    static Logger getInstance() {
        return null;
    }
}
