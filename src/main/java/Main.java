public class Main {
    Einlesen einlesen = new Einlesen();
    Check check = new Check();
    Rechnen calc = new Rechnen();
    SplitInput splitter = new SplitInput();

    public static void main(String[] args) {
        Main main = new Main();

        main.run();
    }

    private void run() {
        String eingabe = einlesen.getUserinput();
        while (!eingabe.equals("exit")) {
            if (check.validate(eingabe)) {
                calc.calculate(splitter.split(eingabe));
            }
            else {
                System.out.println("FALSCHE EINHABE");
            }
            eingabe = einlesen.getUserinput();
        }
        System.out.println("\n------Auf wiedersehen!------");
    }



}
