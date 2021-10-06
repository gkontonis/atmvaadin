package backend.calculator;

import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;
import input.dto.DepositRequest;
import input.dto.PayoutRequest;

import java.util.ArrayList;
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
        container.add(new MoneyBox(10, MoneyBox.Currency.B, MoneyBox.CurrencyType.COIN)); //not used
        container.add(new MoneyBox(5, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(20, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(50, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(100, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
        container.add(new MoneyBox(400, MoneyBox.Currency.B, MoneyBox.CurrencyType.NOTE));
    }

    public List<CalculationResult> calculate(PayoutRequest request) {
        if(request == null){
            throw new IllegalArgumentException("Request must not be null!");
        }

        MoneyBox.Currency currency = get(request.getCurrencyType());
        List<MoneyBox> boxes =  container.get(currency);
        int userRequestValue = request.getCurrencyValue();

        List<CalculationResult> result = new ArrayList<>();

        for(MoneyBox box : boxes){
            int amount = userRequestValue / box.getValue();
            if(amount == 0){
                continue;
            }
            userRequestValue = userRequestValue % box.getValue();
            //System.out.println("DEBUG: " + box.getValue() + " " + box.getType().toString() + " " +  amount);
            result.add(new CalculationResult(amount, box, currency));
        }

        return result;

    }

    public void deposit(DepositRequest depositRequest){
        container.deposit(depositRequest.getMoneyBoxContainer());
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
