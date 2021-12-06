package com.exam.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = {
		com.exam.demo.MongoDbTestConfiguration.class,
		com.exam.demo.TestRedisConfiguration.class,
		com.exam.demo.DemoApplication.class,
		com.exam.demo.KafkaConsumer.class,
		com.exam.demo.KafkaProducerConfig.class,
		com.exam.demo.KafkaConsumerConfig.class,
		com.exam.demo.ColourRepository.class,
		com.exam.demo.ColourServiceImp.class},
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = { "${kafka.topic_name}" })
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
class DemoApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
	private KafkaConsumer kafkaConsumer;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	EmbeddedKafkaBroker embeddedKafkaBroker;

	@Value(value="${kafka.topic_name}")
	private String TOPIC_NAME;

	@Autowired
	MongoTemplate mongoTemplate;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setup() throws Exception {
	}

	@After
	public void clean() {
	}

	@Test
	void contextLoads() throws InterruptedException {
		kafkaTemplate.send(TOPIC_NAME, "Red");
		kafkaTemplate.send(TOPIC_NAME, "Blue");
		kafkaTemplate.send(TOPIC_NAME, "Red");
		Thread.sleep(4000);

		String actualResponse =
				this.restTemplate
				.getForObject("http://localhost:" + port + "/api/", String.class);
		LOGGER.info("The response on the GET https://localhost/api/ endpoint is : {}", actualResponse);

		// The expected result is {"Red":2,"Blue":1} or {"Blue":1,"Red":2}
		assertTrue(actualResponse.equals("{\"Red\":2,\"Blue\":1}") || actualResponse.equals("{\"Blue\":1,\"Red\":2}"));
	}
}
