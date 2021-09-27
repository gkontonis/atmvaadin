public class Check {
    public boolean validate(String input){
        if(input == null){
            return false;
        }
        if(input.length() > 9){
            return false;
        }
        if(input.length() <= 0){
            return false;
        }
        if(input.length() == 1){
            return false;
        }

        for (int i = 0; i < input.length() - 1; i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }

        if (input.charAt(input.length() - 1) == 'A' || input.charAt(input.length() - 1) == 'B') {
            return true;
        }
        return false;
    }
}
