package com.LearningSB.JournalApp.controller;

import com.LearningSB.JournalApp.entity.User;
import com.LearningSB.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createAdminUser(@RequestBody User user){
        User saved = userService.saveAdmin(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/upgrade-to-admin")
    public ResponseEntity<?> upgradeToAdmin(@RequestBody User user){
        User existing = userService.findByUserName(user.getUserName());
        if(existing != null){
            User saved = userService.saveAdminUserName(existing);
            return new ResponseEntity<>(saved, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/remove-admin")
    public ResponseEntity<?> removeAdmin(@RequestBody User user){
        User admin = userService.findByUserName(user.getUserName());
        if(admin != null){
            List<String> roles = admin.getRoles();
            roles.remove("ADMIN");
            admin.setRoles(roles);
            User saved = userService.saveUser(admin);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
