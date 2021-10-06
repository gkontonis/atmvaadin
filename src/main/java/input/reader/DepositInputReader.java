package input.reader;

import input.dto.DepositRequest;
import input.dto.PayoutRequest;

import java.util.Scanner;

public class DepositInputReader {

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

            if(depositRequest == null){
                System.out.println("FALSCHE EINGABE");
            }
            isUserInput = false;
        }
        while (isUserInput);

        return depositRequest;
    }

    public DepositRequest convert(String input) {
        if (input == null) {
            return null;
        }

        if (input.isEmpty()) {
            return null;
        }
        input = input.trim();
        char[] inputArray = input.toCharArray();

        Character currencyTypePart = null;
       throw new IllegalStateException("Not yet Implemented");
    }

    private int toNumber(char digit) {
        return digit - ASCII_OFFSET;
    }

    private boolean isNumber(char digit) {
        return digit >= '0' && digit <= '9';
    }

}
