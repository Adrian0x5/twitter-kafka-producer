package com.example.twitter.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class TwitterKafkaProducer {

    private Twitter twitter;

    private KafkaStreamListener streamListener;

    public void startStream() {
        twitter.streamingOperations().user(Collections.singletonList(streamListener));
    }

    @Autowired
    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    @Autowired
    public void setStreamListener(KafkaStreamListener streamListener) {
        this.streamListener = streamListener;
    }
}
