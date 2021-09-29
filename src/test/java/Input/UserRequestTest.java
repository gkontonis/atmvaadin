package Input;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRequestTest {

    public final static char INVALID_CURRENCY = '-';
    @Test
    public void testGetCurrencyType_It_is_the_set_currency_type() {
        UserRequest userRequest = new UserRequest('A', 3543);
        Assert.assertEquals(userRequest.getCurrencyType(), 'A');
    }

    @Test
    public void testGetCurrencyType_It_doesnt_thorw_exception_when_currencytype_is_defined_in_Possible_currencies() {
        UserRequest userRequest = new UserRequest('A', 3543);
        Assert.assertNotNull(userRequest);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetCurrencyType_It_throws_exception_when_currencytype_is_not_defined_in_Possible_currencies() {
        new UserRequest(INVALID_CURRENCY, 3543);
    }

    @Test
    public void testGetCurrencyValue_It_is_the_set_currency_value() {
        UserRequest userRequest = new UserRequest('A', 3543);
        Assert.assertEquals(userRequest.getCurrencyValue(), 3543);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetCurrencyValue_It_throws_exception_when_currency_type_is_smaller_zero() {
        new UserRequest('A', -1);
    }


    @Test
    public void testIsCurrencyValid_It_is_true_when_currency_was_defined_in_possible_currencies() {
        for (Character currency : UserRequest.POSSIBLE_CURRENCIES) {
            Assert.assertTrue(UserRequest.isCurrencyTypeValid(currency));
        }
    }

    @Test
    public void testIsCurrencyValid_It_is_false_when_currency_was_not_defined_in_possible_currencies() {
        Assert.assertFalse(UserRequest.isCurrencyTypeValid(new Character(INVALID_CURRENCY)));
    }

    @Test
    public void testIsCurrencyValueValid_It_is_true_when_currencyvalue_greater_equals_zero(){
        Assert.assertTrue(UserRequest.isCurrencyValueValid(0));
    }
    @Test
    public void testIsCurrencyValueValid_It_is_false_when_currencyvalue_smaller_zero(){
        Assert.assertFalse(UserRequest.isCurrencyValueValid(-1));
    }


}