package backend.comperator;

import backend.entity.MoneyBox;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MoneyBoxComperatorTest {

    @Test
    public void testCompare() {
    }

    @Test
    public void testCompare_It_returns_0_when_type_and_value_and_amount_are_equal() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN, 1);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN, 1);
        assertEquals(moneyBoxComperator.compare(box1, box2), MoneyBoxComperator.EQUALS);
    }

    @Test
    public void testCompare_It_returns_1_when_first_is_type_note_and_second_is_type_coin() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE, 1);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN, 1);
        assertEquals(moneyBoxComperator.compare(box1, box2), MoneyBoxComperator.GREATER);
    }

    @Test
    public void testCompare_It_returns_negative_1_when_first_is_type_coin_and_second_is_type_note() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN, 1);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE, 1);
        assertEquals(moneyBoxComperator.compare(box1, box2), MoneyBoxComperator.SMALLER);
    }

    @Test
    public void testCompare_It_returns_1_when_first_is_value_greater_as_second_value() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        MoneyBox box1 = new MoneyBox(5, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE, 1);
        MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE, 1);
        assertEquals(moneyBoxComperator.compare(box1, box2), MoneyBoxComperator.GREATER);
    }

    @Test
    public void testCompare_It_returns_negative_1_when_first_value_smaller_second_value() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE, 1);
        MoneyBox box2 = new MoneyBox(5, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE, 1);
        assertEquals(moneyBoxComperator.compare(box1, box2), MoneyBoxComperator.SMALLER);
    }

   // @Test TODO: Werner fragen
   // public void testCompare_It_returns_1_when_first_amount_is_greater_than_second_amount() {
   //     MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
   //     MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE, 2);
   //     MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE, 1);
   //     assertEquals(moneyBoxComperator.compare(box1, box2), MoneyBoxComperator.GREATER);
   // }

 //  @Test
 //  public void testCompare_It_returns_negative_1_when_first_amount_smaller_second_amount() {
 //      MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
 //      MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE, 1);
 //      MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE, 2);
 //      assertEquals(moneyBoxComperator.compare(box1, box2), MoneyBoxComperator.SMALLER);
 //  }

 //  //yee
 //  @Test
 //  public void testCompare_It_returns_1_when_first_amount_same_as_second_amount_AND_first_type_is_coin() {
 //      MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
 //      MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN, 1);
 //      MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE, 1);
 //      assertEquals(moneyBoxComperator.compare(box1, box2), MoneyBoxComperator.SMALLER);
 //  }

 //  @Test
 //  public void testCompare_It_returns_negative_1_when_first_amount_same_as_second_amount_AND_first_type_is_NOTE() {
 //      MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
 //      MoneyBox box1 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE, 1);
 //      MoneyBox box2 = new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN, 1);
 //      assertEquals(moneyBoxComperator.compare(box1, box2), MoneyBoxComperator.GREATER);
 //  }

   //@Test
   //public void testReversed() {
   //}

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testThenComparing() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        moneyBoxComperator.thenComparing(null, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTestThenComparing() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        moneyBoxComperator.thenComparing(null, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testThenComparingInt() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        moneyBoxComperator.thenComparingInt(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testThenComparingLong() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        moneyBoxComperator.thenComparingLong(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testThenComparingDouble() {
        MoneyBoxComperator moneyBoxComperator = new MoneyBoxComperator();
        moneyBoxComperator.thenComparingDouble(null);
    }
}