package com.exam.demo;

import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.io.IOException;

@Configuration
public class MongoDbTestConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbTestConfiguration.class);

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    @Value(value="${spring.data.mongodb.host}")
    private String MONGO_HOST;

    @Value(value="${spring.data.mongodb.port}")
    private Integer MONGO_PORT;

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        ImmutableMongodConfig mongodConfig = MongodConfig
                .builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(MONGO_HOST, MONGO_PORT, Network.localhostIsIPv6()))
                .build();

        LOGGER.info("Embedded MongoDB server started on {} port and {} host.", MONGO_PORT, MONGO_HOST);
        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongodExecutable mongodExe = starter.prepare(mongodConfig);
        MongodProcess mongod = mongodExe.start();

        MongoTemplate mongoTemplate;
        mongoTemplate = new MongoTemplate(
                MongoClients.create(String.format(CONNECTION_STRING, MONGO_HOST, MONGO_PORT)), "colourdb");
        return mongoTemplate;
    }

}