import backend.calculator.Calculator;
import input.dto.ComplexRequest;
import input.reader.DepositInputReader;
import input.reader.PayoutInputReader;
import input.dto.SimpleRequest;
import output.MainMenu;
import output.Output;

public class Main {
    private final PayoutInputReader INPUT_READER = new PayoutInputReader();
    private final DepositInputReader DEPOSIT_INPUT_READER = new DepositInputReader();
    private Calculator calc = new Calculator();
    private Output output = new Output();

    public static void main(String[] args) {
        Main main = new Main();
        main.run5();
    }

    private void run() {
        SimpleRequest request = INPUT_READER.getUserinput();
        while (request != null) {
            output.printContainer(calc.calculateSuggestedDenomination(request));
            request = INPUT_READER.getUserinput();
        }
    }

   private void run2() {
       ComplexRequest request = DEPOSIT_INPUT_READER.getUserinput();
       while (request != null) {
           output.printContainer(calc.deposit(request));
           request = DEPOSIT_INPUT_READER.getUserinput();
       }
   }

    private void run3() {
        SimpleRequest request = INPUT_READER.getUserinput();
        while (request != null) {
            output.printContainer(calc.withdrawSimpleRequest(request));
            request = INPUT_READER.getUserinput();
        }
    }

    private void run4(){
        output.printContainerB(calc.getContainer());
    }

    private void run5(){
        MainMenu mainMenu = new MainMenu(calc);
        mainMenu.showMainMenu();
    }
}
