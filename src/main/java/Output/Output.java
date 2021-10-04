package Output;

import Calculation.CalculationResult;
import Calculation.MoneyBox;

import java.util.List;

public class Output {
    public void printOutput(List<CalculationResult> results) {

        String output = "";
        MoneyBox.CurrencyType currencyType = null;

        for (int i = 0; i < results.size(); i++) {
            CalculationResult currentCalculationResult = results.get(i);

            if (currencyType == null) {
                output += currentCalculationResult.getBox().getType() == MoneyBox.CurrencyType.NOTE ? "S " : "M ";
                currencyType = currentCalculationResult.getBox().getType();
            }

            if (currencyType == MoneyBox.CurrencyType.NOTE && currentCalculationResult.getBox().getType() == MoneyBox.CurrencyType.COIN) {
                output += "M ";
                currencyType = currentCalculationResult.getBox().getType();
            }

            output += currentCalculationResult.getBox().getValue() + " " + currentCalculationResult.getAmount() + " ";
        }
        System.out.println(output);
    }
}
