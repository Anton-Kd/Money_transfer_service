package ru.netology.money_transfer_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.money_transfer_service.service.TransferService;
import ru.netology.money_transfer_service.transfer.ConfirmOperationInfo;
import ru.netology.money_transfer_service.transfer.MoneyTransfer;
import ru.netology.money_transfer_service.transfer.MoneyTransferResponse;

@RestController
@RequestMapping
public class TransferController {
    TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public MoneyTransferResponse transferMoney(@RequestBody MoneyTransfer transfer) {
        return service.getCardsRepository(transfer);
    }

    @PostMapping("/confirmOperation")
    public MoneyTransferResponse confirmOperation(@RequestBody ConfirmOperationInfo info) {
        return service.confirmTransfer(info);
    }
}
