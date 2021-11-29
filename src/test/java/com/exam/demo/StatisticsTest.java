package com.exam.demo;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    @Test
    public void test_groupByCount_emptyInput() {
        List<Colour> colours = new ArrayList<>();
        // colours.add(new Colour("Yellow"));

        Map<String, Integer> actualResult = Statistics.groupByCount(colours);

        assertTrue(actualResult.entrySet().isEmpty());
    }

    @Test
    public void test_groupByCount_standardInput() {
        List<Colour> colours = Arrays.asList(new Colour("Blue"));

        Map<String, Integer> actualResult = Statistics.groupByCount(colours);

        Map<String, Integer> expectedResult = new HashMap<>();
        expectedResult.put("Blue", 1);

        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_groupByCount_inputDataToAggregate() {
       List<Colour> colours = Arrays.asList(
                new Colour("Blue"),
                new Colour("Yellow"),
                new Colour("Blue")
        );

        Map<String, Integer> actualResult = Statistics.groupByCount(colours);

        Map<String, Integer> expectedResult = new HashMap<>();
        expectedResult.put("Blue", 2);
        expectedResult.put("Yellow", 1);

        assertEquals(actualResult, expectedResult);
    }
}