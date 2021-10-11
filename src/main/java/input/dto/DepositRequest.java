package input.dto;

import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DepositRequest {
    public static final Set<Character> POSSIBLE_CURRENCIES;

    private MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();

    static {
        Set<Character> tmp = new HashSet<>();
        tmp.add('A');
        tmp.add('B');

        POSSIBLE_CURRENCIES = Collections.unmodifiableSet(tmp);
    }

    public static boolean isCurrencyTypeValid(char currency) {
        return POSSIBLE_CURRENCIES.contains(currency);
    }

    public MoneyBox addMoneyBox(MoneyBox moneyBox) {
        if (moneyBox == null) {
            throw new IllegalArgumentException("MoneyBox must not be null"); //TODO: ask if checks are needed
        }
        if (moneyBox.getCurrency() == null) {
            throw new IllegalArgumentException("Currency must not be null");
        }
        if (moneyBox.getType() == null) {
            throw new IllegalArgumentException("Type must not be null");
        }
        if (moneyBox.getValue() < 0) {
            throw new IllegalArgumentException("Value must not be smaller 0");
        }

        moneyBoxContainer.put(moneyBox);

        return moneyBox;
    }

    public MoneyBoxContainer getMoneyBoxContainer() {
        return moneyBoxContainer;
    }

 }
