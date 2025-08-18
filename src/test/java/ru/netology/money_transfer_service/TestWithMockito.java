package ru.netology.money_transfer_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.money_transfer_service.repository.CardRepository;
import ru.netology.money_transfer_service.service.TransferService;
import ru.netology.money_transfer_service.transfer.*;

@SpringBootTest
class TestWithMockito {

    static TransferService transferService;
    static CardRepository cardRepository;

    @BeforeAll
    static void init() {
        cardRepository = new CardRepository();
        transferService = new TransferService(cardRepository);

    }

    @Test
    void serviseTransferTest() {

        MoneyTransfer moneyTransfer = new MoneyTransfer(
                new Amount(500, Currency.RUR),
                "1111222233334444",
                "123",
                "12/25",
                "0000111122224444"
        );

        MoneyTransferResponse id = transferService.getCardsRepository(moneyTransfer);
        Assertions.assertEquals(1 + "", id.getOperationId());
    }

    @Test
    void serviceConfirmOperationTest() {
        ConfirmOperationInfo confirmOperationInfo = Mockito.spy(ConfirmOperationInfo.class);
        Mockito.when(confirmOperationInfo.getOperationId()).thenReturn("1");
        Mockito.when(confirmOperationInfo.getCode()).thenReturn("0000");
        MoneyTransferResponse id = transferService.confirmTransfer(confirmOperationInfo);
        Assertions.assertEquals(1 + "", id.getOperationId());
    }
}
