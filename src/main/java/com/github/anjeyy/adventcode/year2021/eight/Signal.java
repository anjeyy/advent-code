package com.github.anjeyy.adventcode.year2021.eight;

class Signal {

    private final String rawSignal;

    Signal(String rawSignal) {
        this.rawSignal = rawSignal;
    }

    static Signal of(String rawSignal) {
        return new Signal(rawSignal);
    }

}
