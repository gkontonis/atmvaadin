package Calculation;

import Input.UserRequest;

import java.util.List;

public class Calculator {
    private MoneyBoxContainer container = new MoneyBoxContainer();

    public Calculator() {
        this.container = container;
        container.add(new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
        container.add(new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
        container.add(new MoneyBox(5, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(10, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(20, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(50, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(100, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(200, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(500, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));

        container.add(new MoneyBox(1, MoneyBox.Currency.B, MoneyBox.CurrencyType.COIN));
        container.add(new MoneyBox(2, MoneyBox.Currency.B, MoneyBox.CurrencyType.COIN));
        container.add(new MoneyBox(5, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(20, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(50, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(100, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(200, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(400, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
    }

    public void calculate(UserRequest request) {
        MoneyBox.Currency currency = get(request.getCurrencyType());
        List<MoneyBox> boxes =  container.get(currency);
        int number = request.getCurrencyValue();

        for(MoneyBox box : boxes){
            int amount = number / box.getValue();
            if(amount == 0){
                continue;
            }
            number = number % box.getValue();
            System.out.println("DEBUG: " + box.getValue() + box.getType().toString() + amount);
        }


    }

    private MoneyBox.Currency get(char c) {
        switch (c) {
            case 'A':
                return MoneyBox.Currency.A;
            case 'B' :
                return MoneyBox.Currency.B;
            default:
                throw new IllegalArgumentException("Couldnt find currency " + c );
        }
    }
}