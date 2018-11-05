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
        Boolean validContinueAnswer = false;
        Boolean nextSubWhile = false;

        while (isRunning) {
            System.out.println("Please, enter a string:");
            String lueTeksti = lukija.nextLine();
            if (checkString(lueTeksti)) {
                Boolean isSubRunning = true;
                while (isSubRunning && !validContinueAnswer) {
                    System.out.println("Please, enter a substring:");
                    String lueSubTeksti = lukija.nextLine();

                    if (checkSubString(lueSubTeksti, lueTeksti)) {
                        findSubString(lueSubTeksti, lueTeksti);

                    } else
                        nextSubWhile = true;

                    if (!nextSubWhile) {
                        while (!validContinueAnswer) {
                            System.out.println("Continue (y/n)?");
                            String jatkuukoVastaus = lukija.nextLine();
                            validContinueAnswer = checkIfValidContinueAnswer(jatkuukoVastaus);
                            if (!validContinueAnswer) {
                                System.out.println("Error!");
                            }

                            if (jatkuukoVastaus.equals("y")) {
                                nextOperation = true;
                                isSubRunning = false;
                            } else if (jatkuukoVastaus.equals("n")) {
                                nextOperation = false;
                                isSubRunning = false;
                            }
                        }
                    }
                    nextSubWhile = false;
                    validContinueAnswer = false;

                }
            }
            if (!nextOperation) {
                isRunning = false;
                System.out.println("See you soon.");
            }
        }


    }

    private static Boolean checkIfValidContinueAnswer(String jatkuukoVastaus) {
        if (jatkuukoVastaus.equals("y") || jatkuukoVastaus.equals("n")) {
            return true;
        } else
            return false;
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
            printSubString(removeWildcard(lueSubTeksti), lueTeksti, 0, fromStart, fromEnd);
        } else {

            for (int i = 0; i < lueTeksti.length(); i++) {
                String stringToCheck = "";

                for (int k = i; k < lueTeksti.length(); k++) {
                    stringToCheck += lueTeksti.charAt(k);
                }
                printSubString(lueSubTeksti,
                        lueTeksti,
                        lueTeksti.length() - stringToCheck.length(), fromStart, fromEnd);

            }
        }

    }


    private static void printSubString(String lueSubTeksti, String lueTeksti, int startPos, boolean fromStart,
                                       boolean fromEnd) {
        String buildedString = "";
        String subStringToCompare = "";


        for (int i = 0; i < lueTeksti.length(); i++) {
            if (fromEnd || fromStart) {
                if ((fromStart && i >= lueSubTeksti.length()) || (fromEnd && i < lueTeksti.length() - lueSubTeksti.length())) {
                    buildedString += "-";
                } else {
                    buildedString += lueTeksti.charAt(i);
                    subStringToCompare += lueTeksti.charAt(i);
                }
            } else {
                if (i < startPos || i >= startPos + lueSubTeksti.length()) {
                    buildedString += "-";
                } else {
                    buildedString += lueTeksti.charAt(i);
                    subStringToCompare += lueTeksti.charAt(i);
                }
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
