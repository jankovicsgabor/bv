package com.exam.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@TestConfiguration
public class TestRedisConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestRedisConfiguration.class);

    private RedisServer redisServer;

    public TestRedisConfiguration(RedisProperties redisProperties) throws IOException {
        LOGGER.info(String.format("Redis port from properties: %d", redisProperties.getPort()));
        this.redisServer = new RedisServer(redisProperties.getPort());
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
        LOGGER.info(String.format("Redis has been started"));
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
