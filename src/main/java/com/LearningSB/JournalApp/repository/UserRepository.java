package com.LearningSB.JournalApp.repository;

import com.LearningSB.JournalApp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserName(String username);
}
