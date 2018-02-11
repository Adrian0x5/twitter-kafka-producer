package com.example.twitter.config;

import com.example.twitter.kafka.KafkaStreamListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
@Import(KafkaConfig.class)
@ComponentScan("com.example.twitter.kafka")
@EnableCaching
public class TwitterConfig {

    @Autowired
    private Producer producer;

    @Value("${spring.social.twitter.app-id}")
    private String appId;

    @Value("${spring.social.twitter.app-secret}")
    private String appSecret;

    @Value("${spring.social.twitter.accessToken}")
    private String accessToken;

    @Value("${spring.social.twitter.accessTokenSecret}")
    private String accessTokenSecret;

    @Value("${kafka.tweets.create.topic}")
    private String createTopic;

    @Value("${kafka.tweets.delete.topic}")
    private String deleteEventTopic;

    @Bean
    public Twitter twitter() {
        return new TwitterTemplate(appId, appSecret, accessToken, accessTokenSecret);
    }

    @Bean
    public KafkaStreamListener streamListener() {
        KafkaStreamListener kafkaStreamListener = new KafkaStreamListener();
        kafkaStreamListener.setProducer(producer);
        kafkaStreamListener.setObjectMapper(objectMapper());
        kafkaStreamListener.setCreatedTweetsTopic(createTopic);
        kafkaStreamListener.setDeleteEventTopic(deleteEventTopic);
        return kafkaStreamListener;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
