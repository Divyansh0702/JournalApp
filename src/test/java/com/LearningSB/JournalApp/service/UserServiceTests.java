package com.LearningSB.JournalApp.service;

import com.LearningSB.JournalApp.entity.User;
import com.LearningSB.JournalApp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testAdd(){
        assertEquals(4, 2 + 2);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 5, 7",
            "7, 5, 10"
    })
    public void testAdd(int a, int b, int expected){
        assertEquals(expected, a + b);
    }

    @Test
    public void testFindByUserName(){
        User user = userRepository.findByUserName("Divyansh");
        assertTrue(!user.getJournalEntries().isEmpty());
//      assertNotNull(userRepository.findByUserName("Divyansh"));
//      assertTrue(5 >= 5);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Divyansh",
            "Manasvi",
            "Rohit"
    })
    public void testFindByUserName(String name) {
        assertNotNull(userRepository.findByUserName(name), "failed for " + name);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveUser(User user) {
        String rawPassword = user.getPassword();

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertNotEquals(rawPassword, savedUser.getPassword());
        assertTrue(passwordEncoder.matches(rawPassword, savedUser.getPassword()));
        assertTrue(savedUser.getRoles().contains("USER"));
    }

}
