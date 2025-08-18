package ru.netology.money_transfer_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.netology.money_transfer_service.advice.ErrorResponse;
import ru.netology.money_transfer_service.transfer.*;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferServiceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final GenericContainer<?> app = new GenericContainer<>("transferapp:1.0")
            .withExposedPorts(5500);

    @BeforeAll
    static void setUp() {
        app.start();
    }

    @Test
    void transferMoneyTest() {
        MoneyTransfer moneyTransfer = new MoneyTransfer(
                new Amount(500, Currency.RUR),
                "1111222233334444",
                "123",
                "12/25",
                "0000111122224444"
        );
        MoneyTransferResponse response = restTemplate.postForObject("http://localhost:" + app.getMappedPort(5500) + "/transfer", moneyTransfer, MoneyTransferResponse.class);
        Assertions.assertEquals(1 + "", response.getOperationId());

    }

    @Test
    void validTransferTest() {
        MoneyTransfer moneyTransfer = new MoneyTransfer(
                new Amount(1000, Currency.RUR),
                "",
                "",
                "",
                ""
        );
        ResponseEntity<ErrorResponse> response = restTemplate.postForEntity("http://localhost:" + app.getMappedPort(5500) + "/transfer", moneyTransfer, ErrorResponse.class);
        Assertions.assertNull(Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void ConfirmOperationTest() {
        ConfirmOperationInfo confirmOperationInfo = new ConfirmOperationInfo();
        confirmOperationInfo.setOperationId("1");
        confirmOperationInfo.setCode("0000");
        MoneyTransferResponse response = restTemplate.postForObject("http://localhost:" + app.getMappedPort(5500) + "/confirmOperation", confirmOperationInfo, MoneyTransferResponse.class);
        Assertions.assertEquals(1 + "", response.getOperationId());
    }
}