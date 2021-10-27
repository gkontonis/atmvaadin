package backend.calculator;

import backend.entity.Currency;
import backend.entity.CurrencyType;
import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;
import input.dto.DepositRequest;
import input.dto.PayoutRequest;
import input.reader.DepositInputReader;
import output.Output;

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
           // System.out.println("INTERNER FEHLER");
           // return;
        }
        initialContainer = moneyBoxContainer;
    }

    //GetTypeMethod should maybe be somewhere else
    public CurrencyType getType(int value, char currencyChar) {
        Currency currency = getCurrency(currencyChar);
        for (MoneyBox mb : initialContainer.get(currency)) {
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

        Currency currency = payoutRequest.getCurrency();
        List<MoneyBox> boxes = initialContainer.get(currency);
        int userRequestValue = payoutRequest.getValue();

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


    public MoneyBoxContainer calculateAndWithdraw(PayoutRequest payoutRequest) {
        if (payoutRequest == null) {
            return null;
        }
        MoneyBoxContainer result = initialContainer.withdraw(calculatePossibleWithdrawal(payoutRequest));
        return result;
    }

    public MoneyBoxContainer calculatePossibleWithdrawal(PayoutRequest payoutRequest) {
        if (payoutRequest == null) {
            throw new IllegalArgumentException("Payout Request must not be null!");
        }

        Currency currency = payoutRequest.getCurrency();
        List<MoneyBox> boxes = initialContainer.get(currency);
        int userRequestValue = payoutRequest.getValue();

        MoneyBoxContainer resultMoneyBoxContainer = new MoneyBoxContainer();

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
        if (valueLeft > 0) {
            System.out.println("BETRAG NICHT VORRÄTIG");
        }
        return resultMoneyBoxContainer;
    }

    public MoneyBoxContainer deposit(DepositRequest depositRequest) {
        if (depositRequest == null) {
            throw new IllegalArgumentException("Deposit Request must not be null!");

        }
        initialContainer.depositContainer(depositRequest.getMoneyBoxContainer());
        return initialContainer;
    }

    public MoneyBoxContainer deposit(MoneyBoxContainer moneyBoxContainer) {
        if (moneyBoxContainer == null) {
            throw new IllegalArgumentException("MoneyBoxContainer must not be null!");

        }
        initialContainer.depositContainer(moneyBoxContainer);
        return initialContainer;
    }

    public Currency getCurrency(char c) {
        for (int i = 0; i < Currency.values().length; i++){
            if (String.valueOf(c).equals(Currency.values()[i].name())){
                return Currency.values()[i];
            }
        }
        throw new IllegalArgumentException("Couldnt find currency " + c);    //TODO: Maybe instead return null?
    }

    public MoneyBoxContainer getContainer() {
        return initialContainer;
    }

  // public boolean validateCurrency(Currency curr) {
  //       for (Currency c : currencies) {
  //          if (c.equals(curr)) {
  //              return true;
  //          }
  //      }
  //      System.out.println("Currency is invalid");
  //      return false;
  //  }
}
