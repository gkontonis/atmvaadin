package backend.entity;

import backend.comperator.MoneyBoxDecendingComperator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MoneyBoxContainer {
    private static MoneyBoxDecendingComperator moneyBoxDecendingComperator = new MoneyBoxDecendingComperator();
    private Map<MoneyBox.Currency, List<MoneyBox>> container = new HashMap<>();


    public List<MoneyBox> add(MoneyBox box) {

        if (box == null) {
            throw new IllegalArgumentException("Passed Box must not be null!");
        }
        List<MoneyBox> boxes = container.get(box.getCurrency());
        if (boxes == null) {
            boxes = new LinkedList<>();
            container.put(box.getCurrency(), boxes);
        }

        boxes.add(box);

        return boxes;
    }

    public List<MoneyBox> get(MoneyBox.Currency currency) {

        List<MoneyBox> result = container.get(currency);

        if (result == null) {
            throw new IllegalArgumentException("Passed Currency " + currency + " was not found");
        }

        result.sort(moneyBoxDecendingComperator);

        return result;
    }

    //TODO: Specification and Implementation
    public void deposit(MoneyBoxContainer container) {
        throw new IllegalStateException("Not yet implemented");
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }

    public List<MoneyBox> addCombineDuplicates(MoneyBox moneyBox) {
        if (moneyBox == null) {
            throw new IllegalArgumentException("Passed Box must not be null!");
        }
        List<MoneyBox> boxes = container.get(moneyBox.getCurrency());
        if (boxes == null) {
            boxes = new LinkedList<>();
            container.put(moneyBox.getCurrency(), boxes);
        }

        //TODO Able to do better / more beaufiul code
        for (MoneyBox mb : container.get(moneyBox.getCurrency())) {
            if (mb.getValue() == moneyBox.getValue() && mb.getType() == moneyBox.getType()) {
                mb.setAmount(mb.getAmount() + moneyBox.getAmount());
                return boxes;
            }
        }
        boxes.add(moneyBox);

        return boxes;
    }

}
