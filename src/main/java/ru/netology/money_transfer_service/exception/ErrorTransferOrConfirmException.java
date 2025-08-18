package ru.netology.money_transfer_service.exception;

public class ErrorTransferOrConfirmException extends RuntimeException {
    public ErrorTransferOrConfirmException(String msg) {
        super(msg);
    }
}