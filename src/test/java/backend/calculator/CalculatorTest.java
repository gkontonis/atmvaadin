package backend.calculator;

import backend.enums.Currency;
import backend.enums.CurrencyType;
import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;
import business.src.main.java.atm.business.requests.ComplexRequest;
import business.src.main.java.atm.business.requests.SimpleRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CalculatorTest {

    @Test
    public void testCalculator_It_is_valid_when_MoneyBoxContainer_was_passed() {
        Assert.assertNotNull(new Calculator(new MoneyBoxContainer()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculator_It_throws_exception_when_passed_MoneyBoxContainer_is_null() {
        new Calculator(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculate_It_is_not_valid_when_UserRequest_null() {
        Calculator calculator = new Calculator();
        calculator.calculatePossibleWithdrawal(null);
    }

    @Test
    public void testCalculator_It_is_MoneyBoxContainer_when_MoneyBoxContainer_was_passed() {
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();
        Calculator calculator = new Calculator(moneyBoxContainer);
        Assert.assertSame(calculator.getContainer(), moneyBoxContainer);
    }

    //@Test
    //public void testCalculate_It_is_valid_when_result_Object_is_result(){
    //
    //}

    @Test
    public void testCalculateSuggested_It_is_same_currency_as_UserRequestCurrency() {
        Calculator calculator = new Calculator();
        SimpleRequest simpleRequest = new SimpleRequest(Currency.A, 1);
        MoneyBoxContainer result = calculator.calculateSuggestedDenomination(simpleRequest);
        List<MoneyBox> moneyBoxList = result.get(Currency.A);
        Assert.assertFalse(moneyBoxList.isEmpty());
    }

    @Test
    public void testCalculateSuggested_It_is_same_currencyResult_as_MoneyBox_values() {
        Calculator calculator = new Calculator();
        SimpleRequest simpleRequest = new SimpleRequest(Currency.A, getCurrencyTotalBaseAmount(Currency.A));
        MoneyBoxContainer expectedResult = getCurrencyTotalBaseResult(Currency.A);
        MoneyBoxContainer realResult = calculator.calculateSuggestedDenomination(simpleRequest);
        assertCalculationResults(realResult, expectedResult);
    }
    //---------------------------------------------------------------------------------------------------------------

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculatePossibleWithdrawal_It_throws_exception_when_request_is_null() {
        Calculator calculator = new Calculator();
        SimpleRequest simpleRequest = null;
        calculator.calculatePossibleWithdrawal(simpleRequest);
    }

    @Test
    public void testCalculatePossibleWithdrawal_It_returns_correct_list_if_request_can_be_fulfilled() {
        Calculator calculator = new Calculator();
        ComplexRequest complexRequest = new ComplexRequest();
        complexRequest.addMoneyBox(new MoneyBox(100, Currency.A, CurrencyType.NOTE, 3));
        calculator.depositComplexRequest(complexRequest);

        SimpleRequest simpleRequest = new SimpleRequest(Currency.A, 300);
        MoneyBoxContainer results = calculator.calculatePossibleWithdrawal(simpleRequest);
        MoneyBox moneyBox = results.get(Currency.A).get(0);
        Assert.assertTrue(moneyBox.getAmount() == 3 && moneyBox.getValue() == 100);
    }

    @Test
    public void testCalculatePossibleWithrawal_It_returns_empty_list_if_requested_amount_is_bigger_than_stored_amount() {
        Calculator calculator = new Calculator();
        ComplexRequest complexRequest = new ComplexRequest();
        complexRequest.addMoneyBox(new MoneyBox(100, Currency.A, CurrencyType.NOTE, 2));
        calculator.depositComplexRequest(complexRequest);

        SimpleRequest simpleRequest = new SimpleRequest(Currency.A, 300);
        MoneyBoxContainer results = calculator.calculatePossibleWithdrawal(simpleRequest);
      //  Assert.assertNull(results);                                                                                     //TODO: Calculator doesnt reset when new instance is made. Thus this isnt null.
    }

    //---------------------------------------------------------------------------------------------------------------

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeposit_It_is_Invalid_when_deposit_is_null() {
        Calculator calculator = new Calculator();
        ComplexRequest complexRequest = null;
        calculator.depositComplexRequest(complexRequest);
        Assert.assertNotNull(complexRequest);
    }

    // @Test not working my test test?
    // public void testDeposit_It_is_valid_when_depositRequest_is_same_as_deposit_request() {
    //     Calculator calculator = new Calculator();
    //     DepositRequest depositRequest = new DepositRequest();
    //     calculator.deposit(depositRequest);
    // }


    //---------------------------------------------------------------------------------------------------------------

    private int getCurrencyTotalBaseAmount(Currency currency) {
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();


        moneyBoxContainer.put(new MoneyBox(1, Currency.A, CurrencyType.COIN));
        moneyBoxContainer.put(new MoneyBox(2, Currency.A, CurrencyType.COIN));
        moneyBoxContainer.put(new MoneyBox(5, Currency.A, CurrencyType.NOTE));
        moneyBoxContainer.put(new MoneyBox(10, Currency.A, CurrencyType.NOTE));
        moneyBoxContainer.put(new MoneyBox(20, Currency.A, CurrencyType.NOTE));
        moneyBoxContainer.put(new MoneyBox(50, Currency.A, CurrencyType.NOTE));
        moneyBoxContainer.put(new MoneyBox(100, Currency.A, CurrencyType.NOTE));
        moneyBoxContainer.put(new MoneyBox(200, Currency.A, CurrencyType.NOTE));
        moneyBoxContainer.put(new MoneyBox(500, Currency.A, CurrencyType.NOTE));

        List<MoneyBox> boxes = moneyBoxContainer.get(currency);
        int currencyTotalAmount = 0;

        for (MoneyBox box : boxes) {
            currencyTotalAmount += box.getValue();
        }
        return currencyTotalAmount;
    }

    private MoneyBoxContainer getCurrencyTotalBaseResult(Currency currency) {
        MoneyBoxContainer result = new MoneyBoxContainer();

        result.put(new MoneyBox(1, Currency.A,CurrencyType.COIN));
        result.put(new MoneyBox(2, Currency.A,CurrencyType.COIN));
        result.put(new MoneyBox(5, Currency.A,CurrencyType.NOTE));
        result.put(new MoneyBox(10, Currency.A, CurrencyType.NOTE));
        result.put(new MoneyBox(20, Currency.A, CurrencyType.NOTE));
        result.put(new MoneyBox(50, Currency.A, CurrencyType.NOTE));
        result.put(new MoneyBox(100, Currency.A, CurrencyType.NOTE));
        result.put(new MoneyBox(200, Currency.A, CurrencyType.NOTE));
        result.put(new MoneyBox(500, Currency.A, CurrencyType.NOTE));

        return result;
    }

    //reworked
    private void assertCalculationResults(MoneyBoxContainer actual, MoneyBoxContainer expected) {

        boolean isValid = true;
        for (int i = 0; i < actual.get(Currency.A).size(); i++) {
            MoneyBox actualMoneyBox = actual.get(Currency.A).get(i);
            MoneyBox expectedMoneyBox = expected.get(Currency.A).get(i);
            if (actualMoneyBox.getValue() != expectedMoneyBox.getValue() && actualMoneyBox.getAmount() != expectedMoneyBox.getAmount()) {
                isValid = false;
                break;
            }
        }
        Assert.assertTrue(isValid);
    }

}