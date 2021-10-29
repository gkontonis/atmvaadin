package output;

import backend.enums.Currency;
import backend.enums.CurrencyType;
import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;

import java.util.List;

@Deprecated
public class Output {
    //Karlis
    public void printContainer(MoneyBoxContainer moneyBoxContainer) {
        if (moneyBoxContainer == null){
            return;
        }

        String output;
        CurrencyType currencyType;


        for (Currency currency : moneyBoxContainer.getMap().keySet()) {
            output = "";
            currencyType = null;
            List<MoneyBox> resultList = moneyBoxContainer.get(currency);
            for (MoneyBox currentMoneyBox : resultList) {
                if (currencyType == null) {
                    output += currentMoneyBox.getType() == CurrencyType.NOTE ? "S " : "M ";
                    currencyType = currentMoneyBox.getType();
                }

                if (currencyType == CurrencyType.NOTE && currentMoneyBox.getType() == CurrencyType.COIN) {
                    output += "M ";
                    currencyType = currentMoneyBox.getType();
                }

                output += currentMoneyBox.getValue() + " " + currentMoneyBox.getAmount() + " ";
            }
            System.out.println(output);
        }
    }
    //werners
    public void printContainerWerner(MoneyBoxContainer moneyBoxContainer) {
        String output;
        CurrencyType currencyType;

        for (Currency currency : moneyBoxContainer.getMap().keySet()) {
            output = "";
            currencyType = null;
            int totalOfType = 0;
            int total = 0;
            List<MoneyBox> resultList = moneyBoxContainer.get(currency);
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
    }

    public void printErrorMsg(String errormsg){
        System.out.println(errormsg);
    }
}
