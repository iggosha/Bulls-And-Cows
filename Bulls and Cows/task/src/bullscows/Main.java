package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length: ");
        int lenghtOfCode = read.nextInt();
        System.out.println("Okay, let's start a game!");
        long secretCode = generateRandomNumber(lenghtOfCode);
        long answerCode = 0;
        int bullCounter, cowCounter;
        List<Integer> secretList = divideToDigits((int) secretCode);
        for (int turnCounter = 0; answerCode != secretCode; turnCounter++) {
            System.out.println("Turn " + (turnCounter + 1) + ":");
            answerCode = read.nextInt();
            List<Integer> answerList = divideToDigits((int) answerCode);
            bullCounter = countBulls(secretList, answerList);
            cowCounter = countCows(secretList, answerList);
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
    public static List<Integer> divideToDigits(int num) {
        List<Integer> arr = new ArrayList<>();
        if (num>0) {
            while (num > 0) {
                arr.add(num % 10);
                num /= 10;
            }
        }
        else {
            for (int i = 0; i < 10; i++) {
                arr.add(0);
            }

        }
        return arr;
    }
    public static int countBulls(List<Integer> firstList, List<Integer> secondList) {
        int bullCounter = 0;
        for (int i = 0; i < firstList.size(); i++) {
            if (firstList.get(i).equals(secondList.get(i))) {
                bullCounter++;
            }
        }
        return bullCounter;
    }
    public static int countCows(List<Integer> firstList, List<Integer> secondList) {
        int cowCounter = 0;
        for (int i = 0; i < firstList.size(); i++) {
            if (secondList.contains(firstList.get(i)) && !firstList.get(i).equals(secondList.get(i))) {
                cowCounter++;
            }
        }
        return cowCounter;
    }
    public static long generateRandomNumber(int lenghtOfCode){
        List<Integer> listOfDigits = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            listOfDigits.add(i);
        }
        Collections.shuffle(listOfDigits);
        listOfDigits.add(0);
        try {
            if (lenghtOfCode>10 || lenghtOfCode <1){
                throw new Exception();
            }
            long secretCode = listOfDigits.get(0);
            if (lenghtOfCode>1) {
                for (int i = 1; i < lenghtOfCode; i++) {
                    secretCode *= 10;
                    secretCode += listOfDigits.get(i);
                }
            }
            return secretCode;
        }
        catch (Exception e)
        {
            System.out.println("Error: can't generate a secret number with" +
                    " a length of " + lenghtOfCode + " because there aren't enough unique digits.");
        }
        return 0;
    }
}