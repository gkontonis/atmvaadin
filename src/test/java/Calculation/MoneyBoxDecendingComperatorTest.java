package Calculation;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class MoneyBoxDecendingComperatorTest {

    @Test
    public void testCompare_It_returns_0_when_type_and_value_are_equal() {
        MoneyBoxDecendingComperator moneyBoxDecendingComperator = new MoneyBoxDecendingComperator();
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        assertEquals(moneyBoxDecendingComperator.compare(box1, box2), 0);
    }

    @Test
    public void testCompare_It_returns_1_when_first_value_greater_second_value() {
        MoneyBoxDecendingComperator moneyBoxDecendingComperator = new MoneyBoxDecendingComperator();
        MoneyBox box1 = new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        assertEquals(moneyBoxDecendingComperator.compare(box1, box2),  MoneyBoxDecendingComperator.SMALLER);
    }

    @Test
    public void testCompare_It_returns_negative1_when_first_value_smaller_second_value() {
        MoneyBoxDecendingComperator moneyBoxDecendingComperator = new MoneyBoxDecendingComperator();
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box2 = new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        assertEquals(moneyBoxDecendingComperator.compare(box1, box2),  MoneyBoxDecendingComperator.GREATER);
    }

    @Test
    public void testCompare_It_returns_1_when_first_and_second_value_are_same_and_first_type_is_note() {
        MoneyBoxDecendingComperator moneyBoxDecendingComperator = new MoneyBoxDecendingComperator();
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        assertEquals(moneyBoxDecendingComperator.compare(box1, box2), MoneyBoxDecendingComperator.SMALLER);
    }
    @Test
    public void testCompare_It_returns_negative1_when_first_and_second_value_are_same_and_first_type_is_note() {
        MoneyBoxDecendingComperator moneyBoxDecendingComperator = new MoneyBoxDecendingComperator();
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        assertEquals(moneyBoxDecendingComperator.compare(box1, box2),  MoneyBoxDecendingComperator.GREATER);
    }

    @Test
    public void testCompare_It_sorts_a_list_with_notes_before_coins_and_bigges_value_first(){
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box2 = new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box3 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        MoneyBox box4 = new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        List<MoneyBox> boxes = new ArrayList<>(4);
        boxes.add(box1);
        boxes.add(box2);
        boxes.add(box3);
        boxes.add(box4);


        List<MoneyBox> expectedBoxes = new ArrayList<>(4);
        expectedBoxes.add(box4);
        expectedBoxes.add(box3);
        expectedBoxes.add(box2);
        expectedBoxes.add(box1);

        MoneyBoxDecendingComperator moneyBoxDecendingComperator = new MoneyBoxDecendingComperator();
        boxes.sort(moneyBoxDecendingComperator);

        Assert.assertEquals(boxes, expectedBoxes);
    }
}