package com.example.twitter.kafka;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.social.twitter.api.StreamingOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterKafkaProducerTest {

    @Mock
    private Twitter twitter;

    @Mock
    private StreamingOperations streamingOperations;

    private TwitterKafkaProducer victim;

    @Before
    public void setUp() throws Exception {
        victim = new TwitterKafkaProducer();
        victim.setTwitter(twitter);
        when(twitter.streamingOperations()).thenReturn(streamingOperations);
    }

    @Test
    public void testTwitterKafkaProducer() {
        victim.startStream();
        verify(streamingOperations, times(1)).user(anyList());
    }

}