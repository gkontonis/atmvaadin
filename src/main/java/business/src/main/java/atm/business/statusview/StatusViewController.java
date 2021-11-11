package business.src.main.java.atm.business.statusview;

import backend.calculator.Calculator;
import backend.entity.MoneyBox;
import backend.entity.MoneyBoxItem;
import backend.enums.Currency;
import backend.transformer.MoneyBoxItemTransformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StatusViewController {

    private Calculator CALCULATOR = new Calculator();

    public List<MoneyBox> getStatus() {
        List<MoneyBox> resultList = null;
        for (Currency currency : CALCULATOR.getContainer().getMap().keySet()) {
            resultList = CALCULATOR.getContainer().get(currency);

        }
        return resultList;
    }

    public List<MoneyBoxItem> getMoneyBoxItems() {
        Collection<List<MoneyBox>> moneyBoxLists = CALCULATOR.getContainer().getMap().values();
        List<MoneyBoxItem> resultContainers = new ArrayList<>();

        for (List<MoneyBox> moneyBoxList : moneyBoxLists) {
            resultContainers.addAll(MoneyBoxItemTransformer.transform(moneyBoxList));
        }
        return resultContainers;
    }


}
