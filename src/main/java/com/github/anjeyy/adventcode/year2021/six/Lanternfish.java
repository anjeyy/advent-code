package com.github.anjeyy.adventcode.year2021.six;

class Lanternfish {

    private int internalTimer;

    private Lanternfish(int internalTimer) {
        this.internalTimer = internalTimer;
    }

    static Lanternfish of(int internalTimer) {
        return new Lanternfish(internalTimer);
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
