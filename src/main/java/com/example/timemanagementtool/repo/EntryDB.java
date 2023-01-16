package com.example.timemanagementtool.repo;

import com.example.timemanagementtool.model.Entry;
import com.example.timemanagementtool.repo.dao.EntryDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class EntryDB implements EntryDao {
    ArrayList<Entry> entries = new ArrayList<>();

    @Override
    public boolean saveEntry(Entry entry){
        if (entry.getId().isEmpty()
                || entry.getDate() == null
                || entry.getProject().isEmpty()
                || entry.getDescription().isEmpty()
                || entry.getDuration() == 0){
            return false;
        } else {
            return entries.add(entry);
        }
    }

    @Override
    public boolean deleteEntry(String id){
        if (id.equals("")){
            return false;
        } else {
            entries.remove(findEntryById(id));
            return true;
        }
    }

    @Override
    public List<Entry> getAllEntries(){
        return entries;
    }

    @Override
    public Entry updateEntry(String id, Entry entry){
        var entryToUpdate = findEntryById(id);
        if (entryToUpdate.getId().isEmpty()
                || entryToUpdate.getProject().isEmpty()
                || entryToUpdate.getDescription().isEmpty()
                || entryToUpdate.getDate() == null
                || entryToUpdate.getDuration() == 0) {
            return null;
        } else {
            entryToUpdate.setProject(entry.getProject());
            entryToUpdate.setDescription(entry.getDescription());
            entryToUpdate.setDate(entry.getDate());
            entryToUpdate.setDuration(entry.getDuration());
            return entry;
        }
    }


    @Override
    public Entry findEntryById(String id) {
        if (id.equals("")){
            return null;
        } else {
            var optEntry = entries.stream()
                    .filter(entry -> Objects.equals(entry.getId(), id))
                    .findFirst();
            return optEntry.orElse(null);
        }
    }
}
