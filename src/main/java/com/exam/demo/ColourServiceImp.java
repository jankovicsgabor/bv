package com.exam.demo;

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

    private static final String COLOUR_KEY = "Colour";

    @Autowired
    private ColourRepository repository;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Colour> hashOperations;

    // This annotation makes sure that the method needs to be executed after
    // dependency injection is done to perform any initialization.
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
        repository.save(colour);
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
