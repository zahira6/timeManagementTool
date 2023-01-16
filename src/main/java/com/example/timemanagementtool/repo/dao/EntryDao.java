package com.example.timemanagementtool.repo.dao;

import com.example.timemanagementtool.exceptions.EntryNotFoundException;
import com.example.timemanagementtool.model.Entry;

import java.util.List;

public interface EntryDao {
    boolean saveEntry(Entry entry);
    boolean deleteEntry(String id) throws EntryNotFoundException;
    List<Entry> getAllEntries();

    Entry updateEntry(String id, Entry entry) throws EntryNotFoundException;

    Entry findEntryById(String id) throws EntryNotFoundException;
}
