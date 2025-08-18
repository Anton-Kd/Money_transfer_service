package ru.netology.money_transfer_service.transfer;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class MoneyTransfer {
    private String cardFromNumber;
    private String cardFromValidTill;
    private String cardFromCVV;
    private String cardToNumber;
    private String date;
    private String time;
    private Amount amount;

    public MoneyTransfer() {
    }

    public MoneyTransfer(Amount amount, String cardToNumber, String cardFromCVV, String cardFromValidTill, String cardFromNumber) {
        this.amount = amount;
        this.cardToNumber = cardToNumber;
        this.cardFromCVV = cardFromCVV;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromNumber = cardFromNumber;
    }

    @Override
    public String toString() {
        return "MoneyTransfer -> " +
                "amount: " + amount.toString() +
                "/ cardFromNumber: " + cardFromNumber +
                ", cardFromValidTill='" + cardFromValidTill + '\'' +
                "/ cardToNumber: " + cardToNumber;

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MoneyTransfer transfer = (MoneyTransfer) o;
        return Objects.equals(cardFromNumber, transfer.cardFromNumber) && Objects.equals(cardFromValidTill, transfer.cardFromValidTill) && Objects.equals(cardFromCVV, transfer.cardFromCVV) && Objects.equals(cardToNumber, transfer.cardToNumber) && Objects.equals(date, transfer.date) && Objects.equals(time, transfer.time) && Objects.equals(amount, transfer.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardFromNumber, cardFromValidTill, cardFromCVV, cardToNumber, date, time, amount);
    }
}

