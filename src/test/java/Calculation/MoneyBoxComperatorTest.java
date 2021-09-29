package Calculation;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MoneyBoxComperatorTest {

    @Test
    public void testCompare_It_returns_0_when_type_and_value_are_equal() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        assertEquals(moneyBoxComperator.compare(box1, box2), 0);
    }

    @Test
    public void testCompare_It_returns_1_when_first_value_greater_second_value() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        MoneyBox box1 = new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        assertEquals(moneyBoxComperator.compare(box1, box2), 1);
    }

    @Test
    public void testCompare_It_returns_negative1_when_first_value_smaller_second_value() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box2 = new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        assertEquals(moneyBoxComperator.compare(box1, box2), -1);
    }

    @Test
    public void testCompare_It_returns_1_when_first_and_second_value_are_same_and_first_type_is_note() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        assertEquals(moneyBoxComperator.compare(box1, box2), 1);
    }
    @Test
    public void testCompare_It_returns_negative1_when_first_and_second_value_are_same_and_first_type_is_note() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        assertEquals(moneyBoxComperator.compare(box1, box2), -1);
    }
}