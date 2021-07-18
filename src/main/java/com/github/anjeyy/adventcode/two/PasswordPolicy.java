package com.github.anjeyy.adventcode.two;

class PasswordPolicy {

    private static final String MINUS = "-";

    private final int minOccurrence;
    private final int maxOccurrence;
    private final char mandatoryCharacter;

    static PasswordPolicy from(String policy) {
        return new PasswordPolicy(policy);
    }

    private PasswordPolicy(String policy) {
        this.minOccurrence = extractMinOccurrence(policy);
        this.maxOccurrence = extractMaxOccurrence(policy);
        this.mandatoryCharacter = extractMandatoryCharacter(policy);
    }

    int getMinOccurrence() {
        return minOccurrence;
    }

    int getMaxOccurrence() {
        return maxOccurrence;
    }

    char getMandatoryCharacter() {
        return mandatoryCharacter;
    }

    private int extractMinOccurrence(String policy) {
        String occurrence = policy.split(Main.WHITESPACE)[0];
        String rawMinOccurrence = occurrence.split(MINUS)[0];
        return Integer.parseInt(rawMinOccurrence);
    }

    private int extractMaxOccurrence(String policy) {
        String occurrence = policy.split(Main.WHITESPACE)[0];
        String rawMaxOccurrence = occurrence.split(MINUS)[1];
        return Integer.parseInt(rawMaxOccurrence);
    }

    private char extractMandatoryCharacter(String policy) {
        String rawMandatoryCharacter = policy.split(Main.WHITESPACE)[1];
        return rawMandatoryCharacter.charAt(0);
    }

    @Override
    public String toString() {
        return "PasswordPolicy{" +
            "minOccurrence=" + minOccurrence +
            ", maxOccurrence=" + maxOccurrence +
            ", mandatoryCharacter=" + mandatoryCharacter +
            '}';
    }
}
