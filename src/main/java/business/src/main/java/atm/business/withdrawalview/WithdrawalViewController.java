package business.src.main.java.atm.business.withdrawalview;

import backend.calculator.Calculator;
import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;
import business.src.main.java.atm.business.requests.ComplexRequest;
import business.src.main.java.atm.business.requests.SimpleRequest;

//TODO: Replace all return nulls with exceptions(usually)
public class WithdrawalViewController {

    private Calculator CALCULATOR = new Calculator();
    public static final int ASCII_OFFSET = 48;

    public MoneyBoxContainer withdrawalTotal(String input) {
        SimpleRequest simpleRequest = null;
        simpleRequest = convert(input);

        if (simpleRequest == null) {
            System.out.println("FEHLERHAFTE EINGABE\n");
        }
        if (simpleRequest.getValue() > 10000000) {
            System.out.println("ANGEFORDERTER BETRAG ZU GROSS\n");
        }

        return CALCULATOR.withdrawSimpleRequest(simpleRequest);
    }

    public MoneyBoxContainer withdrawDenominated(String input) {
        ComplexRequest complexRequest = null;

        if (input.length() > 250) {
            throw new IllegalArgumentException("Input is too long!");
        }
        complexRequest = convertToComplexRequest(input);
        if (complexRequest == null) {
            //TODO FIX FE OUTPUT "Cant withdraw ** amount" complexRequest.getMoneyBoxContainer().getMap().values(
            throw new IllegalStateException("Deposit Request must not be null!");
        }
        return CALCULATOR.withdraw(complexRequest);
    }

    public SimpleRequest convert(String input) {
        if (input == null) {
            return null;
        }
        if (input.contains(".") || input.contains(",")) {
            return null;
        }
        if (input.isEmpty()) {
            return null;
        }
        input = input.trim();
        char[] inputArray = input.toCharArray();

        Character currencyChar = null;
        int numberPart = 0;

        for (int i = 0; i < inputArray.length; i++) {
            if (!isNumber(inputArray[0])) {
                return null;
            }

            if (isNumber(inputArray[0]) && toNumber(inputArray[0]) == 0) {
                return null;
            }

            char currentSign = inputArray[i];
            if (isNumber(currentSign)) {
                numberPart *= 10;
                numberPart += toNumber(currentSign);
                continue;
            }

            currencyChar = currentSign;
            break;
        }

        if (currencyChar == null) {
            return null;
        }

        return new SimpleRequest(CALCULATOR.getCurrency(currencyChar), numberPart);
    }

    private ComplexRequest convertToComplexRequest(String input) {
        ComplexRequest complexRequest = new ComplexRequest();
        if (input == null || input.isEmpty()) {
            return null;
        }
        if (input.contains(".") || input.contains(",")) {
            return null;
        }
        String[] inputStrings = input.split(" ");

        for (String inputPart : inputStrings) {
            char[] inputArray = inputPart.toCharArray();
            MoneyBox mb = convertToSingleBox(inputArray);
            if (mb == null) {
                return null;
            }
            complexRequest.addMoneyBox(mb);
        }
        if (complexRequest.getMoneyBoxContainer().isEmpty()) {
            return null;
        }
        return complexRequest;
    }

    //TODO Optimize
    private MoneyBox convertToSingleBox(char[] source) {
        int value = 0;
        Character currencyChar = null;
        int amount = 0;

        if (!isNumber(source[0])) {
            return null;
        }
        if (toNumber(source[0]) == 0) {
            return null;
        }

        for (int i = 0; i < source.length; i++) {

            char currentSign = source[i];
            if (isNumber(currentSign) && currencyChar == null) {
                value *= 10;
                value += toNumber(currentSign);
                continue;
            }

            if (currencyChar == null) {
                currencyChar = currentSign;
                continue;
            }
            //same principle twice, maybe fix
            if (isNumber(currentSign)) {
                amount *= 10;
                amount += toNumber(currentSign);
                continue;
            }

            if (amount <= 0) {
                return null;
            }
        }

        if (currencyChar == null || amount == 0) {
            return null;
        }

        return createMoneyBox(value, currencyChar, amount);
    }

    private MoneyBox createMoneyBox(int value, char currChar, int amount) {
        return new MoneyBox(value, CALCULATOR.getCurrency(currChar), CALCULATOR.getType(value, currChar), amount);
    }

    private int toNumber(char digit) {
        return digit - ASCII_OFFSET;
    }

    private boolean isNumber(char digit) {
        return digit >= '0' && digit <= '9';
    }
}
