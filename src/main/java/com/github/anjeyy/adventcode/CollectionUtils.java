package com.github.anjeyy.adventcode;

import java.util.ArrayList;
import java.util.Collection;
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
}
