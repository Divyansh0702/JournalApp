//package com.LearningSB.JournalApp.controller;
//
//import com.LearningSB.JournalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryController {
//
//    private Map<String, JournalEntry> journalEntries = new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry myEntry){
//        journalEntries.put(myEntry.getId(), myEntry);
//        return true;
//    }
//
//    @GetMapping("/id/{entryId}")
//    public JournalEntry getEntryById(@PathVariable String entryId){
//        return journalEntries.get(entryId);
//    }
//
//    @DeleteMapping("/id/{entryId}")
//    public JournalEntry deleteEntryById(@PathVariable String entryId){
//        return journalEntries.remove(entryId);
//    }
//
//    @PutMapping("/id/{entryId}")
//    public JournalEntry updateEntryById(@PathVariable String entryId, @RequestBody JournalEntry myEntry){
//        return journalEntries.put(entryId, myEntry);
//    }
//}
