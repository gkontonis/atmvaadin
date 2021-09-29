package Calculation;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class MoneyBoxContainerTest {
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAdd_It_throws_exception_when_passed_moneybox_is_null() {
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();
        moneyBoxContainer.add(null);
    }

    @Test
    public void testAdd_It_contains_a_box_when_valid_box_is_passed(){
        MoneyBoxContainer container = new MoneyBoxContainer();
        MoneyBox box = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        List<MoneyBox> result = container.add(box);
        Assert.assertTrue(result.contains(box));
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGet_It_throws_exception_if_currency_is_not_part_of_container(){
        MoneyBoxContainer boxContainer = new MoneyBoxContainer();
        boxContainer.get(MoneyBox.Currency.A);
    }

    @Test
    public void testGet_It_returns_list_of_boxes_when_boxes_for_currency_exist(){
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();
        MoneyBox box = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        moneyBoxContainer.add(box);
        List<MoneyBox> result = moneyBoxContainer.get(box.getCurrency());
        Assert.assertTrue(result.contains(box));
    }

    @Test
    public void testGet_It_returns_sorted_list_of_boxes_when_boxes_for_currency_exist(){
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();
        MoneyBox box1 = new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box3 = new MoneyBox(10, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        MoneyBox box4 = new MoneyBox(5, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        moneyBoxContainer.add(box1);
        moneyBoxContainer.add(box2);
        moneyBoxContainer.add(box3);
        moneyBoxContainer.add(box4);

        List<MoneyBox> expectedList = new ArrayList<>(4);
        expectedList.add(box3);
        expectedList.add(box4);
        expectedList.add(box1);
        expectedList.add(box2);

        List<MoneyBox> result = moneyBoxContainer.get(box1.getCurrency());
        Assert.assertEquals(result, expectedList);
    }
}