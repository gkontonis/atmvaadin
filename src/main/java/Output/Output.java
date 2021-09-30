package Output;

import Calculation.CalculationResult;
import Calculation.MoneyBox;

import java.util.List;

public class Output {
    public void printOutput(List<CalculationResult> results) {

        String output = "";
        MoneyBox.CurrencyType currencyType = null;

        for (int i = 0; i < results.size(); i++) {
            CalculationResult currentCalculationresult = results.get(i);

            if (currencyType == null) {
                output += currentCalculationresult.getBox().getType() == MoneyBox.CurrencyType.NOTE ? "S " : "M ";
                currencyType = currentCalculationresult.getBox().getType();
            }

            if (currencyType == MoneyBox.CurrencyType.NOTE && currentCalculationresult.getBox().getType() == MoneyBox.CurrencyType.COIN) {
                output += "M ";
                currencyType = currentCalculationresult.getBox().getType();
            }

            output += currentCalculationresult.getBox().getValue() + " " + currentCalculationresult.getAmount() + " ";
        }
        System.out.println(output);
    }
}
