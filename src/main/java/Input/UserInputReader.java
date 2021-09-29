package Input;

import java.util.Scanner;

public class UserInputReader {

    public static final int ASCII_OFFSET = 48;

    public UserRequest getUserinput() {
        Scanner sc = new Scanner(System.in);

        boolean isUserInput = true;

        UserRequest request = null;

        do {
            System.out.print("Bitte ganzzahligen Betrag & Währung A/B eingeben (z.B. 1234A) |'exit' zum abbrechen: ");
            String input = sc.nextLine();
            if (input.equals("exit")) {
                return null;
            }

            request = convert(input);

            if (request == null) {
                System.out.println("FALSCHE EINGABE");
                continue;
            }
            isUserInput = false;
        }
        while (isUserInput);

        return request;
    }

    public UserRequest convert(String input) {
        if (input == null) {
            return null;
        }

        if (input.isEmpty()) {
            return null;
        }
        input = input.trim();
        char[] inputArray = input.toCharArray();

        Character currencyTypePart = null;
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

            if (!UserRequest.isCurrencyTypeValid(currentSign)) {
                return null;
            }
            currencyTypePart = currentSign;
            break;
        }

        if (currencyTypePart == null) {
            return null;
        }

        return new UserRequest(currencyTypePart, numberPart);
    }

    private int toNumber(char digit) {
        return digit - ASCII_OFFSET;
    }

    private boolean isNumber(char digit) {
        return digit >= '0' && digit <= '9';
    }
}
