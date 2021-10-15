package output;

import backend.calculator.Calculator;
import input.reader.DepositInputReader;
import input.reader.PayoutInputReader;

import java.util.Scanner;

public class MainMenu {
    public MainMenu(Calculator calc) {
        this.calc = calc;
    }

    Scanner sc = new Scanner(System.in);
    Calculator calc;
    Output printer = new Output();

    public void showMainMenu() {
        boolean askInput = true;

        while (askInput) {
            System.out.println("1) Einzahlung");
            System.out.println("2) Auszahlung");
            System.out.println("3) Status");
            System.out.println("4) Beenden");
            System.out.print("> ");

            char input = sc.nextLine().charAt(0);

            if (input == '1') {
                showDepositMenu();
            } else if (input == '2') {
                showWithdrawalMenu();
            } else if (input == '3') {
                showStatus();
            } else if (input == '4') {
                askInput = false;
            } else {
                System.out.println("FALSCHE EINGABE\n");
            }
        }
    }

    private void showDepositMenu() {
        DepositInputReader dp = new DepositInputReader();
        PayoutInputReader po = new PayoutInputReader();

        System.out.println("1 Gesamtbetrag");
        System.out.println("2 Gestückelt");
        System.out.println("3 Hauptmenü");
        System.out.print("> ");

        char input = sc.nextLine().charAt(0);
        if (input == '1') {
            calc.deposit(calc.calculateSuggestedDenomination(po.getUserinput())); //TODO: currently calls PayoutInput reader. This sucks
        } else if (input == '2') {
            calc.deposit(dp.getUserinput());
        } else if (input == '3') {
            return;
        } else {
            System.out.println("FALSCHE EINGABE\n");
        }
    }

    private void showWithdrawalMenu() {
        PayoutInputReader po = new PayoutInputReader();
        System.out.println("1) Gesamtbetrag");
        System.out.println("2) Hauptmenü");
        System.out.print("> ");

        char input = sc.nextLine().charAt(0);
        if (input == '1') {
             printer.printContainer(calc.calculateAndWithdraw(po.getUserinput()));
        } else if (input == '2') {
            return;
        } else {
            System.out.println("FALSCHE EINGABE\n");
        }
    }

    private void showStatus() {
        printer.printContainerB(calc.getContainer());
    }
}
