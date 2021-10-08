import backend.calculator.Calculator;
import input.dto.DepositRequest;
import input.reader.DepositInputReader;
import input.reader.PayoutInputReader;
import input.dto.PayoutRequest;
import output.Output;

public class Main {
    private final PayoutInputReader INPUT_READER = new PayoutInputReader();
    private final DepositInputReader DEPOSIT_INPUT_READER = new DepositInputReader();
    private Calculator calc = new Calculator();
    private Output output = new Output();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {



        PayoutRequest request = INPUT_READER.getUserinput();

        while(request != null) {
            output.printOutput(calc.calculatePayoutRequest(request));
            request = INPUT_READER.getUserinput();
        }
    }

    private void run2(){
        DepositRequest request = DEPOSIT_INPUT_READER.getUserinput();
        while(request != null) {
            output.printOutput(calc.deposit(request));
            request = DEPOSIT_INPUT_READER.getUserinput();
        }
    }



}
