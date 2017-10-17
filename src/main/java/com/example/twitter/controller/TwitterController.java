package com.example.twitter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/")
public class TwitterController {

    @Autowired
    private Twitter twitter;

    @GetMapping("/getProfile/{id}")
    public ResponseEntity<TwitterProfile> getTwitterProfile(@PathVariable long id) {
        return new ResponseEntity<>(twitter.userOperations().getUserProfile(id), HttpStatus.OK);
    }
}
