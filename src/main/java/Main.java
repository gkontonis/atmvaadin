import backend.calculator.Calculator;
import input.reader.PayoutInputReader;
import input.dto.PayoutRequest;
import output.Output;

public class Main {
    private final PayoutInputReader INPUT_READER = new PayoutInputReader();
    private Calculator calc = new Calculator();
    private Output output = new Output();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {

        PayoutRequest request = INPUT_READER.getUserinput();

        while(request != null) {
            output.printOutput(calc.calculate(request));
            request = INPUT_READER.getUserinput();
        }
    }



}
