import Calculation.Calculator;
import Input.UserInputReader;
import Input.UserRequest;
import Output.Output;

public class Main {
    private final UserInputReader INPUT_READER = new UserInputReader();
    private Calculator calc = new Calculator();
    private Output output = new Output();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {

        UserRequest request = INPUT_READER.getUserinput();

        while(request != null) {
            //if (request == null) {
            //    throw new IllegalArgumentException("Request must not be null!");
            //}

            //System.out.println("Value: " + request.getCurrencyValue());
            //System.out.println("Currency: " + request.getCurrencyType());

            output.printOutput(calc.calculate(request));
            request = INPUT_READER.getUserinput();
        }
    }



}
