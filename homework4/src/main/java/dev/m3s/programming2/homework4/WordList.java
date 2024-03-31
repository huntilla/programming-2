package dev.m3s.programming2.homework4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordList {
    private final List<String> wordList;

    WordList() {
        wordList = new ArrayList<>();
    }

    WordList(String fileName) throws FileNotFoundException {
        wordList = new ArrayList<>();
        readFile(fileName);
    }

    private void readFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            wordList.add(scanner.nextLine().toLowerCase());
        }
    }

    public List<String> giveWords() {
        return wordList;
    }

    public WordList theWordsOfLength(int length) {
        WordList wordsOfLength = new WordList();
        if (!wordList.isEmpty()) {
            for (String word : wordList) {
                if (word.length() == length) {
                    wordsOfLength.wordList.add(word);
                }
            }
        }
        return wordsOfLength;
    }

    public WordList theWordsWithCharacters(String someString) {
        WordList wordsOfLength = theWordsOfLength(someString.length());
        WordList wordsWithCharacters = new WordList();
        if (!wordsOfLength.giveWords().isEmpty()) {
            for (String word : wordsOfLength.giveWords()) {
                int nonMatchCounter = 0;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) != someString.charAt(i) && someString.charAt(i) != '_') {
                        nonMatchCounter++;
                    }
                }
                if (nonMatchCounter == 0) {
                    wordsWithCharacters.wordList.add(word);
                }
            }
        }
        return wordsWithCharacters;
    }
}
