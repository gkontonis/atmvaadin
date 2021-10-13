package backend.entity;

import backend.comperator.MoneyBoxDecendingComperator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MoneyBoxContainer {
    private static MoneyBoxDecendingComperator moneyBoxDecendingComperator = new MoneyBoxDecendingComperator();
    private Map<MoneyBox.Currency, List<MoneyBox>> containerMap = new HashMap<>();


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
                // mb.setAmount(mb.getAmount() + box.getAmount());
                moneyBox = box;
                return boxes;
            }
        }
        boxes.add(box);
        return boxes;
    }

    public List<MoneyBox> get(MoneyBox.Currency currency) {
        List<MoneyBox> result = containerMap.get(currency);
        if (result == null) {
            throw new IllegalArgumentException("Passed Currency " + currency + " was not found");
        }
        result.sort(moneyBoxDecendingComperator);
        return result;
    }

    //TODO: MAP NOT SORTED & POSSIBLY ADD NEW METHOD TO RETURN List<List<MoneyBox>> which is sorted
    public Map<MoneyBox.Currency, List<MoneyBox>> getMap() {
        return containerMap;
    }

    public void depositContainer(MoneyBoxContainer source) {
        if (source == null) {
            throw new IllegalArgumentException("MoneyBoxContainer must not be null!");
        }

        for (MoneyBox.Currency currency : source.getMap().keySet()) {
            for (MoneyBox box : source.getMap().get(currency)) {
                this.deposit(box);
            }
        }
    }

    public MoneyBox withdraw(MoneyBox toWithdraw) {
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

        for (MoneyBox.Currency currency : getMap().keySet()) {
            for (MoneyBox mb : moneyBoxContainer.get(currency)) {
                if (withdraw(mb) == null) {
                    return null;
                }
            }
        }
        return moneyBoxContainer;
    }

    private int getIndex(MoneyBox moneyBox) {
        if (moneyBox == null) {
            return -1;
        }
        for (MoneyBox.Currency currency : getMap().keySet()) {
            List<MoneyBox> list = get(currency);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getValue() == moneyBox.getValue() && list.get(i).getType() == moneyBox.getType()) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean contains(MoneyBox mb) {
        return getIndex(mb) != -1;
    }

    public boolean containsAmount(MoneyBox toFind) {
        if (contains(toFind)) {
            return get(toFind.getCurrency()).get(getIndex(toFind)).getAmount() >= toFind.getAmount();
        }
        return false;
    }

    public boolean isEmpty() {
        return containerMap.isEmpty();
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
            if (box.getValue() == moneyBox.getValue() && box.getType() == moneyBox.getType()) {         //TODO could be replaced by box.equals() or comperator
                box.setAmount(box.getAmount() + moneyBox.getAmount());
                return;
            }
        }
        boxes.add(moneyBox);
    }

}
