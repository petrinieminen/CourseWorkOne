import java.util.Scanner;

public class SubstringFinder {

    public static void main(String[] args) {
        System.out.println("Hello! I find substrings.");
        start();

    }

    private static void start() {
        Scanner lukija = new Scanner(System.in);
        Boolean isRunning = true;
        Boolean nextOperation = false;

        while (isRunning) {
            System.out.println("Please, enter a string:");
            String lueTeksti = lukija.nextLine();
            if (checkString(lueTeksti)) {
                Boolean isSubRunning = true;
                while (isSubRunning) {
                    System.out.println("Please, enter a substring:");
                    String lueSubTeksti = lukija.nextLine();

                    if (checkSubString(lueSubTeksti, lueTeksti)) {
                        findSubString(lueSubTeksti, lueTeksti);

                        System.out.println("Continue (y/n)?");
                        String jatkuukoVastaus = lukija.nextLine();

                        if (jatkuukoVastaus.equals("y")) {
                            nextOperation = true;
                        } else {
                            nextOperation = false;
                        }
                    }
                    isSubRunning = false;
                }
            }
            if (!nextOperation) {
                isRunning = false;
                System.out.println("See you soon.");
            }
        }


    }

    private static void findSubString(String lueSubTeksti, String lueTeksti) {
        Boolean fromStart = false;
        Boolean fromEnd = false;

        if (lueSubTeksti.charAt(0) == '*') {
            fromEnd = true;
        } else if (lueSubTeksti.charAt(lueSubTeksti.length() - 1) == '*') {
            fromStart = true;
        }

        if (fromEnd || fromStart) {
            printWildcardSubString(removeWildcard(lueSubTeksti), lueTeksti, fromStart, fromEnd);
        } else {

            for (int i = 0; i < lueTeksti.length(); i++) {
                String stringToCheck = "";

                for (int k = i; k < lueTeksti.length(); k++) {
                    stringToCheck += lueTeksti.charAt(k);
                }
                printSubString(lueSubTeksti,
                        lueTeksti,
                        lueTeksti.length() - stringToCheck.length());

            }
        }

    }

    private static void printWildcardSubString(String lueSubTeksti, String lueTeksti, Boolean fromStart, Boolean fromEnd) {
        String buildedString = "";
        String subStringToCompare = "";

        for (int i = 0; i < lueTeksti.length(); i++) {
            if ((fromStart && i >= lueSubTeksti.length()) || (fromEnd && i < lueTeksti.length() - lueSubTeksti.length())) {
                buildedString += "-";
            } else {
                buildedString += lueTeksti.charAt(i);
                subStringToCompare += lueTeksti.charAt(i);
            }
        }
        if (lueSubTeksti.equals(subStringToCompare)) {
            System.out.println(buildedString);
        }
    }

    private static void printSubString(String lueSubTeksti, String lueTeksti, int startPos) {
        String buildedString = "";
        String subStringToCompare = "";


        for (int i = 0; i < lueTeksti.length(); i++) {
            if (i < startPos || i >= startPos + lueSubTeksti.length()) {
                buildedString += "-";
            } else {
                buildedString += lueTeksti.charAt(i);
                subStringToCompare += lueTeksti.charAt(i);
            }
        }
        if (lueSubTeksti.equals(subStringToCompare)) {
            System.out.println(buildedString);
        }
    }

    private static String removeWildcard(String lueSubTeksti) {
        String wildcardlessSubString = "";
        for (int i = 0; i < lueSubTeksti.length(); i++) {
            if (lueSubTeksti.charAt(i) != '*') {
                wildcardlessSubString += lueSubTeksti.charAt(i);
            }
        }
        return wildcardlessSubString;
    }


    private static boolean checkSubString(String input, String source) {
        Boolean status = true;

        if (input.length() < 1) {
            status = false;
        }
        if (input.length() > source.length()) {
            status = false;
        }
        if (!input.isEmpty() && input.charAt(0) == '*' && input.charAt(input.length() - 1) == '*') {
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
