package com.LearningSB.JournalApp.controller;

import com.LearningSB.JournalApp.entity.JournalEntry;
import com.LearningSB.JournalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAllEntries(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try{
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry((myEntry));
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{entryId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId entryId){
        Optional<JournalEntry> journalEntry =  journalEntryService.findById(entryId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{entryId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId entryId){
        journalEntryService.deleteById(entryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{entryId}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId entryId, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(entryId).orElse(null);

        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
