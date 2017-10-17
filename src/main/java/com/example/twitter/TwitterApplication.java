package com.example.twitter;

import com.example.twitter.kafka.TwitterKafkaProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TwitterApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TwitterApplication.class, args);
		TwitterKafkaProducer producer = (TwitterKafkaProducer) context.getBean("kafkaProducer");
		producer.startStream();

	}
}
