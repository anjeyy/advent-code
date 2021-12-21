package com.github.anjeyy.adventcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

public class CollectionUtils {

    private CollectionUtils() {
        throw new UnsupportedOperationException("No instance allowed.");
    }

    public static <E> boolean isNotEmpty(Collection<E> collection) {
        return !collection.isEmpty();
    }

    public static <E> List<E> createDeepCopy(Collection<E> collection){
        List<E> result = new ArrayList<>();
        collection.forEach(result::add);
        return result;
    }

    public static <E> Deque<E> createDequeDeepCopy(Deque<E> deque){
        Deque<E> result = new ArrayDeque<>();
        while (isNotEmpty(deque)){
            result.push(deque.removeLast());
        }
        return result;
    }
}
