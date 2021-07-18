package com.github.anjeyy.adventcode.two;

class PasswordParser {

    private final Password password;
    private final PasswordPolicy passwordPolicy;

    private PasswordParser(String phrase) {
        this.password = Password.from(phrase);
        this.passwordPolicy = PasswordPolicy.from(phrase);
    }

    static PasswordParser from(String phrase) {
        return new PasswordParser(phrase);
    }

    boolean isValid() {
        final String passwordAsString = password.getPhrase();
        final char mandatoryChar = passwordPolicy.getMandatoryCharacter();
        int occurrence = 0;

        for (int i = 0; i < passwordAsString.length(); i++) {
            char currentChar = passwordAsString.charAt(i);
            if (currentChar == mandatoryChar) {
                occurrence++;
            }
        }
        return passwordPolicy.getMinOccurrence() <= occurrence && occurrence <= passwordPolicy.getMaxOccurrence();
    }
}
