package com.LearningSB.JournalApp.controller;

import com.LearningSB.JournalApp.entity.JournalEntry;
import com.LearningSB.JournalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry((myEntry));
        return myEntry;
    }

    @GetMapping("/id/{entryId}")
    public JournalEntry getEntryById(@PathVariable ObjectId entryId){
        return journalEntryService.findById(entryId).orElse(null);
    }

    @DeleteMapping("/id/{entryId}")
    public void deleteEntryById(@PathVariable ObjectId entryId){
        journalEntryService.deleteById(entryId);
    }

    @PutMapping("/id/{entryId}")
    public JournalEntry updateEntryById(@PathVariable ObjectId entryId, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(entryId).orElse(null);

        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
}
