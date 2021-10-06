import backend.calculator.Calculator;
import input.UserInputReader;
import input.dto.UserRequest;
import output.Output;

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
            output.printOutput(calc.calculate(request));
            request = INPUT_READER.getUserinput();
        }
    }



}
