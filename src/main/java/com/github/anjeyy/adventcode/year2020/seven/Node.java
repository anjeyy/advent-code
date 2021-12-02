package com.github.anjeyy.adventcode.year2020.seven;

import java.util.Objects;

class Node {

    private final String label;

    static Node from(String label) {
        return new Node(label);
    }

    private Node(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node)) {
            return false;
        }
        Node node = (Node) o;
        return label.equalsIgnoreCase(node.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
