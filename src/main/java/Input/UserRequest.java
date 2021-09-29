package Input;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UserRequest {
    public static final Set<Character> POSSIBLE_CURRENCIES;

    private final char currencyType;
    private final int currencyValue;

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

    public UserRequest(char currencyType, int currencyValue) {
        validateCurrencyType(currencyType);
        validateCurrencyValue(currencyValue);

        this.currencyType = currencyType;
        this.currencyValue = currencyValue;
    }

    private void validateCurrencyValue(int currencyValue) {
        if (isCurrencyValueValid(currencyValue)) {
            return;
        }
        throw new IllegalArgumentException("Currency Value has to be >= 0");
    }

    public char getCurrencyType() {
        return currencyType;
    }

    public int getCurrencyValue() {
        return currencyValue;
    }

    private void validateCurrencyType(char currencyType) {
        if (isCurrencyTypeValid(currencyType)) {
            return;
        }
        throw new IllegalArgumentException("Currency Type '" + currencyType + "' is not defined ad valid Currency Type");
    }
}
