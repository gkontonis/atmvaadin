package input.reader;

import backend.calculator.Calculator;
import input.dto.PayoutRequest;

import java.util.Scanner;

public class PayoutInputReader {
    private final Calculator CALCULATOR = new Calculator();


    public static final int ASCII_OFFSET = 48;

    public PayoutRequest getUserinput() {
        Scanner sc = new Scanner(System.in);

        boolean isUserInput = true;

        PayoutRequest request = null;

        do {
            System.out.println("Bitte den Betrag & Währung A/B eingeben (z.B. 1234A) den Sie eingeben wollen: "); //VAR B
            //System.out.print("Bitte den Betrag & Währung A/B eingeben (z.B. 1234A) den Sie eingeben wollen |'exit' zum abbrechen: "); //VAR A
            String input = sc.nextLine();
            // if (input.equals("exit")) { //VAR A
            //     return null;
            // }

            request = convert(input);

            if (request == null || request.getValue() > 10000000) {
                System.out.println("FALSCHE EINGABE\n");
                return null;
            }
            isUserInput = false;
        }
        while (isUserInput);

        return request;
    }

    public PayoutRequest convert(String input) {
        if (input == null) {
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

            currencyChar = currentSign;     //TODO: Check if valid currency? Add validator in Calculator or MoneyBox (Currency Enum is in moneybox) - NOTE: Same TODO in DepositInputReader
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
