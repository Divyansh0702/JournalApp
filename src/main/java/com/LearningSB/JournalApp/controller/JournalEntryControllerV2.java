package com.LearningSB.JournalApp.controller;

import com.LearningSB.JournalApp.entity.JournalEntry;
import com.LearningSB.JournalApp.entity.User;
import com.LearningSB.JournalApp.service.JournalEntryService;
import com.LearningSB.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
//    @GetMapping("/{userName}")
//    public ResponseEntity<?> getAllEntriesOfUser(@PathVariable String userName){
    public ResponseEntity<?> getAllEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userService.findByUserName(userName);

        List<JournalEntry> userJournalEntries = user.getJournalEntries();
        if(userJournalEntries != null && !userJournalEntries.isEmpty()){
            return new ResponseEntity<>(userJournalEntries, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User entries not found");
    }


    @PostMapping
//    @PostMapping("/{userName}")
//    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName){
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            journalEntryService.saveEntry(myEntry, userName);
            return ResponseEntity.status(HttpStatus.CREATED).body(myEntry);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/id/{entryId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId entryId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        Optional<JournalEntry> journalEntry = journalEntryService.findByUserName(userName)
                .stream()
                .filter(x -> x.getId().equals(entryId)).findFirst();

        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{entryId}")
//    @DeleteMapping("/{userName}/id/{entryId}")
//    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId entryId, @PathVariable String userName){
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId entryId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        boolean removed = journalEntryService.deleteById(entryId, userName);
        if(removed) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PutMapping("/{userName}/id/{entryId}")
    @PutMapping("/id/{entryId}")
    public ResponseEntity<?> updateEntryById(
            @PathVariable ObjectId entryId,
            @RequestBody JournalEntry newEntry
//            @PathVariable String userName
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        Optional<JournalEntry> journalEntry = journalEntryService.findByUserName(userName)
                .stream()
                .filter(x -> x.getId().equals(entryId)).findFirst();

//        JournalEntry old = journalEntryService.findById(entryId).orElse(null);
        if(journalEntry.isPresent()){
            JournalEntry old = journalEntry.get();
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());

            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
