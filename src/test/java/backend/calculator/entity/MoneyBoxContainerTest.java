package backend.calculator.entity;

import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MoneyBoxContainerTest {
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testPut_It_throws_exception_when_passed_moneybox_is_null() {
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();
        moneyBoxContainer.put(null);
    }

    @Test
    public void testPut_It_contains_a_box_when_valid_box_is_passed() {
        MoneyBoxContainer container = new MoneyBoxContainer();
        MoneyBox box = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        List<MoneyBox> result = container.put(box);
        Assert.assertTrue(result.contains(box));
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGet_It_throws_exception_if_currency_is_not_part_of_container() {
        MoneyBoxContainer boxContainer = new MoneyBoxContainer();
        boxContainer.get(MoneyBox.Currency.A);
    }

    @Test
    public void testGet_It_returns_list_of_boxes_when_boxes_for_currency_exist() {
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();
        MoneyBox box = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        moneyBoxContainer.put(box);
        List<MoneyBox> result = moneyBoxContainer.get(box.getCurrency());
        Assert.assertTrue(result.contains(box));
    }

    @Test
    public void testGet_It_returns_sorted_list_of_boxes_when_boxes_for_currency_exist() {
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();
        MoneyBox box1 = new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
        MoneyBox box3 = new MoneyBox(10, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        MoneyBox box4 = new MoneyBox(5, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        moneyBoxContainer.put(box1);
        moneyBoxContainer.put(box2);
        moneyBoxContainer.put(box3);
        moneyBoxContainer.put(box4);

        List<MoneyBox> expectedList = new ArrayList<>(4);
        expectedList.add(box3);
        expectedList.add(box4);
        expectedList.add(box1);
        expectedList.add(box2);

        List<MoneyBox> result = moneyBoxContainer.get(box1.getCurrency());
        Assert.assertEquals(result, expectedList);
    }

    @Test
    public void testDeposit_It_throws_exception_when_container_is_null() {

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeposit_It_throws_exception_when_moneybox_is_null() {
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();
        MoneyBox moneyBox = null;
        moneyBoxContainer.deposit(moneyBox);
    }

    @Test
    public void testDeposit_Amounts_get_increased_if_moneybox_of_same_currency_and_value_already_exists() {
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();
        MoneyBox existingMoneyBox = new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN, 1);
        MoneyBox newMoneyBox = new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN, 2);
        moneyBoxContainer.put(existingMoneyBox);
        moneyBoxContainer.deposit(newMoneyBox);

        Assert.assertEquals(moneyBoxContainer.get(MoneyBox.Currency.A).get(0).getAmount(),3);
    }

    @Test
    public void testDeposit_It_adds_new_moneybox_if_moneybox_is_unregistred() {
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();
        MoneyBox newMoneyBox = new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN, 2);
        moneyBoxContainer.deposit(newMoneyBox);

        Assert.assertNotNull(moneyBoxContainer.get(MoneyBox.Currency.A));
    }
}