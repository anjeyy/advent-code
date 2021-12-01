package com.github.anjeyy.adventcode.year2020.two;

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

    /**
     * Password policy from the <b>first</b> part of puzzle <i>Day 2: Password Philosophy</i>.
     *
     * @return valid passwords according to the first part of the puzzle
     * @see <a href="https://adventofcode.com/2020/day/2">Day 2: Password Philosophy - Part I</a>
     */
    boolean isValidPartOne() {
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

    /**
     * Password policy from the <b>second</b> part of puzzle <i>Day 2: Password Philosophy</i>.
     *
     * @return valid passwords according to the first part of the puzzle
     * @see <a href="https://adventofcode.com/2020/day/2#part2">Day 2: Password Philosophy - Part II</a>
     */
    boolean isValidPartTwo() {
        final String passwordAsString = password.getPhrase();
        final char mandatoryChar = passwordPolicy.getMandatoryCharacter();
        final int minOccurrence = passwordPolicy.getMinOccurrence();
        final int maxOccurrence = passwordPolicy.getMaxOccurrence();

        boolean matchingFirstPosition =
            minOccurrence >= 0 && passwordAsString.charAt(minOccurrence - 1) == mandatoryChar;
        boolean matchingSecondPosition =
            maxOccurrence <= passwordAsString.length() && passwordAsString.charAt(maxOccurrence - 1) == mandatoryChar;
        return matchingFirstPosition ^ matchingSecondPosition;
    }
}
