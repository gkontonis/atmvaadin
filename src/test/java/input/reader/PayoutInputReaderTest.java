package input.reader;

import input.dto.PayoutRequestTest;
import input.dto.PayoutRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PayoutInputReaderTest {

    @Test
    public void testGetUserinput() {
    }

    @Test
    public void testConvert_It_returns_null_when_String_is_null(){
        PayoutInputReader payoutInputReader = new PayoutInputReader();
        Assert.assertNull(payoutInputReader.convert(null));
    }

    @Test
    public void testConvert_It_returns_null_when_String_is_empty(){
        PayoutInputReader payoutInputReader = new PayoutInputReader();
        Assert.assertNull(payoutInputReader.convert(""));
    }

    @Test
    public void testConvert_It_returns_null_when_String_not_a_number(){
        PayoutInputReader payoutInputReader = new PayoutInputReader();
        Assert.assertNull(payoutInputReader.convert("ABC"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_not_a_whole_natural_number(){
        PayoutInputReader payoutInputReader = new PayoutInputReader();
        Assert.assertNull(payoutInputReader.convert("0.7"));
    }
    @Test
    public void testConvert_It_returns_null_when_String_starts_with_negative_number(){
        PayoutInputReader payoutInputReader = new PayoutInputReader();
        Assert.assertNull(payoutInputReader.convert("-1"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_doesnt_end_with_a_letter(){
        PayoutInputReader payoutInputReader = new PayoutInputReader();
        Assert.assertNull(payoutInputReader.convert("123"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_currencypart_is_not_defined_as_possible_currency(){
        PayoutInputReader payoutInputReader = new PayoutInputReader();
        String invalideCurrency = String.valueOf(PayoutRequestTest.INVALID_CURRENCY);
        Assert.assertNull(payoutInputReader.convert(invalideCurrency));
    }

    @Test
    public void testConvert_It_returns_null_when_String_numberpart_is_longer_than_eight_digits(){
        PayoutInputReader payoutInputReader = new PayoutInputReader();
        Assert.assertNull(payoutInputReader.convert("123456789"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_numberpart_is_greater_than_ten_million(){
        PayoutInputReader payoutInputReader = new PayoutInputReader();
        Assert.assertNull(payoutInputReader.convert("100000001"));
    }

    @Test
    public void testConvert_It_returns_userRequest_when_number_and_Currencypart_are_valid(){
        PayoutInputReader payoutInputReader = new PayoutInputReader();
        PayoutRequest result = payoutInputReader.convert("1234A");
        Assert.assertNotNull(result);
    }

    @Test
    public void testConvert_It_returns_userRequest_even_with_leading_spaces_when_number_and_currencypart_are_valid(){
        PayoutInputReader payoutInputReader = new PayoutInputReader();
        PayoutRequest result = payoutInputReader.convert("    1234A");
        Assert.assertNotNull(result);
    }

    @Test
    public void testConvert_It_returns_userRequest_when_firest_sign_of_currencypart_is_valid_currency(){
        PayoutInputReader payoutInputReader = new PayoutInputReader();
        PayoutRequest result = payoutInputReader.convert("1234Afoo");
        Assert.assertNotNull(result);
    }

}