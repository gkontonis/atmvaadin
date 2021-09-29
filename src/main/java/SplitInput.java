import javafx.util.Pair;

public class SplitInput {
    public String[] split(String eingabe) {
        String[] pair = new String[2];
        pair[0] = eingabe.substring(0, eingabe.length()-1);
        pair[1] = eingabe.substring(eingabe.length()-1);
        return pair;
    }
}
