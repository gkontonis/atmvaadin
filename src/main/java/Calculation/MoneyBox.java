package Calculation;

public class MoneyBox {
    public enum Currency {A, B}

    public enum CurrencyType {COIN, NOTE}

    private int value;
    private Currency currency;
    private CurrencyType type;

    public MoneyBox(int value, Currency currency, CurrencyType type) {
        validateValue(value);
        this.value = value;

        if (currency == null) {
            throw new IllegalArgumentException("Currency must not be null!");
        }
        this.currency = currency;

        if (type == null) {
            throw new IllegalArgumentException("Type must not be null!");
        }
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public CurrencyType getType() {
        return type;
    }

    private void validateValue(int value) {
        if (value == 0) {
            throw new IllegalArgumentException("Type must not be 0!");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Type must not be smaller than 0!");
        }
        if (value == 1) {
            return;
        }
        if (value == 2) {
            return;
        }
        if(value%5 == 0){
            return;
        }
        if(value%10 == 0){
            return;
        }
        throw new IllegalArgumentException("Value " + value + " is not 1, 2, multiple of five or multiple of ten");

    }
}
