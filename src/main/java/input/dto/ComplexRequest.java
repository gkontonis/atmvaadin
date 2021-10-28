package input.dto;

import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ComplexRequest {

    private MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();

    public MoneyBox addMoneyBox(MoneyBox moneyBox) {
        if (moneyBox == null) {
            throw new IllegalArgumentException("MoneyBox must not be null");
        }
        moneyBoxContainer.deposit(moneyBox);
        return moneyBox;
    }

    public MoneyBoxContainer getMoneyBoxContainer() {
        return moneyBoxContainer;
    }

}
