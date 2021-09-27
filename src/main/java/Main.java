public class Main {
    Einlesen einlesen = new Einlesen();
    Check check = new Check();
    Rechnen calc = new Rechnen();
    SplitEingabe splitEingabe = new SplitEingabe();

    static void main(String[] args) {
        Main main = new Main();

        main.run();
    }

    private void run() {
        String eingabe = einlesen.getUserinput();
        if(check.validate(eingabe)){

            calc.calculate(splitEingabe.split(eingabe));
        }
    }



}
