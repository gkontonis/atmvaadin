package input.reader;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DepositInputReaderTest {

    @Test
    public void testConvert_It_returns_null_when_String_is_null() {
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert(null));
    }
    @Test
    public void testConvert_It_returns_null_when_String_is_empty() {
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert(""));
    }

    @Test
    public void testConvert_It_returns_null_when_String_is_not_a_number() {
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("ABC"));
    }


    @Test
    public void testConvert_It_returns_null_when_String_starts_with_0(){
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("0B"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_not_a_whole_natural_number(){
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("1.7"));
    }
    @Test
    public void testConvert_It_returns_null_when_String_starts_with_negative_number(){
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("-1"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_doesnt_contain_a_letter(){
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("123"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_ends_with_a_letter(){
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("123B"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_amount_part_is_0(){
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("123A0"));
    }

    @Test
    public void testTestGetUserinput() {
    }

    @Test
    public void testTestConvert() {
    }

}