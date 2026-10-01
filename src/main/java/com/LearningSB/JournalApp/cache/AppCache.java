package com.LearningSB.JournalApp.cache;

import com.LearningSB.JournalApp.entity.ConfigJournalApp;
import com.LearningSB.JournalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCacheMap;

    @PostConstruct
    public void init(){
        appCacheMap = new HashMap<>();
        List<ConfigJournalApp> all =  configJournalAppRepository.findAll();
        for(ConfigJournalApp configJournalApp : all){
            appCacheMap.put(configJournalApp.getKey(), configJournalApp.getValue());
        }

    }
}
