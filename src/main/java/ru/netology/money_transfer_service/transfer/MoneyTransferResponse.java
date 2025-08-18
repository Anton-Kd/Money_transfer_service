package ru.netology.money_transfer_service.transfer;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MoneyTransferResponse {
    private String operationId;

    public MoneyTransferResponse() {

    }
    public MoneyTransferResponse(String operationId) {
        this.operationId = operationId;
    }
}
