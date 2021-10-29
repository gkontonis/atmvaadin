package business.src.main.java.atm.business.requests;

import backend.enums.Currency;

public class SimpleRequest {

    private final Currency currency;
    private final int value;

    public static boolean isCurrencyValueValid(int currencyValue) {
        return currencyValue >= 0;
    }

    public SimpleRequest(Currency currency, int value) {
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
