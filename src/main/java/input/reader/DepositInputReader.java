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
            if (input.equals("exit")) {
                return null;
            }
            if (input.length() > 250) {
                System.out.println("EINGABE ZU LANG");
                return null;
            }

            depositRequest = convert(input);

            if (depositRequest == null) {
                System.out.println("FALSCHE EINGABE");
                continue;
            }
            isUserInput = false;
        }
        while (isUserInput);

        return depositRequest;
    }

    //----> fixconvert
    public DepositRequest convert(String input) {
        DepositRequest depositResult = new DepositRequest();
        if (input == null || input.isEmpty()) {
            return null;
        }

        String[] inputStrings = input.split(" ");

        for (String inputPart : inputStrings) {
            char[] inputArray = inputPart.toCharArray();
            MoneyBox mb = convertToSingleBox(inputArray);
            if (mb == null) {
                return null;
            }
            depositResult.addMoneyBox(mb);
        }
        if (depositResult.getMoneyBoxContainer().isEmpty()) {
            return null;
        }
        return depositResult;
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
                currencyChar = currentSign; //TODO: Check if valid currency? Add validator in Calculator or MoneyBox (Currency Enum is in moneybox) - NOTE: Same TODO in PayoutInputReader
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
