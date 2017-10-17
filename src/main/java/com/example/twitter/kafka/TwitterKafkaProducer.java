package com.example.twitter.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.FilterStreamParameters;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.UserStreamParameters;

import java.util.Arrays;
import java.util.List;

public class TwitterKafkaProducer {

    private Twitter twitter;

    private StreamListenerKafka streamListener;

    public void startStream() {
        twitter.streamingOperations().user(Arrays.asList(streamListener));
    }

    @Autowired
    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    @Autowired
    public void setStreamListener(StreamListenerKafka streamListener) {
        this.streamListener = streamListener;
    }
}
