package backend.calculator;

import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;
import input.dto.DepositRequest;
import input.dto.PayoutRequest;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private static MoneyBoxContainer container = new MoneyBoxContainer();

    public Calculator() {
        this.container = container;
        container.put(new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
        container.put(new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
        container.put(new MoneyBox(5, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        container.put(new MoneyBox(10, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        container.put(new MoneyBox(20, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        container.put(new MoneyBox(50, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        container.put(new MoneyBox(100, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        container.put(new MoneyBox(200, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        container.put(new MoneyBox(500, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));

        container.put(new MoneyBox(1, MoneyBox.Currency.B, MoneyBox.CurrencyType.COIN));
        container.put(new MoneyBox(2, MoneyBox.Currency.B, MoneyBox.CurrencyType.COIN));
        container.put(new MoneyBox(10, MoneyBox.Currency.B, MoneyBox.CurrencyType.COIN)); //not used
        container.put(new MoneyBox(5, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.put(new MoneyBox(20, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.put(new MoneyBox(50, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.put(new MoneyBox(100, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.put(new MoneyBox(400, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
    }


    public Calculator(MoneyBoxContainer moneyBoxContainer) {
        if (moneyBoxContainer == null) {
            throw new IllegalArgumentException("MoneyBoxContainer must not be null!");
        }

        container = moneyBoxContainer;
    }

    //TODO List of all MoneyBoxes and GetTypeMethod should probably be somewhere else
    public MoneyBox.CurrencyType getType(int value, char currencyChar) {
        MoneyBox.Currency currency = getCurrency(currencyChar);
        for (MoneyBox mb : container.get(currency)) {
            if (value % mb.getValue() == 0) {
                return mb.getType();
            }
        }
        return null;
    }

    //Bsp 1. Stückelungen, schaut nicht ob vorhanden
    public MoneyBoxContainer calculateSuggestedDenomination(PayoutRequest payoutRequest) {
        if (payoutRequest == null) {
            throw new IllegalArgumentException("Payout Request must not be null!");
        }

        MoneyBox.Currency currency = getCurrency(payoutRequest.getCurrency());
        List<MoneyBox> boxes = container.get(currency);
        int userRequestValue = payoutRequest.getValue();

        MoneyBoxContainer result = new MoneyBoxContainer();
        //List<CalculationResult> result = new ArrayList<>();

        for (MoneyBox box : boxes) {
            int amount = userRequestValue / box.getValue();
            if (amount == 0) {
                continue;
            }
            userRequestValue = userRequestValue % box.getValue();
            //System.out.println("DEBUG: " + box.getValue() + " " + box.getType().toString() + " " +  amount);
            result.deposit(new MoneyBox(box.getValue(), box.getCurrency(), box.getType(), amount));
        }
        return result;
    }

    public MoneyBoxContainer calculateAndWithdraw(PayoutRequest payoutRequest) {
        MoneyBoxContainer result = container.withdraw(calculatePossibleWithdrawal(payoutRequest));
        return result;
    }

    public MoneyBoxContainer calculatePossibleWithdrawal(PayoutRequest payoutRequest) {
        if (payoutRequest == null) {
            throw new IllegalArgumentException("Payout Request must not be null!");
        }

        MoneyBox.Currency currency = getCurrency(payoutRequest.getCurrency());
        List<MoneyBox> boxes = container.get(currency);
        int userRequestValue = payoutRequest.getValue();

        MoneyBoxContainer resultMoneyBoxContainer = new MoneyBoxContainer();

        int valueLeft = userRequestValue;

        for (MoneyBox box : boxes) {
            int amount = valueLeft / box.getValue();
            if (amount == 0) {
                continue;
            }
            MoneyBox withdrawalBox = new MoneyBox(box.getValue(), box.getCurrency(), box.getType(), amount);
            if (container.containsAmount(withdrawalBox)) {
                resultMoneyBoxContainer.deposit(withdrawalBox);
                valueLeft = valueLeft % box.getValue();
            }
        }
        if (valueLeft > 0) {
            System.out.println("BETERAG NICHT VORRÄTIG");
        }
        return resultMoneyBoxContainer;
    }

    public void deposit(DepositRequest depositRequest) {
        if (depositRequest == null) {
            throw new IllegalArgumentException("Deposit Request must not be null!");

        }
        container.depositContainer(depositRequest.getMoneyBoxContainer());
    }

    public MoneyBox.Currency getCurrency(char c) {
        switch (c) {
            case 'A':
                return MoneyBox.Currency.A;
            case 'B':
                return MoneyBox.Currency.B;
            default:
                throw new IllegalArgumentException("Couldnt find currency " + c);
        }
    }

    public MoneyBoxContainer getContainer() {
        return container;
    }
}
