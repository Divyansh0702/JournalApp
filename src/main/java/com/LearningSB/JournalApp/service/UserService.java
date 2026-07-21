package com.LearningSB.JournalApp.service;

import com.LearningSB.JournalApp.controller.JournalEntryControllerV2;
import com.LearningSB.JournalApp.entity.User;
import com.LearningSB.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean saveUserViaLogger(User user){
        try{
            if (user.getPassword() != null && !user.getPassword().matches("^\\$2[aby]\\$.+")) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (user.getRoles() == null || user.getRoles().isEmpty()) {
                user.setRoles(Arrays.asList("USER"));
            }
            userRepository.save(user);

            return true;
        } catch (Exception e){
            log.error("Error occured for {} : ", user.getUserName(), e);
            log.warn("Error occured as warn");
            log.info("Error occured as info");
            log.debug("Error occured as debug");
            log.trace("Error occured as trace");

            return false;
        }
    }

    public User saveUser(User user){
        if (user.getPassword() != null && !user.getPassword().matches("^\\$2[aby]\\$.+")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList("USER"));
        }
        return userRepository.save(user);
    }

    public User saveAdmin(User user){

        if (user.getPassword() != null && !user.getPassword().matches("^\\$2[aby]\\$.+")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList("USER", "ADMIN"));
        }
        return userRepository.save(user);
    }

    public User saveAdminUserName(User user){
        List<String> roles = user.getRoles();
        if(!roles.contains("ADMIN")){
            roles.add("ADMIN");
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId userId) {
        return userRepository.findById(String.valueOf(userId));
    }

    public void deleteById(ObjectId userId){
        userRepository.deleteById((String.valueOf(userId)));
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public User deleteByUserName(String userName){
        return userRepository.deleteByUserName(userName);
    }

}
