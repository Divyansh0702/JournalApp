package com.LearningSB.JournalApp.controller;

import com.LearningSB.JournalApp.entity.User;
import com.LearningSB.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
