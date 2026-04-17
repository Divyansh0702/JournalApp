package com.LearningSB.JournalApp.service;

import com.LearningSB.JournalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;


}
