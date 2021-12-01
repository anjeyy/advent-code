package com.github.anjeyy.adventcode.year2020.seven;

import java.util.Objects;

class Edge {

    private final Node source;
    private final Node destination;
    private final int weight;

    static Edge with(Node source, Node destination, int weight) {
        return new Edge(source, destination, weight);
    }

    private Edge(Node source, Node destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    Node getSource() {
        return source;
    }

    Node getDestination() {
        return destination;
    }

    int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge edge = (Edge) o;
        return source.equals(edge.source) && destination.equals(edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }
}
