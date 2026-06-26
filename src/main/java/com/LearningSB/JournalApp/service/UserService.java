package com.LearningSB.JournalApp.service;

import com.LearningSB.JournalApp.entity.User;
import com.LearningSB.JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User saveUser(User user){
        if (user.getPassword() != null && !user.getPassword().matches("^\\$2[aby]\\$.+")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList("USER"));
        }
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
