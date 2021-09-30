package Calculation;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CalculationResultTest {

    @Test
    public void testCalculationResult_It_is_valid_when_Amount_greater_equals_zero(){
        MoneyBox validMoneyBox = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        CalculationResult calculationResult = new CalculationResult(0, validMoneyBox, MoneyBox.Currency.A);
        Assert.assertNotNull(calculationResult);
    }

    @Test
    public void testCalculationResult_It_is_valid_when_MoneyBox_is_not_null(){
        MoneyBox validMoneyBox = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        CalculationResult calculationResult = new CalculationResult(0, validMoneyBox, MoneyBox.Currency.A);
        Assert.assertNotNull(calculationResult);
    }

    @Test
    public void testCalculationResult_It_is_valid_when_currency_not_null(){
        MoneyBox validMoneyBox = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        CalculationResult calculationResult = new CalculationResult(0, validMoneyBox, MoneyBox.Currency.A);
        Assert.assertNotNull(calculationResult);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculationResult_It_throw_exception_when_amount_smaller_zero(){
        MoneyBox validMoneyBox = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        new CalculationResult(-1, validMoneyBox, MoneyBox.Currency.A);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculationResult_It_throw_exception_when_MoneyBox_is_null(){
        MoneyBox invalidMoneyBox = null;
        new CalculationResult(0, invalidMoneyBox, MoneyBox.Currency.A);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculationResult_It_throw_exception_when_currency_is_null(){
        MoneyBox validMoneyBox = new MoneyBox(1, MoneyBox.Currency.A, null);
        new CalculationResult(0, validMoneyBox, MoneyBox.Currency.A);
    }


    @Test
    public void testGetAmount_It_is_amount_when_amount_was_valid() {
        MoneyBox validMoneyBox = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        CalculationResult calculationResult = new CalculationResult(1, validMoneyBox, MoneyBox.Currency.A);
        Assert.assertEquals(calculationResult.getAmount(), 1);
    }

    @Test
    public void testGetBox_It_is_box_when_box_was_valid() {
        MoneyBox validMoneyBox = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        CalculationResult calculationResult = new CalculationResult(0, validMoneyBox, MoneyBox.Currency.A);
        Assert.assertEquals(calculationResult.getBox(), validMoneyBox);
    }

    @Test
    public void testGetCurrency_It_is_currency_when_currency_was_valid() {
        MoneyBox validMoneyBox = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        CalculationResult calculationResult = new CalculationResult(0, validMoneyBox, MoneyBox.Currency.A);
        Assert.assertEquals(calculationResult.getCurrency(), MoneyBox.Currency.A);
    }
}