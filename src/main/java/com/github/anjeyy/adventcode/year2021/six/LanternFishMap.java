package com.github.anjeyy.adventcode.year2021.six;

import java.util.HashMap;

class LanternFishMap extends HashMap<Long, Long> {

    LanternFishMap() {
        super();
    }

    void increment(Long key) {
        Long value = incrementWith(key, 1L);
        super.put(key, value);
    }

    void increment(Long key, Long value) {
        Long actualValue = incrementWith(key, value);
        super.put(key, actualValue);
    }

    private Long incrementWith(Long key, Long value) {
        return super.containsKey(key) ? super.get(key) + value : value;
    }

}
