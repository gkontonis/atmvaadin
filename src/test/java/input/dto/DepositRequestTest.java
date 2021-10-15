package input.dto;

import backend.entity.Currency;
import backend.entity.CurrencyType;
import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DepositRequestTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddMoneyBox_It_throws_exception_when_value_is_negative() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.addMoneyBox(new MoneyBox(-1, Currency.A, CurrencyType.COIN));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddMoneyBox_It_throws_exception_when_currency_is_null() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.addMoneyBox(new MoneyBox(1, null, CurrencyType.COIN));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddMoneyBox_It_throws_exception_when_type_is_null() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.addMoneyBox(new MoneyBox(1, Currency.A, null));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddMoneyBox_It_is_invalid_when_value_is_zero() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.addMoneyBox(new MoneyBox(0, Currency.A, CurrencyType.COIN));
    }

    @Test
    public void testAddMoneyBox_It_valid_when_value_is_greater_than_zero() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.addMoneyBox(new MoneyBox(2, Currency.A, CurrencyType.COIN));
    }

    @Test
    public void testAddMoneyBox_It_valid_when_currency_is_valid_currency() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.addMoneyBox(new MoneyBox(1, Currency.A, CurrencyType.COIN));
    }

}