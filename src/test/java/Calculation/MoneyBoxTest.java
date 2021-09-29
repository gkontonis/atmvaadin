package Calculation;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MoneyBoxTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMoneyBox_It_throws_exception_when_value_is_smaller_zero(){
        new MoneyBox(-1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMoneyBox_It_throws_exception_when_value_is_euqals_zero(){
        new MoneyBox(0, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN);
    }

    @Test
    public void testMoneyBox_It_does_NOT_throw_exception_when_value_is_ONE(){
        Assert.assertNotNull(new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
    }

    @Test
    public void testMoneyBox_It_does_NOT_throw_exception_when_value_is_TWO(){
        Assert.assertNotNull(new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
    }

    @Test
    public void testMoneyBox_It_is_valid_when_value_is_power_of_FIVE(){
       Assert.assertNotNull(new MoneyBox(5, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
    }

    @Test
    public void testMoneyBox_It_is_valid_when_value_is_power_of_TEN(){
        Assert.assertNotNull(new MoneyBox(10, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
    }

    @Test
    public void testMoneyBox_It_does_throw_exception_when_value_is_not_1_2_power_of_five_power_of_ten(){
        int[] invalideValues = {3, 4, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 18, 19, 21};
        int currentValue = 0;
        for (int v: invalideValues) {
            currentValue = v;
            try{
                new MoneyBox(v, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
            }
            catch(IllegalArgumentException e){
                continue;
            }
            throw new IllegalStateException("Value " + currentValue + " shouldn not be allowed.");
        }
        Assert.assertNotNull(new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
    }





    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMoneyBox_It_throws_exception_when_Currency_is_euqals_null(){
        Assert.assertNotNull(new MoneyBox(10, null, MoneyBox.CurrencyType.NOTE));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMoneyBox_It_throws_exception_when_Currency_type_is_euqals_null(){
        Assert.assertNotNull(new MoneyBox(10, MoneyBox.Currency.A, null));
    }


    @Test
    public void testGetValue_is_valid_if_Value_is_valid() {
        MoneyBox mb = new MoneyBox(10, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        Assert.assertEquals(mb.getValue(), 10);
    }

    @Test
    public void testGetCurrency_is_valid_if_Currency_is_valid() {
        MoneyBox mb = new MoneyBox(10, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        Assert.assertEquals(mb.getCurrency(), MoneyBox.Currency.A);
    }

    @Test
    public void testGetType_is_valid_if_Type_is_valid() {
        MoneyBox mb = new MoneyBox(10, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE);
        Assert.assertEquals(mb.getType(), MoneyBox.CurrencyType.NOTE);
    }
}