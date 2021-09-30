package Calculation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MoneyBoxContainer {
    private static MoneyBoxDecendingComperator moneyBoxDecendingComperator = new MoneyBoxDecendingComperator();
    private Map<MoneyBox.Currency, List<MoneyBox>> container = new HashMap<>();


    public List<MoneyBox> add(MoneyBox box){

        if(box == null){
            throw new IllegalArgumentException("Passed Box must not be null!");
        }

        List<MoneyBox> boxes = container.get(box.getCurrency());
        if(boxes==null){
            boxes = new LinkedList<>();
            container.put(box.getCurrency(), boxes);
        }
        boxes.add(box);

        return boxes;
    }

    public List<MoneyBox> get(MoneyBox.Currency currency){

        List<MoneyBox> result = container.get(currency);

        if(result == null){
            throw new IllegalArgumentException("Passed Currency " + currency + " was not found");

        }

        result.sort(moneyBoxDecendingComperator);

        return result;
    }
}
