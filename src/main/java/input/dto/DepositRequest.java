package input.dto;

import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;

public class DepositRequest {
    private MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();


    public MoneyBox addMoneyBox(int value, MoneyBox.Currency currency, MoneyBox.CurrencyType type) {
        if(currency == null){
            throw new IllegalArgumentException("Currency must not be null");
        }
        if(type == null){
            throw new IllegalArgumentException("Type must not be null");
        }
        if(value < 0){
            throw new IllegalArgumentException("Value must not be smaller negative");
        }
        return null;
    }

    public MoneyBoxContainer getMoneyBoxContainer() {
        return moneyBoxContainer;
    }

    public boolean contains(MoneyBox moneyBox){
        throw new IllegalStateException("Not yet implemented!");
    }




}
