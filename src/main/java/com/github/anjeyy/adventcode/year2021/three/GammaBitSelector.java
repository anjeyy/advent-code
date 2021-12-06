package com.github.anjeyy.adventcode.year2021.three;

class GammaBitSelector {

    private final BitSequence bitSequence;

    private GammaBitSelector(BitSequence bitSequence) {
        this.bitSequence = bitSequence;
    }

    static GammaBitSelector with(BitSequence bitSequence) {
        return new GammaBitSelector(bitSequence);
    }

    int commonBit() {
        String rawBitSequence = bitSequence.getBitSequence();
        char[] bitArray = rawBitSequence.toCharArray();
        int zeroCounter = countZeroBits(bitArray);

        int bitSequenceLength = bitArray.length;
        int oneCounter = bitSequenceLength - zeroCounter;
        return (oneCounter > zeroCounter) ? Bit._1.getAsInt() : Bit._0.getAsInt();
    }

    private int countZeroBits(char[] bitArray) {
        int zeroCounter = 0;
        for (char currentBit : bitArray) {
            if (Bit.isZero(currentBit)) {
                zeroCounter++;
            }
        }
        return zeroCounter;
    }
}
