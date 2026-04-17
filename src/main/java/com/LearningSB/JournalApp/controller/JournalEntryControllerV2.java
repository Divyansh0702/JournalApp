package com.LearningSB.JournalApp.controller;

import com.LearningSB.JournalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {


    @GetMapping
    public List<JournalEntry> getAll(){
        return null;
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        return true;
    }

    @GetMapping("/id/{entryId}")
    public JournalEntry getEntryById(@PathVariable String entryId){
        return null;
    }

    @DeleteMapping("/id/{entryId}")
    public JournalEntry deleteEntryById(@PathVariable String entryId){
        return null;
    }

    @PutMapping("/id/{entryId}")
    public JournalEntry updateEntryById(@PathVariable String entryId, @RequestBody JournalEntry myEntry){
        return null;
    }
}
