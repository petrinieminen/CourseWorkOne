import java.util.Scanner;

public class SubstringFinder {

    public static void main(String[] args) {
        System.out.println("Hello! I find substrings.");
        start();

    }

    private static void start() {
        Scanner lukija = new Scanner(System.in);
        Boolean isRunning = true;
        Boolean isSubRunning = true;

        while(isRunning) {
            System.out.println("Please, enter a string:");
            String lueTeksti = lukija.nextLine();
            if (checkString(lueTeksti)) {
                String lueSubTeksti = "";
                while(isSubRunning) {
                    System.out.println("Please, enter a substring:");
                    lueSubTeksti = lukija.nextLine();
                    if (checkSubString(lueSubTeksti, lueTeksti)) {
                        isSubRunning = false;
                    }

                }

            }


            isRunning = false;
        }



    }

    private static boolean checkSubString(String input, String source) {
        Boolean status = true;

        if(input.length() < 1) {
            status = false;
        }
        if(input.length() > source.length()) {
            status = false;
        }
        if(!input.isEmpty() && input.charAt(0) == '*' && input.charAt(input.length()-1) == '*')  {
            status = false;
        }

        if (!status) {
            System.out.println("Error!");
        }

        return status;

    }

    private static boolean checkString(String input) {
        if (!input.isEmpty()) return true;
        return false;
    }


}
