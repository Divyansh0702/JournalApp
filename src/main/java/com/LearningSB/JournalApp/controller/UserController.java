package com.LearningSB.JournalApp.controller;

import com.LearningSB.JournalApp.api.response.WeatherResponse;
import com.LearningSB.JournalApp.entity.User;
import com.LearningSB.JournalApp.service.UserService;
import com.LearningSB.JournalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User userInDB = userService.findByUserName(userName);
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());

        User updatedUser = userService.saveUser(userInDB);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherRespoonse = weatherService.getWeather("Noida");

        String greeting = "";
        if(weatherRespoonse != null){
            greeting = ". Today's temperature in Noida is " + weatherRespoonse.getCurrent().getFeelslike() + "°C with " + weatherRespoonse.getCurrent().getWeather_descriptions().get(0) + " weather.";
        }
        return new ResponseEntity<>("Hello, " + authentication.getName() + greeting ,HttpStatus.OK);
    }

// For Admin Only:
//    @GetMapping
//    public List<User> getAllUsers(){
//        return userService.getAll();
//    }
//    @GetMapping("/{userName}")
//    public ResponseEntity<?> getUserByUsername(@PathVariable String userName){
//        User user = userService.findByUserName(userName);
//
//        if(user != null){
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

// Sent to PublicController.java
//    @PostMapping
//    public void createUser(@RequestBody User user){
//        userService.saveUser(user);
//    }


}
