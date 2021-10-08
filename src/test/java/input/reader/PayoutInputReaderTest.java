package input.reader;

import input.dto.PayoutRequestTest;
import input.dto.PayoutRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PayoutInputReaderTest {

    @Test
    public void testConvert_It_returns_null_when_String_is_null(){
        PayoutInputReader inputReader = new PayoutInputReader();
        Assert.assertNull(inputReader.convert(null));
    }

    @Test
    public void testConvert_It_returns_null_when_String_is_empty(){
        PayoutInputReader inputReader = new PayoutInputReader();
        Assert.assertNull(inputReader.convert(""));
    }

    @Test
    public void testConvert_It_returns_null_when_String_not_a_number(){
        PayoutInputReader inputReader = new PayoutInputReader();
        Assert.assertNull(inputReader.convert("ABC"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_starts_with_0(){
        PayoutInputReader inputReader = new PayoutInputReader();
        Assert.assertNull(inputReader.convert("0B"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_not_a_whole_natural_number(){
        PayoutInputReader inputReader = new PayoutInputReader();
        Assert.assertNull(inputReader.convert("1.7"));
    }
    @Test
    public void testConvert_It_returns_null_when_String_starts_with_negative_number(){
        PayoutInputReader inputReaderr = new PayoutInputReader();
        Assert.assertNull(inputReaderr.convert("-1"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_doesnt_end_with_a_letter(){
        PayoutInputReader inputReader = new PayoutInputReader();
        Assert.assertNull(inputReader.convert("123"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_currencypart_is_not_defined_as_possible_currency(){
        PayoutInputReader inputReader = new PayoutInputReader();
        String invalideCurrency = String.valueOf(PayoutRequestTest.INVALID_CURRENCY);
        Assert.assertNull(inputReader.convert(invalideCurrency));
    }

    @Test
    public void testConvert_It_returns_null_when_String_numberpart_is_longer_than_eight_digits(){
        PayoutInputReader inputReader = new PayoutInputReader();
        Assert.assertNull(inputReader.convert("123456789"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_numberpart_is_greater_than_ten_million(){
        PayoutInputReader inputReader = new PayoutInputReader();
        Assert.assertNull(inputReader.convert("100000001"));
    }

    @Test
    public void testConvert_It_returns_userRequest_when_number_and_Currencypart_are_valid(){
        PayoutInputReader inputReader = new PayoutInputReader();
        PayoutRequest result = inputReader.convert("1234A");
        Assert.assertNotNull(result);
    }

    @Test
    public void testConvert_It_returns_userRequest_even_with_leading_spaces_when_number_and_currencypart_are_valid(){
        PayoutInputReader inputReader = new PayoutInputReader();
        PayoutRequest result = inputReader.convert("    1234A");
        Assert.assertNotNull(result);
    }

    @Test
    public void testConvert_It_returns_userRequest_when_firest_sign_of_currencypart_is_valid_currency(){
        PayoutInputReader inputReader = new PayoutInputReader();
        PayoutRequest result = inputReader.convert("1234Afoo");
        Assert.assertNotNull(result);
    }

}