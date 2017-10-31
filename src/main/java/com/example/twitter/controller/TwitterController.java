package com.example.twitter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @GetMapping("/getLimit")
    public ResponseEntity<Map<ResourceFamily, List<RateLimitStatus>>> getLimit() {
        return new ResponseEntity<>(twitter.userOperations().getRateLimitStatus(ResourceFamily.values()), HttpStatus.OK);
    }
}
