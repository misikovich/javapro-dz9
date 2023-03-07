package org.example;

import java.util.List;

public class RussianNaziDetector {
    private static RussianNaziDetector russianNaziDetector;

    private RussianNaziDetector() {}
    public boolean analyze(String message) {
        List<Character> russianCharacters = List.of('ы', 'ъ', 'ё', 'э');

        for (Character russianCharacter : russianCharacters) {
            if (message.toLowerCase().contains(russianCharacter.toString())) return true;
        }

        return false;
    }

    public static RussianNaziDetector getRussianNaziDetector() {
        if (russianNaziDetector == null) russianNaziDetector = new RussianNaziDetector();
        return russianNaziDetector;
    }
}
