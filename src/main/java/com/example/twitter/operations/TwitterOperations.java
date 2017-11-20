package com.example.twitter.operations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.social.twitter.api.RateLimitStatus;
import org.springframework.social.twitter.api.ResourceFamily;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TwitterOperations {

    private static final Logger logger = LoggerFactory.getLogger(TwitterOperations.class);

    @Autowired
    private Twitter twitter;

    @Cacheable(cacheNames = "rateLimits", key = "#root.methodName")
    public Map<ResourceFamily, List<RateLimitStatus>> getLimits() {
        logger.info("getting rate limits");
        return twitter.userOperations().getRateLimitStatus(ResourceFamily.values());
    }

    public TwitterProfile getMyTwitterProfile() {
        return twitter.userOperations().getUserProfile();
    }

    public TwitterProfile getTwitterProfileById(long id) {
        return twitter.userOperations().getUserProfile(id);
    }

    public TwitterProfile getTwitterProfileByUsername(String username) {
        return twitter.userOperations().getUserProfile(username);
    }
}
