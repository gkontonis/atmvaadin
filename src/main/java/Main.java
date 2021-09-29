import Input.UserInputReader;
import Input.UserRequest;

public class Main {
    private final static UserInputReader INPUT_READER = new UserInputReader();
    Check check = new Check();
    Rechnen calc = new Rechnen();
    SplitInput splitter = new SplitInput();


    public static void main(String[] args) {
        Main main = new Main();
        UserRequest request = INPUT_READER.getUserinput();

        main.run(request);
    }

    private void run(UserRequest request) {
        if(request == null){
            throw new IllegalArgumentException("Request must not be null!");
        }

        System.out.println("Value: " + request.getCurrencyValue());
        System.out.println("Currency: " + request.getCurrencyType());
    }



}
