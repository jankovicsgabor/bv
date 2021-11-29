package com.exam.demo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statistics {

    public static Map<String, Integer> groupByCount(List<Colour> colours) {
        Map<String, Integer> statistic =
                colours
                        .stream()
                        .map(c -> c.getColourName()).
                        collect(Collectors.toMap(s -> s, s -> 1, Integer::sum));
        return statistic;
    }

}
