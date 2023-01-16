package com.example.timemanagementtool.service;

import com.example.timemanagementtool.exceptions.EntryNotFoundException;
import com.example.timemanagementtool.model.Entry;
import com.example.timemanagementtool.repo.dao.EntryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EntryService {

    @Autowired
    EntryDao entryDB;

    public Entry addEntry(Entry entry){
        if (entryDB.saveEntry(entry)){
            entry.setId(UUID.randomUUID().toString());
            return entry;
        } else {
            return null;
        }
    }

    public boolean deleteEntry(String id){
        try {
            return entryDB.deleteEntry(id);
        } catch (EntryNotFoundException e){
            return false;
        }
    }

    public List<Entry> getAllEntries(){
        return entryDB.getAllEntries();
    }

    public Entry findEntryById(String id){
        try {
            return entryDB.findEntryById(id);
        } catch (EntryNotFoundException e){
            return null;
        }

    }

    public Entry updateEntry(String id, Entry entry){
        try {
            return entryDB.updateEntry(id, entry);
        } catch (EntryNotFoundException e){
            return null;
        }
    }


}
