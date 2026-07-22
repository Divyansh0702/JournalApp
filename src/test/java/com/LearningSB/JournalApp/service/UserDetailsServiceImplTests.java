package com.LearningSB.JournalApp.service;

import com.LearningSB.JournalApp.entity.User;
import com.LearningSB.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {

    @InjectMocks
    private  UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Disabled
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Disabled
    @Test
    void loadUserByUsernameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(User.builder()
                        .userName("Divyansh")
                        .password("password")
                        .roles(new ArrayList<>())
                        .build());
        UserDetails user = userDetailsService.loadUserByUsername("Divyansh");
        Assertions.assertNotNull(user);
    }
}
