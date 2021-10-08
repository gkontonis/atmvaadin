package input.reader;

import backend.calculator.Calculator;
import backend.entity.MoneyBox;
import input.dto.DepositRequest;
import input.dto.PayoutRequest;

import java.util.Scanner;

public class DepositInputReader {

    private final Calculator CALCULATOR = new Calculator();

    public static final int ASCII_OFFSET = 48;

    public DepositRequest getUserinput() {
        Scanner sc = new Scanner(System.in);

        boolean isUserInput = true;

        DepositRequest depositRequest = null;

        do {
            System.out.println("Bitte die Beträge eingeben die Sie einzahlen wollen (Form: TypWährungAnzahl z.B. 10A15 5A33) "); //TODO: Sinnvoller Text
            System.out.print(">");
            String input = sc.nextLine();
            // if (input.equals("exit")) {
            //     return null;
            // }

            depositRequest = convert(input);

            //if (depositRequest == null || depositRequest.getCurrencyValue() > 10000000) {
            //    System.out.println("FALSCHE EINGABE");
            //    continue;
            //}

            if (depositRequest == null) {
                System.out.println("FALSCHE EINGABE");
                continue;
            }
            isUserInput = false;
        }
        while (isUserInput);

        return depositRequest;
    }

    public DepositRequest convert(String input) {
        DepositRequest result = new DepositRequest();
        if (input == null) {
            return null;
        }
        if (input.isEmpty()) {
            return null;
        }
        String[] inputStrings = input.split(" ");

        for (String inputPart : inputStrings) {
            char[] inputArray = inputPart.toCharArray();

            Character currencyChar = null;
            int amount = 0;
            int value = 0;

            for (int i = 0; i < inputArray.length; i++) {
                if (!isNumber(inputArray[0])) {
                    return null;
                }

                if (isNumber(inputArray[0]) && toNumber(inputArray[0]) == 0) {
                    return null;
                }

                char currentSign = inputArray[i];
                if (isNumber(currentSign)) {
                    value *= 10;
                    value += toNumber(currentSign);
                    continue;
                }

                if (!DepositRequest.isCurrencyTypeValid(currentSign)) {
                    return null;
                }

                if (currencyChar == null) {
                    if (!DepositRequest.isCurrencyTypeValid(currentSign)) {
                        return null;
                    }
                    currencyChar = currentSign;
                    continue;
                }

                //TODO: same priciple as for first number part - maybe merge together?
                if (isNumber(currentSign)) {
                    amount *= 10;
                    amount += toNumber(currentSign);
                    continue;
                }

                if (amount <= 0) {
                    return null;
                }
                result.addMoneyBox(createMoneyBox(value, currencyChar, amount));
            }
        }
        if (result.getMoneyBoxContainer().isEmpty()) {
            return null;
        }
        return result;
    }

    //TODO: createMoneyBox should probably not be done here
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
