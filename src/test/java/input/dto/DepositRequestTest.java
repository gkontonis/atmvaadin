package input.dto;

import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DepositRequestTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddMoneyBox_It_throws_exception_when_value_is_negative() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.addMoneyBox(new MoneyBox(-1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddMoneyBox_It_throws_exception_when_currency_is_null() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.addMoneyBox(new MoneyBox(1, null, MoneyBox.CurrencyType.COIN));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddMoneyBox_It_throws_exception_when_type_is_null() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.addMoneyBox(new MoneyBox(1, MoneyBox.Currency.A, null));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddMoneyBox_It_is_invalid_when_value_is_zero() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.addMoneyBox(new MoneyBox(0, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
    }

    @Test
    public void testAddMoneyBox_It_valid_when_value_is_greater_than_zero() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.addMoneyBox(new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
    }

    @Test
    public void testAddMoneyBox_It_valid_when_currency_is_valid_currency() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.addMoneyBox(new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
    }

}