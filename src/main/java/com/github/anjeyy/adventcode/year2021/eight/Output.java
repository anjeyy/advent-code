package com.github.anjeyy.adventcode.year2021.eight;

class Output {

    private final String encoded;

    Output(String encoded) {
        this.encoded = encoded;
    }

    static Output of(String encoded) {
        return new Output(encoded);
    }
}
