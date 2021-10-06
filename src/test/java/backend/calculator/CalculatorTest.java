package backend.calculator;

import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;
import input.dto.PayoutRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculatorTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculate_It_is_not_valid_when_UserRequest_null() {
        Calculator calculator = new Calculator();
        calculator.calculate(null);
    }

    //@Test
    //public void testCalculate_It_is_valid_when_result_Object_is_result(){
    //
    //}

    @Test
    public void testCalculate_It_is_same_currency_as_UserRequestCurrency() {
        Calculator calculator = new Calculator();
        PayoutRequest payoutRequest = new PayoutRequest('A', 1);
        List<CalculationResult> results = calculator.calculate(payoutRequest);
        CalculationResult calculationResult = results.get(0);

        Assert.assertEquals(calculationResult.getCurrency(), MoneyBox.Currency.A);
    }

    @Test
    public void testCalculate_It_is_same_currencyResult_as_MoneyBox_values() {
        Calculator calculator = new Calculator();
        PayoutRequest payoutRequest = new PayoutRequest('A', getCurrencyTotalBaseAmount(MoneyBox.Currency.A));
        List<CalculationResult> expectedResult = getCurrencyTotalBaseResult(MoneyBox.Currency.A);
        List<CalculationResult> realResult = calculator.calculate(payoutRequest);

        assertCalculationResults(realResult, expectedResult);
    }


    private int getCurrencyTotalBaseAmount(MoneyBox.Currency currency) {
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();


        moneyBoxContainer.add(new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
        moneyBoxContainer.add(new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
        moneyBoxContainer.add(new MoneyBox(5, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        moneyBoxContainer.add(new MoneyBox(10, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        moneyBoxContainer.add(new MoneyBox(20, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        moneyBoxContainer.add(new MoneyBox(50, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        moneyBoxContainer.add(new MoneyBox(100, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        moneyBoxContainer.add(new MoneyBox(200, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        moneyBoxContainer.add(new MoneyBox(500, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));

        List<MoneyBox> boxes = moneyBoxContainer.get(currency);
        int currencyTotalAmount = 0;

        for (MoneyBox box : boxes) {
            currencyTotalAmount += box.getValue();
        }
        return currencyTotalAmount;
    }

    private List<CalculationResult> getCurrencyTotalBaseResult(MoneyBox.Currency currency) {
        MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer();

        moneyBoxContainer.add(new MoneyBox(1, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
        moneyBoxContainer.add(new MoneyBox(2, MoneyBox.Currency.A, MoneyBox.CurrencyType.COIN));
        moneyBoxContainer.add(new MoneyBox(5, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        moneyBoxContainer.add(new MoneyBox(10, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        moneyBoxContainer.add(new MoneyBox(20, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        moneyBoxContainer.add(new MoneyBox(50, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        moneyBoxContainer.add(new MoneyBox(100, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        moneyBoxContainer.add(new MoneyBox(200, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));
        moneyBoxContainer.add(new MoneyBox(500, MoneyBox.Currency.A, MoneyBox.CurrencyType.NOTE));

        List<MoneyBox> boxes = moneyBoxContainer.get(currency);
        List<CalculationResult> results = new ArrayList<>(boxes.size());

        for (MoneyBox box : boxes) {
            results.add(new CalculationResult(1, box, currency));
        }
        return results;
    }


    //TODO Ugly errorprone quick and dirty
    private void assertCalculationResults(List<CalculationResult> actual, List<CalculationResult> expected) {
        for (int i = 0; i < actual.size(); i++) {
            CalculationResult a = actual.get(i);
            CalculationResult e = expected.get(i);
            if (a.getAmount() != e.getAmount()) {
                throw new AssertionError("Lists differ at element: " + i + ", expected amount: " + e.getAmount() + ", actual amount: " + a.getAmount());
            }

            if (a.getCurrency() != e.getCurrency()) {
                throw new AssertionError("Lists differ at element: " + i + ", expected currency: " + e.getCurrency() + ", actual currency: " + a.getCurrency());
            }

            if(a.getBox().getValue() != e.getBox().getValue()){
                throw new AssertionError("Lists differ at element: " + i + ", expected value: " + e.getBox().getValue() + ", actual value: " + a.getBox().getValue());

            }

            if(a.getBox().getType() != e.getBox().getType()){
                throw new AssertionError("Lists differ at element: " + i + ", expected type: " + e.getBox().getType() + ", actual type: " + a.getBox().getType());

            }

        }

    }

}