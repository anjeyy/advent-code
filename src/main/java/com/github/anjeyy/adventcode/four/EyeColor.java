package com.github.anjeyy.adventcode.four;

enum EyeColor {
    AMB("amb"),
    BLUE("blu"),
    BROWN("brn"),
    GRAY("gry"),
    GREEN("grn"),
    HAZEL("hzl"),
    OTHER("oth");

    private final String colorAbbreviation;

    EyeColor(String colorAbbreviation) {
        this.colorAbbreviation = colorAbbreviation;
    }

    public String getColorAbbreviation() {
        return colorAbbreviation;
    }
}