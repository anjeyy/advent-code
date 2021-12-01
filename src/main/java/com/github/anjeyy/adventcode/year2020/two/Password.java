package com.github.anjeyy.adventcode.year2020.two;

import com.github.anjeyy.adventcode.Constants;

class Password {

    private final String phrase;

    private Password(String phrase) {
        this.phrase = extractPasswordPhrase(phrase);
    }

    String getPhrase() {
        return phrase;
    }

    static Password from(String phrase) {
        return new Password(phrase);
    }

    private String extractPasswordPhrase(String phrase) {
        return phrase.split(Constants.WHITESPACE)[2];
    }

    @Override
    public String toString() {
        return "Password{" + "phrase='" + phrase + '\'' + '}';
    }
}
