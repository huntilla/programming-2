package dev.m3s.programming2.homework4;

import java.util.*;

public class Hangman {
    private final String wordToBeGuessed;
    private int guessesLeft;
    private final List<Character> guessedCharacters = new ArrayList<>();
    private final List<Character> correctGuessedCharacters = new ArrayList<>();
    private final List<Character> wordAsList = new ArrayList<>();
    private int wordLength;
    Hangman(WordList wordList, int guesses) {
        Random rand = new Random();
        this.guessesLeft = guesses;
        this.wordToBeGuessed = wordList.giveWords().get(rand.nextInt(wordList.giveWords().size()));
        calculateWordLength();
        wordToList(wordToBeGuessed);
    }

    public boolean guess(Character c) {
        if (!guessedCharacters.contains(c)) {
            guessedCharacters.add(c);
        }
        if (wordToBeGuessed.indexOf(c) != -1) {
            correctGuessedCharacters.add(c);
            return true;
        }
        guessesLeft--;
        return false;
    }

    public List<Character> guesses() {
        return guessedCharacters;
    }

    public int guessesLeft() {
        return guessesLeft;
    }

    public String word() {
        return wordToBeGuessed;
    }

    private void wordToList(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!wordAsList.contains(word.charAt(i))) {
                wordAsList.add(word.charAt(i));
            }
        }
        Collections.sort(wordAsList);
    }

    public boolean theEnd() {
        if (guessesLeft <= 0) {
            return true;
        }
        return new HashSet<>(correctGuessedCharacters).containsAll(wordAsList);
    }

    public int getWordLength() {
        return wordLength;
    }

    private void calculateWordLength() {
        this.wordLength = wordToBeGuessed.length();
    }

    public String maskedWord() {
        StringBuilder maskedWord = new StringBuilder();
        for (Character c : wordToBeGuessed.toCharArray()) {
            if (correctGuessedCharacters.contains(c)) {
                maskedWord.append(c);
            }
            else {
                maskedWord.append('*');
            }
            maskedWord.append(" ");
        }
        return maskedWord.toString().trim();
    }
}
