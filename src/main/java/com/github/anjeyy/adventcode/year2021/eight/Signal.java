package com.github.anjeyy.adventcode.year2021.eight;

import java.util.Objects;
import java.util.Optional;

class Signal {

    private final String rawSignal;

    private Digit digit;

    private Signal(String rawSignal) {
        this.rawSignal = rawSignal;
    }

    static Signal of(String rawSignal) {
        return new Signal(rawSignal);
    }

    String getRawSignal() {
        return rawSignal;
    }

    Digit getDigit() {
        return digit;
    }

    void setDigit(Digit digit) {
        this.digit = digit;
    }

    int length() {
        return rawSignal.length();
    }

    boolean matchesPartly(Signal subSignal) {
        for (char currentChar : subSignal.rawSignal.toCharArray()) {
            boolean isPart = rawSignal.indexOf(currentChar) != -1;
            if (!isPart) {
                return false;
            }
        }
        return true;
    }

    boolean encodeUniqueLength() {
        int signalLength = rawSignal.length();
        Optional<Digit> uniqueDigit = Digit.encodeViaUniqueLength(signalLength);
        uniqueDigit.ifPresent(value -> this.digit = value);
        return uniqueDigit.isPresent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Signal)) {
            return false;
        }
        Signal signal = (Signal) o;
        return rawSignal.equals(signal.rawSignal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawSignal);
    }

    @Override
    public String toString() {
        return "Signal{" +
            "rawSignal='" + rawSignal + '\'' +
            ", digit=" + digit +
            '}';
    }
}
