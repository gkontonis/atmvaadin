package input.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PayoutRequest {
    public static final Set<Character> POSSIBLE_CURRENCIES;

    private final char currency;
    private final int value;

    static {
        Set<Character> tmp = new HashSet<>();
        tmp.add('A');
        tmp.add('B');

        POSSIBLE_CURRENCIES = Collections.unmodifiableSet(tmp);
    }

    public static boolean isCurrencyTypeValid(char currency) {
        return POSSIBLE_CURRENCIES.contains(currency);
    }

    public static boolean isCurrencyValueValid(int currencyValue) {
        return currencyValue >= 0;
    }

    public PayoutRequest(char currency, int value) {
        validateCurrencyType(currency);
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

    public char getCurrency() {
        return currency;
    }

    public int getValue() {
        return value;
    }

    private void validateCurrencyType(char currencyType) {
        if (isCurrencyTypeValid(currencyType)) {
            return;
        }
        throw new IllegalArgumentException("Currency Type '" + currencyType + "' is not defined as valid Currency Type");
    }
}
