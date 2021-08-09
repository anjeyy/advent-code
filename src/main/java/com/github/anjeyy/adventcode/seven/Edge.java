package com.github.anjeyy.adventcode.seven;

import java.util.Objects;

class Edge {

    private final Node source;
    private final Node destination;

    static Edge with(Node source, Node destination) {
        return new Edge(source, destination);
    }

    private Edge(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
    }

    Node getSource() {
        return source;
    }

    Node getDestination() {
        return destination;
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
