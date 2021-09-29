package Input;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserInputReaderTest {

    @Test
    public void testGetUserinput() {
    }

    @Test
    public void testConvert_It_returns_null_when_String_is_null(){
        UserInputReader userInputReader = new UserInputReader();
        Assert.assertNull(userInputReader.convert(null));
    }

    @Test
    public void testConvert_It_returns_null_when_String_is_empty(){
        UserInputReader userInputReader = new UserInputReader();
        Assert.assertNull(userInputReader.convert(""));
    }

    @Test
    public void testConvert_It_returns_null_when_String_not_a_number(){
        UserInputReader userInputReader = new UserInputReader();
        Assert.assertNull(userInputReader.convert("ABC"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_not_a_whole_natural_number(){
        UserInputReader userInputReader = new UserInputReader();
        Assert.assertNull(userInputReader.convert("0.7"));
    }
    @Test
    public void testConvert_It_returns_null_when_String_starts_with_negative_number(){
        UserInputReader userInputReader = new UserInputReader();
        Assert.assertNull(userInputReader.convert("-1"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_doesnt_end_with_a_letter(){
        UserInputReader userInputReader = new UserInputReader();
        Assert.assertNull(userInputReader.convert("123"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_currencypart_is_not_defined_as_possible_currency(){
        UserInputReader userInputReader = new UserInputReader();
        String invalideCurrency = String.valueOf(UserRequestTest.INVALID_CURRENCY);
        Assert.assertNull(userInputReader.convert(invalideCurrency));
    }

    @Test
    public void testConvert_It_returns_null_when_String_numberpart_is_longer_than_eight_digits(){
        UserInputReader userInputReader = new UserInputReader();
        Assert.assertNull(userInputReader.convert("123456789"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_numberpart_is_greater_than_ten_million(){
        UserInputReader userInputReader = new UserInputReader();
        Assert.assertNull(userInputReader.convert("100000001"));
    }

    @Test
    public void testConvert_It_returns_userRequest_when_number_and_Currencypart_are_valid(){
        UserInputReader userInputReader = new UserInputReader();
        UserRequest result = userInputReader.convert("1234A");
        Assert.assertNotNull(result);
    }

    @Test
    public void testConvert_It_returns_userRequest_even_with_leading_spaces_when_number_and_currencypart_are_valid(){
        UserInputReader userInputReader = new UserInputReader();
        UserRequest result = userInputReader.convert("    1234A");
        Assert.assertNotNull(result);
    }

    @Test
    public void testConvert_It_returns_userRequest_when_firest_sign_of_currencypart_is_valid_currency(){
        UserInputReader userInputReader = new UserInputReader();
        UserRequest result = userInputReader.convert("1234Afoo");
        Assert.assertNotNull(result);
    }

}