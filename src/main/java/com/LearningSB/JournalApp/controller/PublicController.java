package com.LearningSB.JournalApp.controller;

import com.LearningSB.JournalApp.entity.User;
import com.LearningSB.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/health-check")
    public ResponseEntity<?> healthCheck(){
        return new ResponseEntity<>("Public API is working", HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        User saved = userService.saveUser(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // If you want to test the POST request to the create-user endpoint using RestTemplate, you can do it like this (without using Postman):
    @GetMapping("/test-post")
    public ResponseEntity<?> testPost(){
        User user = new User();
        user.setUserName("test");
        user.setPassword("tst123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<User> response = restTemplate.postForEntity("http://localhost:8082/journal/public/create-user", entity, User.class);

        return response;
    }

    @PostMapping("/create-user-logger")
    public boolean createUserViaLogger(@RequestBody User user){
        return userService.saveUserViaLogger(user);
    }

}
