package backend.entity;

import backend.enums.Currency;
import backend.enums.CurrencyType;

public class MoneyBox {

    private int value;
    private Currency currency;
    private CurrencyType type;
    private int amount = 0;

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

    public MoneyBox(int value, Currency currency, CurrencyType type, int initialAmount) {
        this(value, currency, type);

        validateAmount(initialAmount);

        amount = initialAmount;
    }

    public int deposit(int amount){
        validateAmountDeposition(amount);

        this.amount += amount;
        return this.amount;
    }

    public int payout(int amount){

        validateAmount(amount);

        if(this.amount-amount < 0){
            amount = this.amount;
            this.amount = 0;
            return amount;
        }

        this.amount -= amount;

        return this.amount;
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

    public int getAmount() { return amount; }

    public void setAmount(int amount){
        this.amount = amount;
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
        if (value % 5 == 0) {
            return;
        }
        if (value % 10 == 0) {
            return;
        }
        throw new IllegalArgumentException("Value " + value + " is not 1, 2, multiple of five or multiple of ten");

    }

    private void validateAmount(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("Amount must not be smaller than zero!");
        }
    }

    private void validateAmountDeposition(int amount){
        validateAmount(amount);
        if(this.amount + amount < this.amount){
            throw new IllegalArgumentException("Cannot add " + amount + " pieces. Only possible: " + (Integer.MAX_VALUE - this.amount));
        }
    }
}
