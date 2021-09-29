import java.util.Scanner;

public class Einlesen {
    public String getUserinput() {
        System.out.print("Bitte ganzzahligen Betrag & Währung A/B eingeben (z.B. 1234A) |'exit' zum abbrechen: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
