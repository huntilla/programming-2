package dev.m3s.programming2.homework4;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static WordList wordList;
    private final static int amountOfGuesses = 4;
    private static Hangman game;
    public static void main(String[] args) throws FileNotFoundException {
        try {
            wordList = new WordList("/home/kalle/git/group-0204-project/homework4/words.txt");
            game = new Hangman(wordList, amountOfGuesses);
            playGame();
        } catch (FileNotFoundException e) {
            System.out.println("Word file could not be found!");
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
            System.out.printf("The hidden word was: \"%s\"", game.word());
        }
        if (game.guessesLeft() != 0) {
            System.out.println("Congratulations! You won!!!");
            System.out.printf("The hidden word was: \"%s\"", game.word());
        }
    }

    private static char userInput() {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Guess a letter: ");
        String guess = scanner.nextLine();
        while (guess.length() != 1 || guess.equals(" ") || guess.matches("[^A-Öa-ö0-9 ]")) {
            System.out.println("Incorrect input. Please try again.");
            System.out.println("Guess a letter: ");
            guess = scanner.nextLine();
        }
        return guess.charAt(0);
    }
}
