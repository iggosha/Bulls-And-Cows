package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int lenghtOfCode = 0;
        System.out.println("Please, enter the secret code's length:");
        try {
            lenghtOfCode = read.nextInt();
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Input the number of possible symbols in the code:");
        int numOfPossibleSymbols = 0;
        try {
            numOfPossibleSymbols = read.nextInt();
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
        List<Character> secretCode = generateSecretCode(lenghtOfCode, numOfPossibleSymbols);
        System.out.print("The secret is prepared: ");
        for (int i = 0; i < lenghtOfCode; i++) {
            System.out.print('*');
        }
        System.out.println(" (0-9, a-" + (char) ('a'+numOfPossibleSymbols-11) + ").");
        System.out.println("Okay, let's start a game!");
        List<Character> answerCode = new ArrayList<>();
        int bullCounter, cowCounter;
        for (int turnCounter = 0; !answerCode.equals(secretCode); turnCounter++) {
            System.out.println("Turn " + (turnCounter + 1) + ":");
            answerCode = inputAnswer();
            bullCounter = countBulls(secretCode, answerCode);
            cowCounter = countCows(secretCode, answerCode);
            System.out.print("Grade: ");
            if (bullCounter == 0 && cowCounter == 0) {
                System.out.println("None");
            } else if (bullCounter > 0 && bullCounter < lenghtOfCode && cowCounter == 0) {
                System.out.println(bullCounter + " bull(s)");
            } else if (bullCounter == 0 && cowCounter > 0) {
                System.out.println(cowCounter + " cow(s)");
            } else if (bullCounter > 0 && bullCounter < lenghtOfCode && cowCounter > 0) {
                System.out.println(bullCounter + " bull(s) and " + cowCounter + " cow(s)");
            } else if (bullCounter == lenghtOfCode) {
                System.out.println(bullCounter + " bull(s)");
                System.out.println("Congratulations! You guessed the secret code.");
            }
        }

    }

    public static int countBulls(List<Character> secretCode, List<Character> answerCode) {
        int bullCounter = 0;
        for (int i = 0; i < secretCode.size(); i++) {
            if (secretCode.get(i).equals(answerCode.get(i))) {
                bullCounter++;
            }
        }
        return bullCounter;
    }

    public static int countCows(List<Character> secretCode, List<Character> answerCode) {
        int cowCounter = 0;
        for (int i = 0; i < secretCode.size(); i++) {
            if (answerCode.contains(secretCode.get(i)) && !secretCode.get(i).equals(answerCode.get(i))) {
                cowCounter++;
            }
        }
        return cowCounter;
    }

    public static List<Character> generateSecretCode(int lenghtOfCode, int numOfPossibleSymbols) {
        try {
            if (lenghtOfCode < 1 || numOfPossibleSymbols > 36 || numOfPossibleSymbols < 1
                    || numOfPossibleSymbols < lenghtOfCode) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Error: can't generate a secret number with" +
                    " a length of " + lenghtOfCode + " and " + numOfPossibleSymbols + " possible symbols");
            System.exit(0);
        }
        List<Character> sercetCode = new ArrayList<>();
        if (numOfPossibleSymbols>10){ // not only numbers
        for (int i = 0; i < 10; i++) {
            sercetCode.add((char) (i + '0'));
        }
        for (int i = 0; i < numOfPossibleSymbols-10; i++) {
            sercetCode.add((char) ('a' + i));
        }}
        else { // only numbers
            for (int i = 0; i < numOfPossibleSymbols; i++) {
                sercetCode.add((char) (i + '0'));
            }
        }
        Collections.shuffle(sercetCode);
        sercetCode.subList(lenghtOfCode, sercetCode.size()).clear();
        return sercetCode;
    }

    public static List<Character> inputAnswer(){
        Scanner read = new Scanner(System.in);
        String str = read.nextLine();
        char[] chars = str.toCharArray();
        List<Character> answerCode = new ArrayList<>();
        for (char aChar : chars) {
            answerCode.add(aChar);
        }
        return answerCode;
    }
}