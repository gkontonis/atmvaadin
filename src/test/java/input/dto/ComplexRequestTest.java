package input.dto;

import backend.enums.Currency;
import backend.enums.CurrencyType;
import backend.entity.MoneyBox;
import business.src.main.java.atm.business.requests.ComplexRequest;
import org.testng.annotations.Test;

public class ComplexRequestTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddMoneyBox_It_throws_exception_when_value_is_negative() {
        ComplexRequest complexRequest = new ComplexRequest();
        complexRequest.addMoneyBox(new MoneyBox(-1, Currency.A, CurrencyType.COIN));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddMoneyBox_It_throws_exception_when_currency_is_null() {
        ComplexRequest complexRequest = new ComplexRequest();
        complexRequest.addMoneyBox(new MoneyBox(1, null, CurrencyType.COIN));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddMoneyBox_It_throws_exception_when_type_is_null() {
        ComplexRequest complexRequest = new ComplexRequest();
        complexRequest.addMoneyBox(new MoneyBox(1, Currency.A, null));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddMoneyBox_It_is_invalid_when_value_is_zero() {
        ComplexRequest complexRequest = new ComplexRequest();
        complexRequest.addMoneyBox(new MoneyBox(0, Currency.A, CurrencyType.COIN));
    }

    @Test
    public void testAddMoneyBox_It_valid_when_value_is_greater_than_zero() {
        ComplexRequest complexRequest = new ComplexRequest();
        complexRequest.addMoneyBox(new MoneyBox(2, Currency.A, CurrencyType.COIN));
    }

    @Test
    public void testAddMoneyBox_It_valid_when_currency_is_valid_currency() {
        ComplexRequest complexRequest = new ComplexRequest();
        complexRequest.addMoneyBox(new MoneyBox(1, Currency.A, CurrencyType.COIN));
    }

}