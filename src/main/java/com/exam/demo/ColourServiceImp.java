package com.exam.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ColourServiceImp implements ColourService {

    private static final Logger logger = LoggerFactory.getLogger(ColourServiceImp.class);

    private static final String COLOUR_KEY = "Colour";

    @Autowired
    private ColourRepository repository;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Colour> hashOperations;

    @PostConstruct
    private void intializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();

        // In case of the external cache service not empty
        hashOperations.entries(COLOUR_KEY).clear();

        // load cache from database
        for (Colour colour : repository.findAll()) {
            hashOperations.put(COLOUR_KEY, colour.getId(), colour);
        }
    }

    @Override
    public void save(final Colour colour) {
        logger.info("Service will save incoming colour ({}) to database...", colour.colourName);
        repository.save(colour);

        logger.info("and put it into cache.");
        hashOperations.put(COLOUR_KEY, colour.getId(), colour);
    }

    @Override
    public List<Colour> findAll() {
        Map<String, Colour> map = hashOperations.entries(COLOUR_KEY);

        List<Colour> colours =
                map.entrySet()
                        .stream()
                        .map(e -> e.getValue())
                        .collect(Collectors.toList());
        return colours;
    }
}
