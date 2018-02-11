package com.example.twitter.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;


public class KafkaStreamListener implements StreamListener {

    private static final Logger logger = LoggerFactory.getLogger(KafkaStreamListener.class);

    private Producer producer;

    private ObjectMapper objectMapper;

    private String createdTweetsTopic;
    private String deleteEventTopic;

    @Override
    public void onTweet(Tweet tweet) {
        try {
            logger.info(objectMapper.writeValueAsString(tweet));
            producer.send(new ProducerRecord(createdTweetsTopic,objectMapper.writeValueAsString(tweet)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDelete(StreamDeleteEvent deleteEvent) {

        try {
            logger.info(objectMapper.writeValueAsString(deleteEvent));
            producer.send(new ProducerRecord(deleteEventTopic, objectMapper.writeValueAsString(deleteEvent)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLimit(int numberOfLimitedTweets) {
        logger.info("onLimit: " + numberOfLimitedTweets);
    }

    @Override
    public void onWarning(StreamWarningEvent warningEvent) {
        try {
            logger.info("onWarning: " + objectMapper.writeValueAsString(warningEvent));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void setCreatedTweetsTopic(String createdTweetsTopic) {
        this.createdTweetsTopic = createdTweetsTopic;
    }

    public void setDeleteEventTopic(String deleteEventTopic) {
        this.deleteEventTopic = deleteEventTopic;
    }
}
