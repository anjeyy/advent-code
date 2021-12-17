package com.github.anjeyy.adventcode.year2021.eight;

import com.github.anjeyy.adventcode.Constants;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class SignalEntry {

    private static final String DELIMITER = " \\| ";

    private final List<Signal> signal;
    private final List<Output> output;

    SignalEntry(String rawEntry) {
        this.signal = extractSignal(rawEntry);
        this.output = extractOutput(rawEntry);
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

    List<Output> getOutput() {
        return this.output;
    }
}
