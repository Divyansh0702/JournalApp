package com.LearningSB.JournalApp.service;

import com.LearningSB.JournalApp.entity.JournalEntry;
import com.LearningSB.JournalApp.entity.User;
import com.LearningSB.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        if(user == null){
            throw new RuntimeException("User not found");
        }
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);

        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId entryId){
       return journalEntryRepository.findById(String.valueOf(entryId));
    }

    public void deleteById(ObjectId entryId, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(entryId));
        userService.saveUser(user);

        journalEntryRepository.deleteById(String.valueOf(entryId));
    }

}
