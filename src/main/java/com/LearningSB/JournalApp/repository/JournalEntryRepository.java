package com.LearningSB.JournalApp.repository;

import com.LearningSB.JournalApp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {

}
