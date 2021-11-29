package com.exam.demo;


import com.mongodb.DBObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

//https://jcompetence.com/2021/03/08/integration-testing-with-springboot-embedded-mongo-mockmvc/
//https://www.geekyhacker.com/2020/10/03/test-spring-kafka-consumer-and-producer-with-embeddedkafka/
//https://www.baeldung.com/spring-boot-embedded-mongodb

//https://jcompetence.com/2021/03/08/integration-testing-with-springboot-embedded-mongo-mockmvc/
//@DataMongoTest
//Use @AutoConfigureDataMongo with @SpringBootTest and this will resolve this ambiguity issue. @SpringBootTest and @DataMongoTest cannot be used together.
@AutoConfigureDataMongo
//@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ExtendWith(SpringExtension.class)
//@DirtiesContext
//@EmbeddedKafka
//@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
//@EmbeddedKafka(partitions = 1, topics = { "colour" })
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
	private MongoTemplate mongoTemplate;

/*
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@DisplayName("test somethig"
			+ " what i wanna test")
	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
	}

	private static final String TOPIC_NAME = "colour";

        @Autowired
        EmbeddedKafkaBroker embeddedKafkaBroker;

	@Autowired
	KafkaConsumer kafkaConsumer;

	/*
	@Test
	public void someTest(@EmbeddedKafkaAddress String bootstrapServers) {
		Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("consumer-1", "true", this.embeddedKafkaBroker);
		consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		ConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
		Consumer<Integer, String> consumer = cf.createConsumer();
		consumer.subscribe(Collections.singleton(TOPIC_NAME));

		//this.embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, TOPIC);
		ConsumerRecords<Integer, String> replies = KafkaTestUtils.getSingleRecord(consumer, TOPIC_NAME);
		//ConsumerRecords<Integer, String> replies = KafkaTestUtils.getRecords(consumer);
		assertThat(replies.count()).isGreaterThanOrEqualTo(1);
	}


            @Autowired
            private KafkaConsumer consumer;

            @Autowired
            private KafkaProducer producer;

            @Value("${kafka.topic_name}")
            private String topic;

            @Test
            public void givenEmbeddedKafkaBroker_whenSendingtoSimpleProducer_thenMessageReceived()
                    throws Exception {
                producer.send(topic, "Sending with own simple KafkaProducer");
                consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);

                assertThat(consumer.getLatch().getCount(), equalTo(0L));
                assertThat(consumer.getPayload(), containsString("embedded-test-topic"));
            }


            private Consumer<Integer, String> configureConsumer() {
                Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", embeddedKafkaBroker);
                consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
                Consumer<Integer, String> consumer = new DefaultKafkaConsumerFactory<Integer, String>(consumerProps).createConsumer();
                consumer.subscribe(Collections.singleton(TOPIC));
                return consumer;
            }

            private Producer<Integer, String> configureProducer() {
                Map<String, Object> producerProps = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
                return new DefaultKafkaProducerFactory<Integer, String>(producerProps).createProducer();
            }

            @Test
            public void givenEmbeddedKafkaBroker_whenSendingtoSimpleProducer_thenMessageReceived() {
                Consumer<Integer, String> consumer = configureConsumer();
                Producer<Integer, String> producer = configureProducer();

                producer.send(new ProducerRecord<>(TOPIC, 123, "my-test-value"));
                ConsumerRecord<Integer, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, TOPIC);

                assertThat(singleRecord).isNotNull();
                assertThat(singleRecord.key()).isEqualTo(123);
                assertThat(singleRecord.value()).isEqualTo("my-test-value");

                LOGGER.info("It is fine.");

                consumer.close();
                producer.close();
            }



	@Test
	void contextLoads() {
		String message = "WWW";
		producer.send(new ProducerRecord<>(TOPIC_NAME, 123, message));
		producer.flush();

		LOGGER.info("Message was: " + kafkaConsumer.getLastMessage());
	}

	private Producer<Integer, String> producer;

	@BeforeAll
	void setUp() {
		Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
		producer = new DefaultKafkaProducerFactory<Integer, String>(configs).createProducer();
	}

	@AfterAll
	void shutdown() {
		producer.close();
	}

	 */

	@Test
	//void contextLoads(@Autowired MongoTemplate mongoTemplate) {
	void contextLoads() {

		//mongoTemplate.findAll(DBObject.class, "collection");
	}
}
