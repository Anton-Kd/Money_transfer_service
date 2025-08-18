package ru.netology.money_transfer_service.service;

import org.springframework.stereotype.Service;
import ru.netology.money_transfer_service.exception.ErrorTransferOrConfirmException;
import ru.netology.money_transfer_service.logger.Logger;
import ru.netology.money_transfer_service.logger.LoggerImpl;
import ru.netology.money_transfer_service.repository.CardRepository;
import ru.netology.money_transfer_service.transfer.ConfirmOperationInfo;
import ru.netology.money_transfer_service.transfer.MoneyTransfer;
import ru.netology.money_transfer_service.transfer.MoneyTransferResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class TransferService {

    private CardRepository cardsRepository;
    private Logger logger;

    public TransferService(CardRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
        logger = LoggerImpl.getInstance();
    }

    public MoneyTransferResponse getCardsRepository(MoneyTransfer transfer) {
        transfer.setDate(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        transfer.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss.nnn")));
        logger.log("Transfer " + transfer);
        String id = cardsRepository.addTransfer(transfer);
        return new MoneyTransferResponse(id);
    }

    public MoneyTransferResponse confirmTransfer(ConfirmOperationInfo info) {
        MoneyTransfer transfer;
        if (info.getCode().equals("0000")) {
            transfer = cardsRepository.confirmOperation(info.getOperationId());
            if (transfer == null) {
                throw new ErrorTransferOrConfirmException("Error confirm : " + info);
            }
            String operationId = String.valueOf((info));
            logger.log("Confirm: " + operationId);
        } else {
            transfer = cardsRepository.errorConfirmOperation(info.getOperationId());
            if (transfer == null) {
                throw new ErrorTransferOrConfirmException("Error  errorConfirm : " + info);
            }
            logger.log("Error confirm: " + transfer);
        }
        return new MoneyTransferResponse(info.getOperationId() + "");
    }
}