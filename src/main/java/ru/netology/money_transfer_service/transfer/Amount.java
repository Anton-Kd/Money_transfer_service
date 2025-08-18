package ru.netology.money_transfer_service.transfer;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Amount {
    private Float value;
    private Currency currency;
    private Float commission;

    public Amount(Integer value, Currency currency) {
        this.value = (float) (value / 100);
        this.currency = currency;
        commission = (float) (value / 10000);
    }

    @Override
    public String toString() {
        return value + " " + currency + " commission: " + commission + " " + currency;

    }
}
