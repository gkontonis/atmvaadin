package input.dto;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PayoutRequestTest {

    public final static char INVALID_CURRENCY = '-';
    @Test
    public void testGetCurrencyType_It_is_the_set_currency_type() {
        PayoutRequest payoutRequest = new PayoutRequest('A', 3543);
        Assert.assertEquals(payoutRequest.getCurrency(), 'A');
    }

    @Test
    public void testGetCurrencyType_It_doesnt_throw_exception_when_currencytype_is_defined_in_Possible_currencies() {
        PayoutRequest payoutRequest = new PayoutRequest('A', 3543);
        Assert.assertNotNull(payoutRequest);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetCurrencyType_It_throws_exception_when_currencytype_is_not_defined_in_Possible_currencies() {
        new PayoutRequest(INVALID_CURRENCY, 3543);
    }

    @Test
    public void testGetCurrencyValue_It_is_the_set_currency_value() {
        PayoutRequest payoutRequest = new PayoutRequest('A', 3543);
        Assert.assertEquals(payoutRequest.getValue(), 3543);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetCurrencyValue_It_throws_exception_when_currency_type_is_smaller_zero() {
        new PayoutRequest('A', -1);
    }


    @Test
    public void testIsCurrencyValid_It_is_true_when_currency_was_defined_in_possible_currencies() {
        for (Character currency : PayoutRequest.POSSIBLE_CURRENCIES) {
            Assert.assertTrue(PayoutRequest.isCurrencyTypeValid(currency));
        }
    }

    @Test
    public void testIsCurrencyValid_It_is_false_when_currency_was_not_defined_in_possible_currencies() {
        Assert.assertFalse(PayoutRequest.isCurrencyTypeValid(new Character(INVALID_CURRENCY)));
    }

    @Test
    public void testIsCurrencyValueValid_It_is_true_when_currencyvalue_greater_equals_zero(){
        Assert.assertTrue(PayoutRequest.isCurrencyValueValid(0));
    }
    @Test
    public void testIsCurrencyValueValid_It_is_false_when_currencyvalue_smaller_zero(){
        Assert.assertFalse(PayoutRequest.isCurrencyValueValid(-1));
    }


}