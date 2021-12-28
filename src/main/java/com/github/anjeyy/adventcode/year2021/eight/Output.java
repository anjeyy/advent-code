package com.github.anjeyy.adventcode.year2021.eight;

class Output {

    private final String encoded;

    Output(String encoded) {
        this.encoded = encoded;
    }

    static Output of(String encoded) {
        return new Output(encoded);
    }

    boolean matchesSignal(Signal signal) {
        if (signal.length() != encoded.length()) {
            return false;
        }
        String rawSignal = signal.getRawSignal();
        for (char currentChar : rawSignal.toCharArray()) {
            boolean isPart = encoded.indexOf(currentChar) != -1;
            if (!isPart) {
                return false;
            }
        }
        return true;
    }

    boolean isUniqueDigit() {
        return Digit.encodeViaUniqueLength(encoded.length()).isPresent();
    }

    @Override
    public String toString() {
        return "Output{" +
            "encoded='" + encoded + '\'' +
            '}';
    }
}
