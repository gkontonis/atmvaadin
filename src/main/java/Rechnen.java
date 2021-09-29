public class Rechnen {


    public void calculate(String[] pair) {
        Integer value = Integer.parseInt(pair[0]);
        Character type = pair[1].charAt(0);


        int anzahlCounter = 0;
        boolean hasLetter = false;

        if (type == 'A') {
            //500er
            while (value - 500 >= 0) {
                anzahlCounter++;
                value -= 500;
            }
            if (anzahlCounter > 0) {
                hasLetter = true;
                if (hasLetter){
                    System.out.print("S");
                }
                System.out.print(" 500 " + anzahlCounter);
            }
            anzahlCounter = 0;

            //200er
            while (value - 200 >= 0) {
                anzahlCounter++;
                value -= 200;
            }
            if (anzahlCounter > 0) {
                if (!hasLetter){
                    hasLetter=true;
                    System.out.print("S");
                }
                System.out.print(" 200 " + anzahlCounter);
            }
            anzahlCounter = 0;

            //100er
            while (value - 100 >= 0) {
                anzahlCounter++;
                value -= 100;
            }
            if (anzahlCounter > 0) {
                if (!hasLetter){
                    hasLetter=true;
                    System.out.print("S");
                }
                System.out.print(" 100 " + anzahlCounter);
            }
            anzahlCounter = 0;

            //50er
            while (value - 50 >= 0) {
                anzahlCounter++;
                value -= 50;
            }
            if (anzahlCounter > 0) {
                if (!hasLetter){
                    hasLetter=true;
                    System.out.print("S");
                }
                System.out.print(" 50 " + anzahlCounter);
            }
            anzahlCounter = 0;

            //20er
            while (value - 20 >= 0) {
                anzahlCounter++;
                value -= 20;
            }
            if (anzahlCounter > 0) {
                if (!hasLetter){
                    hasLetter=true;
                    System.out.print("S");
                }
                System.out.print(" 20 " + anzahlCounter);
            }
            anzahlCounter = 0;

            //10er
            while (value - 10 >= 0) {
                anzahlCounter++;
                value -= 10;
            }
            if (anzahlCounter > 0) {
                if (!hasLetter){
                    hasLetter=true;
                    System.out.print("S");
                }
                System.out.print(" 10 " + anzahlCounter);
            }
            anzahlCounter = 0;

            //5er
            while (value - 5 >= 0) {
                anzahlCounter++;
                value -= 5;
            }
            if (anzahlCounter > 0) {
                if (!hasLetter){
                    hasLetter=true;
                    System.out.print("S");
                }
                System.out.print(" 5 " + anzahlCounter);
            }
            anzahlCounter = 0;

            //2er
            while (value - 2 >= 0) {
                anzahlCounter++;
                value -= 2;
            }
            if (anzahlCounter > 0) {
                hasLetter=true;
                if (hasLetter){
                    System.out.print(" M");
                }
                System.out.print(" 2 " + anzahlCounter);
            }
            anzahlCounter = 0;

            //1er
            while (value - 1 >= 0) {
                anzahlCounter++;
                value -= 1;
            }
            if (anzahlCounter > 0) {
                if (!hasLetter){
                    System.out.print(" M");
                }
                System.out.print(" 1 " + anzahlCounter);
            }

            System.out.println();

        }
    }
}
