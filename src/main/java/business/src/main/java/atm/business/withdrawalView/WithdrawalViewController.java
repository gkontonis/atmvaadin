package business.src.main.java.atm.business.withdrawalView;

import backend.calculator.Calculator;
import backend.entity.MoneyBoxContainer;
import input.dto.PayoutRequest;

public class WithdrawalViewController {

    private Calculator CALCULATOR = new Calculator();
    public static final int ASCII_OFFSET = 48;

    public MoneyBoxContainer withdrawal(String input) {
        CALCULATOR = new Calculator();
        PayoutRequest payoutRequest = null;
        payoutRequest = convert(input);
        if (payoutRequest == null) {
            System.out.println("FEHLERHAFTE EINGABE\n");
        }
        if (payoutRequest.getValue() > 10000000) {
            System.out.println("ANGEFORDERTER BETRAG ZU GROSS\n");
        }
        return CALCULATOR.calculateAndWithdraw(payoutRequest);
    }

    public PayoutRequest convert(String input) {
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

        return new PayoutRequest(CALCULATOR.getCurrency(currencyChar), numberPart);
    }


    private int toNumber(char digit) {
        return digit - ASCII_OFFSET;
    }

    private boolean isNumber(char digit) {
        return digit >= '0' && digit <= '9';
    }
}
