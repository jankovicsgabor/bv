package com.exam.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@RestController
@RequestMapping("/api")
public class StatisticsController {

    @Autowired
    ColourService colourService;

    @GetMapping("/")
    public String getColourStatistics() throws JsonProcessingException {
        List<Colour> colours = colourService.findAll();

        Map<String, Integer> statistic = Statistics.groupByCount(colours);

        String json = new ObjectMapper().writeValueAsString(statistic);

        return json;
    }

}
