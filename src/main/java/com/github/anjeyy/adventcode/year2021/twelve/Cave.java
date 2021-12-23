package com.github.anjeyy.adventcode.year2021.twelve;

import java.util.Objects;

class Cave {

    private final String name;
    private final boolean small;

    private Cave(String name) {
        this.name = Objects.requireNonNull(name, "Name for cave is necessary.");
        this.small = determineSize(name);
    }

    private static boolean determineSize(String name) {
        for (char currentChar : name.toCharArray()) {
            if (Character.isUpperCase(currentChar)) {
                return false;
            }
        }
        return true;
    }

    static Cave of(String name) {
        return new Cave(name);
    }

    boolean isNotSmall() {
        return !isSmall();
    }

    boolean isSmall() {
        return small;
    }

    boolean isEnd() {
        Cave end = Cave.of("end");
        return this.equals(end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cave)) {
            return false;
        }
        Cave cave = (Cave) o;
        return name.equals(cave.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Cave{" +
            "name='" + name + '\'' +
            ", small=" + small +
            '}';
    }
}
