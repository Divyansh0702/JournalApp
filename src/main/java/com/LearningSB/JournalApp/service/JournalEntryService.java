package com.LearningSB.JournalApp.service;

import com.LearningSB.JournalApp.entity.JournalEntry;
import com.LearningSB.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId entryId){
       return journalEntryRepository.findById(String.valueOf(entryId));
    }

    public void deleteById(ObjectId entryId){
        journalEntryRepository.deleteById(String.valueOf(entryId));
    }

}
