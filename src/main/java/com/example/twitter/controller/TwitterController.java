package com.example.twitter.controller;


import com.example.twitter.operations.TwitterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.RateLimitStatus;
import org.springframework.social.twitter.api.ResourceFamily;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class TwitterController {

    @Autowired
    private TwitterOperations twitterOperations;

    @GetMapping("/getProfileById/{id}")
    public ResponseEntity<TwitterProfile> getTwitterProfile(@PathVariable long id) {
        return new ResponseEntity<>(twitterOperations.getTwitterProfileById(id), HttpStatus.OK);
    }

    @GetMapping("/getProfileByUsername/{username}")
    public ResponseEntity<TwitterProfile> getTwitterProfileByUsername(@PathVariable String username) {
        return new ResponseEntity<>(twitterOperations.getTwitterProfileByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<TwitterProfile> getMyTwitterProfile() {
        return new ResponseEntity<>(twitterOperations.getMyTwitterProfile(), HttpStatus.OK);
    }

    @GetMapping("/getLimit")
    public ResponseEntity<Map<ResourceFamily, List<RateLimitStatus>>> getLimit() {
        return new ResponseEntity<>(twitterOperations.getLimits(), HttpStatus.OK);
    }
}
