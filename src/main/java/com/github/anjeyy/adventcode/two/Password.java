package com.github.anjeyy.adventcode.two;

class Password {

    private final String phrase;

    private Password(String phrase) {
        this.phrase = extractPasswordPhrase(phrase);
    }

    public String getPhrase() {
        return phrase;
    }

    static Password from(String phrase) {
        return new Password(phrase);
    }

    private String extractPasswordPhrase(String phrase) {
        return phrase.split(Main.WHITESPACE)[2];
    }

    @Override
    public String toString() {
        return "Password{" +
            "phrase='" + phrase + '\'' +
            '}';
    }
}
