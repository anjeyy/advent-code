package com.github.anjeyy.adventcode.year2021.eight;

import com.github.anjeyy.adventcode.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class SignalEntry {

    private static final String DELIMITER = " \\| ";

    private final List<Signal> signals;
    private final List<Output> outputs;

    SignalEntry(String rawEntry) {
        this.signals = extractSignal(rawEntry);
        this.outputs = extractOutput(rawEntry);
    }

    private List<Signal> extractSignal(String rawEntry) {
        String rawSignalString = rawEntry.split(DELIMITER)[0];
        String[] rawSignals = whitespaceSplit(rawSignalString);
        return Arrays.stream(rawSignals)
                     .filter(s -> !s.isBlank())
                     .map(Signal::of)
                     .collect(Collectors.toList());
    }

    private List<Output> extractOutput(String rawEntry) {
        String rawOutputString = rawEntry.split(DELIMITER)[1];
        String[] rawOutputs = whitespaceSplit(rawOutputString);
        return Arrays.stream(rawOutputs)
                     .filter(s -> !s.isBlank())
                     .map(Output::of)
                     .collect(Collectors.toList());
    }

    private static String[] whitespaceSplit(String string) {
        return string.split(Constants.WHITESPACE);
    }

    List<Output> getOutputs() {
        return this.outputs;
    }

    long decodeOutput() {
        encodeDigits();

        StringBuilder result = new StringBuilder();
        for (Output currentOutput : outputs) {
            for (Signal currentSignal : signals) {
                boolean signalMatch = currentOutput.matchesSignal(currentSignal);
                if (signalMatch) {
                    result.append(currentSignal.getDigit().asString());
                    break;
                }
            }
        }
        long sum = Long.parseLong(result.toString());
        return sum;
    }

    private void encodeDigits() {
        List<Signal> unknownDigits = decodeUniqueDigits();
        decodeThree(unknownDigits);
        decodeSixNineCombination(unknownDigits);
        decodeTwoFiveCombination(unknownDigits);
        verifyDecodedSignals();
    }

    private List<Signal> decodeUniqueDigits() {
        List<Signal> unknownDigits = new ArrayList<>();
        for (Signal currentSignal : signals) {
            boolean lengthDecoded = currentSignal.encodeUniqueLength();
            if (!lengthDecoded) {
                unknownDigits.add(currentSignal);
            }
        }
        return unknownDigits;
    }

    private void decodeThree(List<Signal> unknownDigits) {
        Predicate<Signal> twoFiveLength = s -> s.length() == Digit._2.getLength();
        List<Signal> relevantSignals = unknownDigits.stream().filter(twoFiveLength).collect(Collectors.toList());
        Signal oneSignal = signals.stream().filter(s -> s.length() == Digit._1.getLength()).findFirst().orElseThrow();
        for (Signal currentRelevantSignal : relevantSignals) {
            boolean digitOneMatches = currentRelevantSignal.matchesPartly(oneSignal);
            if (digitOneMatches) {
                currentRelevantSignal.setDigit(Digit._3);
                unknownDigits.remove(currentRelevantSignal);
                return;
            }
        }
    }

    private void decodeSixNineCombination(List<Signal> unknownDigits) {
        Signal fourSignal = findUniqueSignal(Digit._4);
        List<Signal> sixLengthDigits =
            unknownDigits.stream().filter(s -> s.length() == Digit._9.getLength()).collect(Collectors.toList());
        Signal nineSignal = null;
        for (Signal currentSignal : sixLengthDigits) {
            boolean isNineDigitMatch = currentSignal.matchesPartly(fourSignal);
            if (isNineDigitMatch) {
                currentSignal.setDigit(Digit._9);
                nineSignal = currentSignal;
                unknownDigits.remove(currentSignal);
            }
        }
        sixLengthDigits.remove(nineSignal);
        decodeZeroSixCombination(sixLengthDigits);
        unknownDigits.removeAll(sixLengthDigits);
    }

    private void decodeZeroSixCombination(List<Signal> sixLengthDigits) {
        if (sixLengthDigits.size() != 2) {
            throw new IllegalStateException("Too many entries for six length digits.");
        }
        Signal oneSignal = findUniqueSignal(Digit._1);
        for (Signal currentSignal : sixLengthDigits) {
            boolean isZero = currentSignal.matchesPartly(oneSignal);
            if (isZero) {
                currentSignal.setDigit(Digit._0);
            } else {
                currentSignal.setDigit(Digit._6);
            }
        }
    }

    private void decodeTwoFiveCombination(List<Signal> unknownDigits) {
        if (unknownDigits.size() != 2) {
            throw new IllegalStateException("Too many entries for six length digits.");
        }
        Signal sixSignal = findUniqueSignal(Digit._6);
        for (Signal currentSignal : unknownDigits) {
            boolean isFive = sixSignal.matchesPartly(currentSignal);
            if (isFive) {
                currentSignal.setDigit(Digit._5);
            } else {
                currentSignal.setDigit(Digit._2);
            }
        }
        unknownDigits.clear();
    }

    private Signal findUniqueSignal(Digit digit) {
        return signals.stream().filter(s -> s.getDigit() == digit).findFirst().orElseThrow();
    }

    private void verifyDecodedSignals() {
        Optional<Digit> encodedDigit = signals.stream().map(Signal::getDigit).filter(Objects::isNull).findAny();
        if (encodedDigit.isPresent()) {
            throw new IllegalStateException("Encoded digit found.");
        }
    }
}
