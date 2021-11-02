package backend.calculator;

import backend.enums.Currency;
import backend.enums.CurrencyType;
import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;
import business.src.main.java.atm.business.requests.ComplexRequest;
import business.src.main.java.atm.business.requests.SimpleRequest;

import java.util.List;

public class Calculator {
    private static MoneyBoxContainer initialContainer = new MoneyBoxContainer();

    public Calculator() {
        initialContainer.put(new MoneyBox(1, Currency.B, CurrencyType.COIN));
        initialContainer.put(new MoneyBox(2, Currency.B, CurrencyType.COIN));
        initialContainer.put(new MoneyBox(10, Currency.B, CurrencyType.COIN)); //not used
        initialContainer.put(new MoneyBox(5, Currency.B, CurrencyType.NOTE));
        initialContainer.put(new MoneyBox(20, Currency.B, CurrencyType.NOTE));
        initialContainer.put(new MoneyBox(50, Currency.B, CurrencyType.NOTE));
        initialContainer.put(new MoneyBox(100, Currency.B, CurrencyType.NOTE));
        initialContainer.put(new MoneyBox(400, Currency.B, CurrencyType.NOTE));

        initialContainer.put(new MoneyBox(1, Currency.A, CurrencyType.COIN));
        initialContainer.put(new MoneyBox(2, Currency.A, CurrencyType.COIN));
        initialContainer.put(new MoneyBox(5, Currency.A, CurrencyType.NOTE));
        initialContainer.put(new MoneyBox(10, Currency.A, CurrencyType.NOTE));
        initialContainer.put(new MoneyBox(20, Currency.A, CurrencyType.NOTE));
        initialContainer.put(new MoneyBox(50, Currency.A, CurrencyType.NOTE));
        initialContainer.put(new MoneyBox(100, Currency.A, CurrencyType.NOTE));
        initialContainer.put(new MoneyBox(200, Currency.A, CurrencyType.NOTE));
        initialContainer.put(new MoneyBox(500, Currency.A, CurrencyType.NOTE));
    }

    public Calculator(MoneyBoxContainer moneyBoxContainer) {
        if (moneyBoxContainer == null) {
            throw new IllegalArgumentException("MoneyBoxContainer must not be null!");
        }
        initialContainer = moneyBoxContainer;
    }

    public CurrencyType getType(int value, char currencyChar) {
        Currency currency = getCurrency(currencyChar);
        for (MoneyBox mb : initialContainer.get(currency)) {
            if (value % mb.getValue() == 0) {
                return mb.getType();
            }
        }
        return null;
    }

    public MoneyBoxContainer calculateSuggestedDenomination(SimpleRequest simpleRequest) {
        if (simpleRequest == null) {
            throw new IllegalArgumentException("Payout Request must not be null!");
        }
        Currency currency = simpleRequest.getCurrency();
        List<MoneyBox> boxes = initialContainer.get(currency);
        int userRequestValue = simpleRequest.getValue();

        MoneyBoxContainer result = new MoneyBoxContainer();

        for (MoneyBox box : boxes) {
            int amount = userRequestValue / box.getValue();
            if (amount == 0) {
                continue;
            }
            userRequestValue = userRequestValue % box.getValue();
            result.deposit(new MoneyBox(box.getValue(), box.getCurrency(), box.getType(), amount));
        }
        return result;
    }

    public MoneyBoxContainer withdraw(ComplexRequest complexRequest) {
        MoneyBoxContainer result = initialContainer.withdraw(complexRequest.getMoneyBoxContainer());
        return result;
    }


    //TODO exceptions
    public MoneyBoxContainer withdrawSimpleRequest(SimpleRequest simpleRequest) {
        if (simpleRequest == null) {
            //return null;
            throw new IllegalArgumentException("SimpleRequest must not be null!");
        }
        if (calculatePossibleWithdrawal(simpleRequest) == null) {
            //return null;
            throw new IllegalArgumentException("MoneyBoxContainer for Simplerequest must not be null!");
        }
        MoneyBoxContainer result = initialContainer.withdraw(calculatePossibleWithdrawal(simpleRequest));
        return result;
    }

    MoneyBoxContainer calculatePossibleWithdrawal(SimpleRequest simpleRequest) { //Is now package private
        if (simpleRequest == null) {
            throw new IllegalArgumentException("Payout Request must not be null!");
        }

        MoneyBoxContainer resultMoneyBoxContainer = new MoneyBoxContainer();

        Currency currency = simpleRequest.getCurrency();
        int userRequestValue = simpleRequest.getValue();
        List<MoneyBox> boxes = initialContainer.get(currency);


        int valueLeft = userRequestValue;

        for (MoneyBox box : boxes) {
            int amount = valueLeft / box.getValue();
            if (amount == 0) {
                continue;
            }
            MoneyBox withdrawalBox = new MoneyBox(box.getValue(), box.getCurrency(), box.getType(), amount);
            if (initialContainer.containsAmount(withdrawalBox)) {
                resultMoneyBoxContainer.deposit(withdrawalBox);
                valueLeft = valueLeft % box.getValue();
            }
        }
        //TODO ATM Exception "zu wenig geld am konto bsp"
        if (valueLeft > 0) {
            System.out.println("BETRAG NICHT VORRÄTIG - CALC");
            //return null;
            throw new IllegalArgumentException("Value left > 0");
        }

        return resultMoneyBoxContainer;
    }

    public MoneyBoxContainer depositComplexRequest(ComplexRequest complexRequest) {
        if (complexRequest == null) {
            throw new IllegalArgumentException("Deposit Request must not be null!");
        }
        return depositContainer(complexRequest.getMoneyBoxContainer());
    }

    public MoneyBoxContainer depositSimpleRequest(SimpleRequest request) {
        return depositContainer(calculateSuggestedDenomination(request));
    }

    private MoneyBoxContainer depositContainer(MoneyBoxContainer moneyBoxContainer) {
        if (moneyBoxContainer == null) {
            throw new IllegalArgumentException("MoneyBoxContainer must not be null!");
        }
        initialContainer.depositContainer(moneyBoxContainer);
        return initialContainer;    //TODO: Return copy of initial container
    }

    public Currency getCurrency(char c) {
        for (int i = 0; i < Currency.values().length; i++) {
            if (String.valueOf(c).equals(Currency.values()[i].name())) {
                return Currency.values()[i];
            }
        }
        throw new IllegalArgumentException("Couldnt find currency " + c);
    }

    public MoneyBoxContainer getContainer() {
        return initialContainer;
    } //TODO: Return copy of initial container

}
