package com.github.anjeyy.adventcode.four;

import com.github.anjeyy.adventcode.Constants;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Passport {

    private final String birthYear;
    private final String issueYear;
    private final String expirationYear;
    private final String height;
    private final String hairColor;
    private final String eyeColor;
    private final String passportId;
    private final String countryId;

    static Passport from(String rawInput) {
        return new Passport(rawInput);
    }

    private Passport(String rawInput) {
        List<String> atomicAttributes = extractRawInput(rawInput);
        this.birthYear = Attributes.BIRTH_YEAR.extract(atomicAttributes);
        this.issueYear = Attributes.ISSUE_YEAR.extract(atomicAttributes);
        this.expirationYear = Attributes.EXPIRATION_YEAR.extract(atomicAttributes);
        this.height = Attributes.HEIGHT.extract(atomicAttributes);
        this.hairColor = Attributes.HAIR_COLOR.extract(atomicAttributes);
        this.eyeColor = Attributes.EYE_COLOR.extract(atomicAttributes);
        this.passportId = Attributes.PASSPORT_ID.extract(atomicAttributes);
        this.countryId = Attributes.COUNTRY_ID.extract(atomicAttributes);
    }

    private static List<String> extractRawInput(String rawInput) {
        String[] splitInput = rawInput.split(Constants.WHITESPACE);
        return Arrays.stream(splitInput)
                     .map(str -> str.split(Constants.NEW_LINE))
                     .flatMap(Arrays::stream)
                     .collect(Collectors.toList());
    }

    boolean isValid() {
        return birthYear != null
            && issueYear != null
            && expirationYear != null
            && height != null
            && hairColor != null
            && eyeColor != null
            && passportId != null;
    }

    @Override
    public String toString() {
        return "Passport{" +
            "birthYear='" + birthYear + '\'' +
            ", issueYear='" + issueYear + '\'' +
            ", expirationYear='" + expirationYear + '\'' +
            ", height='" + height + '\'' +
            ", hairColor='" + hairColor + '\'' +
            ", eyeColor='" + eyeColor + '\'' +
            ", passportId='" + passportId + '\'' +
            ", countryId='" + countryId + '\'' +
            '}';
    }

    private enum Attributes {
        BIRTH_YEAR("byr"),
        ISSUE_YEAR("iyr"),
        EXPIRATION_YEAR("eyr"),
        HEIGHT("hgt"),
        HAIR_COLOR("hcl"),
        EYE_COLOR("ecl"),
        PASSPORT_ID("pid"),
        COUNTRY_ID("cid");

        private final String abbreviation;

        Attributes(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        private String extract(List<String> splitInput) {
            return splitInput.stream()
                             .filter(str -> str.contains(abbreviation))
                             .map(str -> str.split(Constants.COLON)[1])
                             .findFirst()
                             .orElse(null);
        }
    }
}
