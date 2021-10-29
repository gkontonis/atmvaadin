package input.reader;

import backend.enums.Currency;
import backend.enums.CurrencyType;
import backend.entity.MoneyBox;
import business.src.main.java.atm.business.requests.ComplexRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    public void testConvert_It_returns_null_when_String_starts_with_0() {
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("0B"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_not_a_whole_natural_number() {
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("1.7"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_starts_with_negative_number() {
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("-1"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_doesnt_contain_a_letter() {
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("20"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_ends_with_a_letter() {
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("20B"));
    }

    @Test
    public void testConvert_It_returns_null_when_String_amount_part_is_0() {
        DepositInputReader inputReader = new DepositInputReader();
        Assert.assertNull(inputReader.convert("20A0"));
    }

    @Test
    public void testTestConvert_It_returns_valid_depositResult() {
        DepositInputReader depositInputReader = new DepositInputReader();
        ComplexRequest expected = new ComplexRequest();
        expected.addMoneyBox(new MoneyBox(20, Currency.A, CurrencyType.NOTE, 10));
        ComplexRequest actual = depositInputReader.convert("20A10");
        Assert.assertTrue(expected.getMoneyBoxContainer().contains(actual.getMoneyBoxContainer().get(Currency.A).get(0)));
    }

}