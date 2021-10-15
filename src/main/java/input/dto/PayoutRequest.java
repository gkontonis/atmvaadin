package input.dto;

import backend.entity.Currency;
import backend.entity.MoneyBox;

public class PayoutRequest {

    private final Currency currency;
    private final int value;

    public static boolean isCurrencyValueValid(int currencyValue) {
        return currencyValue >= 0;
    }

    public PayoutRequest(Currency currency, int value) {
        validateCurrencyValue(value);

        this.currency = currency;
        this.value = value;
    }

    private void validateCurrencyValue(int currencyValue) {
        if (isCurrencyValueValid(currencyValue)) {
            return;
        }
        throw new IllegalArgumentException("Currency Value has to be >= 0");
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getValue() {
        return value;
    }

}
