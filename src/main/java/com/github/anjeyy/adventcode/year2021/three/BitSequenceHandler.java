package com.github.anjeyy.adventcode.year2021.three;

import com.github.anjeyy.adventcode.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class BitSequenceHandler {

    private final List<BitSequence> bitSequences;

    private BitSequenceHandler(List<String> diagnosticReport) {
        this.bitSequences = create(diagnosticReport);
    }

    private static List<BitSequence> create(List<String> diagnosticReport) {
        String[] rawBitSequences = createEmptyArrayWithSize(diagnosticReport);
        for (int i = 0; i < diagnosticReport.size(); i++) {
            String rawVerticalBits = diagnosticReport.get(i);
            for (int j = 0; j < rawVerticalBits.length(); j++) {
                rawBitSequences[j] = rawBitSequences[j] + rawVerticalBits.charAt(j);
            }
        }
        return Arrays.stream(rawBitSequences).map(BitSequence::from).collect(Collectors.toList());
    }

    private static String[] createEmptyArrayWithSize(List<String> diagnosticReport) {
        if (diagnosticReport.isEmpty()) {
            return new String[]{};
        }
        int size = diagnosticReport.get(0).length();
        String[] rawBitSequences = new String[size];
        Arrays.fill(rawBitSequences, Constants.EMPTY);
        return rawBitSequences;
    }

    static BitSequenceHandler from(List<String> diagnosticReport) {
        return new BitSequenceHandler(diagnosticReport);
    }

    long calculateGammaRate() {
        BitSequence gammaBit = calculateGammaBitSequence();
        return gammaBit.asDecimal();
    }

    long calculateEpsilonRate() {
        BitSequence gammaRate = calculateGammaBitSequence();
        BitSequence epsilonRate = gammaRate.negate();
        return epsilonRate.asDecimal();
    }

    private BitSequence calculateGammaBitSequence() {
        return bitSequences.stream()
                .map(GammaBitSelector::with)
                .map(GammaBitSelector::commonBit)
                .map(String::valueOf)
                .collect(Collectors.collectingAndThen(Collectors.joining(), BitSequence::from));
    }

}
