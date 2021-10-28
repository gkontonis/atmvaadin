package input.dto;

import backend.entity.Currency;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleRequestTest {

    @Test
    public void testGetCurrencyType_It_is_the_set_currency_type() {
        SimpleRequest simpleRequest = new SimpleRequest(Currency.A, 3543);
        Assert.assertEquals(simpleRequest.getCurrency(), Currency.A);
    }

    @Test
    public void testGetCurrencyType_It_doesnt_throw_exception_when_currencytype_is_defined_in_Possible_currencies() {
        SimpleRequest simpleRequest = new SimpleRequest(Currency.A, 3543);
        Assert.assertNotNull(simpleRequest);
    }

    @Test
    public void testGetCurrencyValue_It_is_the_set_currency_value() {
        SimpleRequest simpleRequest = new SimpleRequest(Currency.A, 3543);
        Assert.assertEquals(simpleRequest.getValue(), 3543);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetCurrencyValue_It_throws_exception_when_currency_type_is_smaller_zero() {
        new SimpleRequest(Currency.A, -1);
    }


    @Test
    public void testIsCurrencyValueValid_It_is_true_when_currencyvalue_greater_equals_zero(){
        Assert.assertTrue(SimpleRequest.isCurrencyValueValid(0));
    }
    @Test
    public void testIsCurrencyValueValid_It_is_false_when_currencyvalue_smaller_zero(){
        Assert.assertFalse(SimpleRequest.isCurrencyValueValid(-1));
    }


}