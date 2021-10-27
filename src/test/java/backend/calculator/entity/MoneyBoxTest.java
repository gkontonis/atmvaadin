package backend.calculator.entity;

import backend.entity.Currency;
import backend.entity.CurrencyType;
import backend.entity.MoneyBox;
import org.testng.Assert;
import org.testng.annotations.Test;
//import sun.misc.ASCIICaseInsensitiveComparator;

public class MoneyBoxTest {
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMoneyBox_It_throws_exception_when_value_is_smaller_zero() {

        new MoneyBox(-1, Currency.A, CurrencyType.COIN);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMoneyBox_It_throws_exception_when_value_is_euqals_zero() {
        new MoneyBox(0, Currency.A, CurrencyType.COIN);
    }

    @Test
    public void testMoneyBox_It_does_NOT_throw_exception_when_value_is_ONE() {
        Assert.assertNotNull(new MoneyBox(1, Currency.A, CurrencyType.COIN));
    }

    @Test
    public void testMoneyBox_It_does_NOT_throw_exception_when_value_is_TWO() {
        Assert.assertNotNull(new MoneyBox(2, Currency.A, CurrencyType.COIN));
    }

    @Test
    public void testMoneyBox_It_is_valid_when_value_is_power_of_FIVE() {
        Assert.assertNotNull(new MoneyBox(5, Currency.A, CurrencyType.NOTE));
    }

    @Test
    public void testMoneyBox_It_is_amount_zero_when_amount_was_not_passed() {
        MoneyBox moneyBox = new MoneyBox(5, Currency.A, CurrencyType.NOTE);
        Assert.assertEquals(moneyBox.getAmount(), 0);
    }

    @Test
    public void testMoneyBox_It_is_valid_when_value_is_power_of_TEN() {
        Assert.assertNotNull(new MoneyBox(10, Currency.A, CurrencyType.NOTE));
    }

    @Test
    public void testMoneyBox_It_does_throw_exception_when_value_is_not_1_2_power_of_five_power_of_ten() {
        int[] invalidValues = {3, 4, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 18, 19, 21};
        int currentValue = 0;
        for (int v : invalidValues) {
            currentValue = v;
            try {
                new MoneyBox(v, Currency.A, CurrencyType.NOTE);
            } catch (IllegalArgumentException e) {
                continue;
            }
            throw new IllegalStateException("Value " + currentValue + " shouldn not be allowed.");
        }
        Assert.assertNotNull(new MoneyBox(1, Currency.A, CurrencyType.COIN));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMoneyBox_It_throws_exception_when_Currency_is_euqals_null() {
        Assert.assertNotNull(new MoneyBox(10, null, CurrencyType.NOTE));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMoneyBox_It_throws_exception_when_Currency_type_is_euqals_null() {
        Assert.assertNotNull(new MoneyBox(10, Currency.A, null));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMoneyBox_It_is_invalid_when_amount_less_than_zero() {
        new MoneyBox(1, Currency.A, CurrencyType.COIN, -1);
    }

    @Test
    public void testMoneyBox_It_is_valid_when_amount_is_zero() {
        MoneyBox moneyBox = new MoneyBox(1, Currency.A, CurrencyType.COIN, 0);
        Assert.assertEquals(moneyBox.getAmount(), 0);
    }

    @Test
    public void testMoneyBox_It_is_valid_when_amount_is_greater_zero() {
        MoneyBox moneyBox = new MoneyBox(1, Currency.A, CurrencyType.COIN, 2);
        Assert.assertEquals(moneyBox.getAmount(), 2);
    }

    @Test
    public void testMoneyBox_It_is_amount_when_amount_was_passed() {
        MoneyBox moneyBox = new MoneyBox(5, Currency.A, CurrencyType.NOTE, 5);
        Assert.assertEquals(moneyBox.getAmount(), 5);
    }
    //----------------------------------------------------------------------------------------------------------------


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeposit_Is_invalid_when_amount_is_less_than_zero() {
        new MoneyBox(1, Currency.A, CurrencyType.COIN, -1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeposit_Is_invalid_when_amount_is_bigger_than_IntegerMAX() {
        MoneyBox moneyBox = new MoneyBox(1, Currency.A, CurrencyType.COIN, Integer.MAX_VALUE);
        moneyBox.deposit(1);
    }

    @Test
    public void testDeposit_Is_valid_when_amount_is_zero() {
        MoneyBox moneyBox = new MoneyBox(1, Currency.A, CurrencyType.COIN, 5);
        int deposition = moneyBox.deposit(0);
        Assert.assertEquals(deposition, moneyBox.getAmount());
    }

    @Test
    public void testDeposit_Is_valid_when_amount_is_greater_zero() {
        MoneyBox moneyBox = new MoneyBox(1, Currency.A, CurrencyType.COIN, 1);
        int depositedAmount = moneyBox.deposit(2);
        Assert.assertEquals(depositedAmount, 3);
    }

    @Test
    public void testPayOut_Is_Valid_when_amount_is_zero() {
        MoneyBox moneyBox = new MoneyBox(1, Currency.A, CurrencyType.COIN, 1);
        moneyBox.payout(0);
    }

    @Test
    public void testPayOut_Is_Valid_when_amount_is_greater_zero() {
        MoneyBox moneyBox = new MoneyBox(1, Currency.A, CurrencyType.COIN, 1);
        moneyBox.payout(1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testPayOut_Is_invalid_when_amount_is_less_than_zero() {
        MoneyBox moneyBox = new MoneyBox(1, Currency.A, CurrencyType.COIN, 0);
        moneyBox.payout(-1);
    }

    @Test
    public void testPayOut_Is_existing_amount_when_passed_amount_is_greater_than_existing_amount() {
        MoneyBox moneyBox = new MoneyBox(1, Currency.A, CurrencyType.COIN, 5);
        int payout = moneyBox.payout(11);
        Assert.assertEquals(payout, 5);
    }

    @Test
    public void testPayOut_Is_existing_amount_is_zero_when_amount_is_greater_than_existing_amount() {
        MoneyBox moneyBox = new MoneyBox(1, Currency.A, CurrencyType.COIN, 5);
        moneyBox.payout(11);
        Assert.assertEquals(moneyBox.getAmount(), 0);
    }

    //----------------------------------------------------------------------------------------------------------------

    @Test
    public void testGetValue_is_valid_if_Value_is_valid() {
        MoneyBox moneyBox = new MoneyBox(10, Currency.A, CurrencyType.NOTE);
        Assert.assertEquals(moneyBox.getValue(), 10);
    }

    @Test
    public void testGetCurrency_is_valid_if_Currency_is_valid() {
        MoneyBox moneyBox = new MoneyBox(10, Currency.A, CurrencyType.NOTE);
        Assert.assertEquals(moneyBox.getCurrency(), Currency.A);
    }

    @Test
    public void testGetType_is_valid_if_Type_is_valid() {
        MoneyBox moneyBox = new MoneyBox(10, Currency.A, CurrencyType.NOTE);
        Assert.assertEquals(moneyBox.getType(), CurrencyType.NOTE);
    }

    //----------------------------------------------------------------------------------------------------------------

   // @Test
   // public void testValidateCurrency_Currency_Is_valid(){
   //     MoneyBox moneyBox = new MoneyBox(10, Currency.A, CurrencyType.NOTE);
   //     Assert.assertTrue(moneyBox.validateCurrency(moneyBox.getCurrency()));
   // }
//
   // @Test
   // public void testValidateCurrency_Currency_Is_valid_B(){
   //     MoneyBox moneyBox = new MoneyBox(10, Currency.B, CurrencyType.NOTE);
   //     Assert.assertTrue(moneyBox.validateCurrency(moneyBox.getCurrency()));
   // }
}