package input.reader;

import backend.calculator.Calculator;
import input.dto.SimpleRequest;

import java.util.Scanner;

public class PayoutInputReader {
    private final Calculator CALCULATOR = new Calculator();

    public static final int ASCII_OFFSET = 48;

    public SimpleRequest getUserinput() {
        Scanner sc = new Scanner(System.in);

        boolean isUserInput = true;

        SimpleRequest simpleRequest = null;

        do {
            System.out.println("Bitte den Betrag & Währung A/B eingeben (z.B. 1234A) den Sie eingeben wollen: "); //VAR B
            String input = sc.nextLine();
            // if (input.equals("exit")) { //VAR A
            //     return null;
            // }

            simpleRequest = convert(input);

            if (simpleRequest == null) {
                System.out.println("FEHLERHAFTE EINGABE\n");
                return null;
            }
            if (simpleRequest.getValue() > 10000000) {
                System.out.println("ANGEFORDERTER BETRAG ZU GROSS\n");
                return null;
            }
            isUserInput = false;
        }
        while (isUserInput);

        return simpleRequest;
    }

    public SimpleRequest convert(String input) {
        if (input == null) {
            return null;
        }
        if(input.contains(".") || input.contains(",")){
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

            currencyChar = currentSign;     //TODO: Check if valid currency? Add validator in Calculator or MoneyBox - NOTE: Same TODO in DepositInputReader
            break;
        }

        if (currencyChar == null) {
            return null;
        }

        return new SimpleRequest(CALCULATOR.getCurrency(currencyChar), numberPart);
    }

    private int toNumber(char digit) {
        return digit - ASCII_OFFSET;
    }

    private boolean isNumber(char digit) {
        return digit >= '0' && digit <= '9';
    }
}
