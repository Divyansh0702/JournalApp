package com.LearningSB.JournalApp.controller;

import com.LearningSB.JournalApp.entity.User;
import com.LearningSB.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUsersByUsername(@PathVariable String username){
        User user = userService.findByUserName(username);

        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username){
        User userInDB = userService.findByUserName(username);
        if(userInDB != null){
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.saveUser(userInDB);
            return new ResponseEntity<>(userInDB, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
