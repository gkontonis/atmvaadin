package backend.entity;

import backend.comperator.MoneyBoxDecendingComperator;
import backend.enums.Currency;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MoneyBoxContainer {
    private static MoneyBoxDecendingComperator moneyBoxDecendingComperator = new MoneyBoxDecendingComperator();
    private Map<Currency, List<MoneyBox>> containerMap = new HashMap<>();

    public List<MoneyBox> put(MoneyBox box) {
        if (box == null) {
            throw new IllegalArgumentException("Passed Box must not be null!");
        }

        List<MoneyBox> boxes = containerMap.get(box.getCurrency());
        if (boxes == null) {
            boxes = new LinkedList<>();
            containerMap.put(box.getCurrency(), boxes);
        }

        for (MoneyBox moneyBox : boxes) {
            if (moneyBox.getValue() == box.getValue() && moneyBox.getType() == box.getType()) {
                System.out.println("Box of equal value found");
                return boxes;
            }
        }
        boxes.add(box);
        return boxes;
    }

    public List<MoneyBox> get(Currency currency) {
        List<MoneyBox> result = containerMap.get(currency);
        if (result == null) {
            throw new IllegalArgumentException("WÄHRUNG: " + currency + "NICHT GEFUNDEN ");
        }
        result.sort(moneyBoxDecendingComperator);
        return result;
    }

    public Map<Currency, List<MoneyBox>> getMap() {
        return containerMap;
    }

    public void depositContainer(MoneyBoxContainer source) {
        if (source == null) {
            throw new IllegalArgumentException("MoneyBoxContainer must not be null!");
        }

        for (Currency currency : source.getMap().keySet()) {
            for (MoneyBox box : source.getMap().get(currency)) {
                this.deposit(box);
            }
        }
    }

    public void deposit(MoneyBox moneyBox) {
        if (moneyBox == null) {
            throw new IllegalArgumentException("Passed Box must not be null!");
        }
        List<MoneyBox> boxes = containerMap.get(moneyBox.getCurrency());
        if (boxes == null || boxes.isEmpty()) {
            boxes = new LinkedList<>();
            containerMap.put(moneyBox.getCurrency(), boxes);
        }

        for (MoneyBox box : containerMap.get(moneyBox.getCurrency())) {
            if (box.getValue() == moneyBox.getValue() && box.getType() == moneyBox.getType()) {
                box.setAmount(box.getAmount() + moneyBox.getAmount());
                return;
            }
        }
        boxes.add(moneyBox);
    }

    public MoneyBox withdraw(MoneyBox toWithdraw) {     //TODO: Does this need to be public?
        if (toWithdraw == null) {
            throw new IllegalArgumentException("MoneyBox must not be null!");
        }

        if (containsAmount(toWithdraw)) {
            MoneyBox toChange = containerMap.get(toWithdraw.getCurrency()).get(getIndex(toWithdraw));
            toChange.setAmount(toChange.getAmount() - toWithdraw.getAmount());
            return toWithdraw;
        }
        return null;
    }

    public MoneyBoxContainer withdraw(MoneyBoxContainer moneyBoxContainer) {
        if (moneyBoxContainer == null) {
            throw new IllegalArgumentException("MoneyBox must not be null!");
        }

        for (Currency currency : moneyBoxContainer.getMap().keySet()) {
            for (MoneyBox mb : moneyBoxContainer.get(currency)) {
                if (withdraw(mb) == null) {
                    //return null;
                    throw new IllegalArgumentException("MBContainer musst not be null!");
                }
            }
        }
        return moneyBoxContainer;
    }

    private int getIndex(MoneyBox moneyBox) {
        if (moneyBox == null) {
            return -1;
        }
        List<MoneyBox> list = get(moneyBox.getCurrency());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue() == moneyBox.getValue() && list.get(i).getType() == moneyBox.getType()) {
                return i;
            }
        }
        return -1;
    }

    public boolean containsAmount(MoneyBox toFind) {
        if (contains(toFind)) {
            return get(toFind.getCurrency()).get(getIndex(toFind)).getAmount() >= toFind.getAmount();
        }
        return false;
    }

    public boolean contains(MoneyBox mb) {
        return getIndex(mb) != -1;
    }

    public boolean isEmpty() {
        return containerMap.isEmpty();
    }

}
