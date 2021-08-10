package com.github.anjeyy.adventcode.seven;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

class Graph {

    private final Map<Node, List<Edge>> adjacencyMap;

    Graph() {
        this.adjacencyMap = new HashMap<>();
    }

    void addNeighbor(Node node, Edge neighbor) {
        if (adjacencyMap.containsKey(node)) {
            List<Edge> edges = adjacencyMap.get(node);
            edges.add(neighbor);
        } else {
            List<Edge> edges = new ArrayList<>();
            edges.add(neighbor);
            adjacencyMap.put(node, edges);
        }
    }

    int countNodesFrom(Node start) {
        // breadth first search
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        Set<Node> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            visited.add(currentNode);
            List<Edge> neighbors = adjacencyMap.get(currentNode);
            if (neighbors != null && !neighbors.isEmpty()) {
                neighbors.stream().map(Edge::getDestination).forEach(queue::offer);
            }
        }
        return visited.size() - 1;
    }

    int countRecursivelyFrom(Node start) {
        List<Edge> edges = findNeighbors(start);
        if (edges.isEmpty()) {
            return 0;
        }
        int result = 0;
        for (Edge currentEdge : edges) {
            int currentWeight = currentEdge.getWeight();
            Node neighbor = currentEdge.getSource();
            result = result + currentWeight + currentWeight * countRecursivelyFrom(neighbor);
        }
        return result;
    }

    private List<Edge> findNeighbors(Node node) {
        return adjacencyMap
            .values()
            .stream()
            .flatMap(Collection::stream)
            .filter(e -> e.getDestination().equals(node))
            .collect(Collectors.toList());
    }
}
