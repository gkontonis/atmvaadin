package backend.helper;

import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;

public class Helper {
    private MoneyBoxContainer container = new MoneyBoxContainer();

    public Helper() {
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
        container.add(new MoneyBox(10, MoneyBox.Currency.B, MoneyBox.CurrencyType.COIN)); //not used
        container.add(new MoneyBox(5, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(20, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(50, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(100, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(400, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
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
}
