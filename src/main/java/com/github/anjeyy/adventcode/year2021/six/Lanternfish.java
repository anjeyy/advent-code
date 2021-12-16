package com.github.anjeyy.adventcode.year2021.six;

class Lanternfish {

    private int internalTimer;

    Lanternfish(int internalTimer) {
        this.internalTimer = internalTimer;
    }

    boolean simulateDay() {
        if (isReady()) {
            reset();
            return true;
        }
        internalTimer--;
        return false;
    }

    private boolean isReady() {
        return internalTimer == 0;
    }

    private void reset() {
        internalTimer = 6;
    }
}
