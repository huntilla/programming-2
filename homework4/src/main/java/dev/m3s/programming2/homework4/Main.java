package dev.m3s.programming2.homework4;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static WordList wordList;
    private final static int amountOfGuesses = 4;
    private static Hangman game;

    public static void main(String[] args) throws FileNotFoundException {
        try {
            wordList = new WordList("words.txt");
            gameMenu();
            game = new Hangman(wordList, amountOfGuesses);
            playGame();
        } catch (FileNotFoundException e) {
            System.out.println("Word file could not be found!");
        }
    }

    private static void gameMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman!");
        String input;
        do {
            System.out.println("Would you like to filter the words? Yes/No (Y/N)");
            input = scanner.nextLine().toLowerCase();
            if (!input.equals("yes") 
            && !input.equals("no") && !input.equals("y") && !input.equals("n")) {
                System.out.println("Invalid input. Try again!");
            }
        } while (!input.equals("yes") 
        && !input.equals("no") && !input.equals("y") && !input.equals("n"));

        if (input.equals("yes") || input.equals("y")) {
            do {
                System.out.println("Filtering options:\n" +
                "Filter by length (length/L)\n" +
                "Filter by characters (char/C)");
                input = scanner.nextLine();
                if (!input.equals("length") && !input.equals("l") 
                && !input.equals("char") && !input.equals("c")) {
                    System.out.println("Incorrect input. Try again!");
                }
            } while (!input.equals("length") && !input.equals("l") 
            && !input.equals("char") && !input.equals("c"));

            WordList filteredList = new WordList();

            if (input.equals("length") || input.equals("l")) {
                int wordLength = 0;
                do {
                    System.out.println("Give the word length (int)");
                    try {
                        wordLength = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        System.out.println("Invalid input. Try again!");
                        continue;
                    }

                    filteredList = wordList.theWordsOfLength(wordLength);
                    if (filteredList.giveWords().isEmpty()) {
                        System.out.println("No words matching given length found!");
                    }

                } while (filteredList.giveWords().isEmpty());
            }

            if (input.equals("char") || input.equals("c")) {
                do {
                    System.out.println("Give your filter in the following format: ___a__");
                    String filter = scanner.nextLine().toLowerCase();
                    filteredList = wordList.theWordsWithCharacters(filter);
                    if (filteredList.giveWords().isEmpty()) {
                        System.out.println("No words matching given filter found!");
                        System.out.println("Please try again");
                    }
                } while (filteredList.giveWords().isEmpty());
            }
            wordList = filteredList;
        }
    }

    private static void playGame() {
        while (!game.theEnd()) {
            System.out.println("The hidden word...");
            System.out.println();
            System.out.println(game.maskedWord());
            System.out.println();
            System.out.println("Guesses left: " + game.guessesLeft());
            System.out.println("Guessed letters: " + game.guesses());
            System.out.println();
            game.guess(userInput());
        }
        if (game.guessesLeft() == 0) {
            System.out.println("Sorry, you lost!");
            System.out.printf("The hidden word was: \"%s\"\n", game.word());
        }
        if (game.guessesLeft() != 0) {
            System.out.println("Congratulations! You won!!!");
            System.out.printf("The hidden word was: \"%s\"\n", game.word());
        }
    }

    private static char userInput() {
        Scanner scanner =  new Scanner(System.in);
        String guess;
        do {
            System.out.println("Guess a letter: ");
            guess = scanner.nextLine();
            if (guess.length() != 1 || guess.equals(" ") || guess.matches("[^A-Öa-ö ]")) {
                System.out.println("Incorrect input. Please try again.");
            }
        } while (guess.length() != 1 || guess.equals(" ") || guess.matches("[^A-Za-z ]"));
        
        return guess.charAt(0);
    }
}
