package com.github.anjeyy.adventcode.year2021.twelve;

import com.github.anjeyy.adventcode.CollectionUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Graph {

    private final Map<Cave, Set<Cave>> adjacencyMap = new HashMap<>();

    private Graph(List<String> rawMap) {
        initializeCaveMap(rawMap);
    }

    static Graph from(List<String> rawMap) {
        return new Graph(rawMap);
    }

    private void initializeCaveMap(List<String> rawMap) {
        for (String edge : rawMap) {
            String[] splitEdge = edge.split("-");
            if (splitEdge.length != 2) {
                throw new IllegalArgumentException("Invalid format: " + edge);
            }
            Cave start = Cave.of(splitEdge[0]);
            Cave end = Cave.of(splitEdge[1]);
            updateNeighbours(start, end);
            updateNeighbours(end, start);
        }
    }

    private void updateNeighbours(Cave start, Cave end) {
        Set<Cave> alreadyContained = adjacencyMap.get(start);
        if (alreadyContained == null) {
            Set<Cave> neighbours = new HashSet<>();
            neighbours.add(end);
            adjacencyMap.put(start, neighbours);
        } else {
            alreadyContained.add(end);
        }
    }

    long determineDistinctPaths() {
        List<List<Cave>> paths = new ArrayList<>();
        List<Deque<Cave>> startingStack = createStartingStack();
        Queue<Deque<Cave>> visiting = new ArrayDeque<>(startingStack);
        while (CollectionUtils.isNotEmpty(visiting)) {
            Deque<Cave> currentCavePath = visiting.poll();
            Cave currentCave = currentCavePath.peek();
            Set<Cave> neighbourCaves = adjacencyMap.get(currentCave);
            for (Cave neighbourCave : neighbourCaves) {
                if (neighbourCave.isEnd()) {
                    Deque<Cave> caveCopy = CollectionUtils.createDequeDeepCopy(currentCavePath);
                    caveCopy.push(neighbourCave);
                    List<Cave> result = new ArrayList<>(caveCopy);
                    paths.add(result);
                } else if (neighbourCave.isSmall() && currentCavePath.contains(neighbourCave)
                    || neighbourCave.equals(Cave.of("start"))) {
                    //skip cave - small and already contained
                } else {
                    Deque<Cave> caveCopy = CollectionUtils.createDequeDeepCopy(currentCavePath);
                    caveCopy.push(neighbourCave);
                    visiting.offer(caveCopy);
                }
            }
        }
        return paths.size();
    }

    private List<Deque<Cave>> createStartingStack() {
        Cave start = Cave.of("start");
        Set<Cave> neighbours = adjacencyMap.get(start);
        List<Deque<Cave>> result = new ArrayList<>();
        for (Cave neighbour : neighbours) {
            Deque<Cave> caveStack = new ArrayDeque<>();
            caveStack.push(start);
            caveStack.push(neighbour);
            result.add(caveStack);
        }
        return result;
    }
}
