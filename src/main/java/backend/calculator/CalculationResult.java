package backend.calculator;

import backend.entity.MoneyBox;

public class CalculationResult {
    private int amount;
    private MoneyBox box;
    private MoneyBox.Currency currency;

    //Thrashed and has been replaces by MoneyBoxContainer
    public CalculationResult(int amount, MoneyBox box, MoneyBox.Currency currency) {
        if(amount < 0){
            throw new IllegalArgumentException("Amount must not be smaller than 0: " + amount);
        }
        this.amount = amount;

        if(box==null){
            throw new IllegalArgumentException("Box must not be null");
        }
        this.box = box;

        if(currency==null){
            throw new IllegalArgumentException("Currency must not be null");
        }
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public MoneyBox getBox() {
        return box;
    }

    public MoneyBox.Currency getCurrency() {
        return currency;
    }
}
