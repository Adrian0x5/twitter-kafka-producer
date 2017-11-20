package com.example.twitter;

import com.example.twitter.kafka.TwitterKafkaProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TwitterProducerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TwitterProducerApplication.class, args);
		TwitterKafkaProducer producer = context.getBean(TwitterKafkaProducer.class);
		producer.startStream();

	}
}
