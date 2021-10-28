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
            String input = sc.nextLine();


            if (input.length() < 0) {
                System.out.println("FEHLERHAFTE EINGABE\n");
                //showMainMenu();
                return;
            }

            //TODO: -> Ifs to switchCase -> in all menus
            switch (input) {
                case "1":
                    showDepositMenu();
                    break;
                case "2":
                    showWithdrawalMenu();
                    break;
                case "3":
                    showStatus();
                    break;
                case "4":
                    askInput = false;
                    break;
                default:
                    System.out.println("FEHLERHAFTE EINGABE\n");
            }
        }
        System.out.println("Vielen Dank, dass Sie den LehrlingsATM benutzt haben. Wir wünschen Ihnen einen schönen Tag!");
    }

    private void showDepositMenu() {
        DepositInputReader dp = new DepositInputReader();
        PayoutInputReader po = new PayoutInputReader();

        System.out.println("1 Gesamtbetrag");
        System.out.println("2 Gestückelt");
        System.out.println("3 Hauptmenü");
        System.out.print("> ");

        String input = sc.nextLine();

        if (input.length() < 0) {
            System.out.println("FEHLERHAFTE EINGABE\n");
            return;
        }

        switch (input) {
            case "1":
                calc.depositSimpleRequest(po.getUserinput()); //TODO: currently calls PayoutInput reader. Fix
                break;
            case "2":
                calc.deposit(dp.getUserinput());
                break;
            case "3":
                return;
            default:
                System.out.println("FEHLERHAFTE EINGABE\n");
        }
    }

    private void showWithdrawalMenu() {
        //TODO: Auszahlen Gestückelt
        PayoutInputReader po = new PayoutInputReader();
        System.out.println("1) Gesamtbetrag");
        System.out.println("2) Gestückelt");
        System.out.println("3) Hauptmenü");
        System.out.print("> ");

        String input = sc.nextLine();

        if (input.length() < 0) {
            System.out.println("FEHLERHAFTE EINGABE\n");
            return;
        }

        switch (input) {
            case "1":
                printer.printContainer(calc.withdrawSimpleRequest(po.getUserinput()));
                break;
            case "2":
                System.out.println("Not yet implemented");
                return;
                //break;
            case "3":
                return;
            default:
                System.out.println("FEHLERHAFTE EINGABE\n");
        }
    }
    private void showStatus() {
        printer.printContainerB(calc.getContainer());
    }
}
