package com.github.anjeyy.adventcode.year2021.three;

import com.github.anjeyy.adventcode.CollectionUtils;
import com.github.anjeyy.adventcode.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BitSequenceHandler {

    private final List<BitSequence> bitSequences;

    private BitSequenceHandler(List<String> diagnosticReport) {
        this.bitSequences = create(diagnosticReport);
    }

    private static List<BitSequence> create(List<String> diagnosticReport) {
        String[] rawBitSequences = createEmptyArrayWithSize(diagnosticReport);
        for (String rawVerticalBits : diagnosticReport) {
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

    long calculateOxygenRate() {
        List<BitSequence> zeroBits = new ArrayList<>();
        List<BitSequence> oneBits = new ArrayList<>();
        List<BitSequence> result = transformToVerticalBitSequence();
        int bitPosition = 0;
        while (result.size() != 1) {
            StringBuilder sb = new StringBuilder();
            for (BitSequence bitSequence : result) {
                String currentBitSequence = bitSequence.getBitSequence();
                Character charAt = currentBitSequence.charAt(bitPosition);
                String valueOf = String.valueOf(charAt);
                sb.append(valueOf);
            }
            String rawHorizontalBitSequence = sb.toString();

            for (int i = 0; i < rawHorizontalBitSequence.length(); i++) {
                char currentBit = rawHorizontalBitSequence.charAt(i);
                BitSequence currentBitSequence = result.get(i);
                if (Bit.isZero(currentBit)) {
                    zeroBits.add(currentBitSequence);
                } else if (Bit.isOne(currentBit)) {
                    oneBits.add(currentBitSequence);
                } else {
                    throw new IllegalStateException("Bit could not be identified.");
                }
            }
            bitPosition++;
            result.clear();
            if (zeroBits.size() > oneBits.size()) {
                result.addAll(zeroBits);
            } else {
                result.addAll(oneBits);
            }
            oneBits.clear();
            zeroBits.clear();
        }

        return result.stream()
                .findFirst()
                .map(BitSequence::asDecimal)
                .orElseThrow();
    }

    long calculateCo2ScrubberRate() {
        List<BitSequence> zeroBits = new ArrayList<>();
        List<BitSequence> oneBits = new ArrayList<>();
        List<BitSequence> result = transformToVerticalBitSequence();
        int bitPosition = 0;
        while (result.size() != 1) {
            StringBuilder sb = new StringBuilder();
            for (BitSequence bitSequence : result) {
                String currentBitSequence = bitSequence.getBitSequence();
                Character charAt = currentBitSequence.charAt(bitPosition);
                String valueOf = String.valueOf(charAt);
                sb.append(valueOf);
            }
            String rawHorizontalBitSequence = sb.toString();

            for (int i = 0; i < rawHorizontalBitSequence.length(); i++) {
                char currentBit = rawHorizontalBitSequence.charAt(i);
                BitSequence currentBitSequence = result.get(i);
                if (Bit.isZero(currentBit)) {
                    zeroBits.add(currentBitSequence);
                } else if (Bit.isOne(currentBit)) {
                    oneBits.add(currentBitSequence);
                } else {
                    throw new IllegalStateException("Bit could not be identified.");
                }
            }
            bitPosition++;
            result.clear();
            if (zeroBits.size() <= oneBits.size()) {
                result.addAll(zeroBits);
            } else {
                result.addAll(oneBits);
            }
            oneBits.clear();
            zeroBits.clear();
        }

        return result.stream()
                .findFirst()
                .map(BitSequence::asDecimal)
                .orElseThrow();
    }

    private List<BitSequence> transformToVerticalBitSequence() {
        int rawFirstBitSequenceLength = bitSequences.stream()
                .findFirst()
                .map(BitSequence::length)
                .orElseThrow();
        List<BitSequence> result = CollectionUtils.createDeepCopy(bitSequences);
        return IntStream.range(0, rawFirstBitSequenceLength)
                .boxed()
                .map(this::extractVerticalBitSequence)
                .collect(Collectors.toList());
    }

    private List<BitSequence> extractVerticalBitSequence(List<Integer> indices) {
        return indices.stream()
                .map(this::extractVerticalBitSequence)
                .collect(Collectors.toList());
    }

    private BitSequence extractVerticalBitSequence(int index) {
        StringBuilder result = new StringBuilder();
        for (BitSequence currentBitSequence : bitSequences) {
            String rawCurrentBitSequence = currentBitSequence.getBitSequence();
            if (rawCurrentBitSequence.length() < index) {
                throw new IllegalStateException("Bit sequence is not long enough.");
            }
            result.append(rawCurrentBitSequence.charAt(index));
        }
        String rawVerticalBitSequence = result.toString();
        return BitSequence.from(rawVerticalBitSequence);
    }

    private int getSequenceLength() {
        return bitSequences.stream()
                .findAny()
                .map(BitSequence::getBitSequence)
                .map(String::length)
                .orElseThrow();
    }

}
