package ru.netology.money_transfer_service.transfer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmOperationInfo {
    private String operationId;
    private String code;

    public ConfirmOperationInfo() {
    }

    public ConfirmOperationInfo(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    @Override
    public String toString() {
        return "operationId='" + operationId +
                ", code=" + code;

    }
}
