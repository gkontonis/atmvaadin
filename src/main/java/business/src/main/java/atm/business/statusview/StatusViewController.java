package business.src.main.java.atm.business.statusView;

import backend.calculator.Calculator;
import backend.enums.Currency;
import backend.enums.CurrencyType;
import backend.entity.MoneyBox;

import java.util.List;

public class StatusViewController {

    private Calculator CALCULATOR = new Calculator();

    public String getStatus() {
        String output = "";

        CurrencyType currencyType;

        for (Currency currency : CALCULATOR.getContainer().getMap().keySet()) {
            currencyType = null;
            int totalOfType = 0;
            int total = 0;
            List<MoneyBox> resultList = CALCULATOR.getContainer().get(currency);
            for (MoneyBox currentMoneyBox : resultList) {
                if (currencyType != currentMoneyBox.getType()) {
                    if (currentMoneyBox.getType() == CurrencyType.NOTE) {
                        output += "Noten: ";
                        currencyType = currentMoneyBox.getType();
                    } else {
                        output += ": " + totalOfType;
                        output += "\n";
                        output += "Münzen: ";
                        totalOfType = 0;
                        currencyType = currentMoneyBox.getType();
                    }
                }
                output += currentMoneyBox.getValue() + "" + currentMoneyBox.getCurrency() + "" + currentMoneyBox.getAmount() + " ";
                totalOfType += currentMoneyBox.getValue() * currentMoneyBox.getAmount();
                total += currentMoneyBox.getValue() * currentMoneyBox.getAmount();
            }
            output += ": " + totalOfType;
            output += "\nGesamt: " + total + currency.name();

            System.out.println(output);
            System.out.println();
        }
        return output;
    }
}
