package com.example.timemanagementtool.testRepo;

import com.example.timemanagementtool.model.Entry;
import com.example.timemanagementtool.repo.EntryDB;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class testEntryDB {

    public Entry mockEntry = new Entry("a", "b", new Date(2022-4-5), 14, "203a516e-6b5d-432f-b931-6777e3edd756");
    public Entry mockEntry2 = new Entry("", "b", new Date(2022-4-5), 14, "203a516e-6b5d-432f-b931-6777e3edd756");
    public Entry mockEntry3 = new Entry("a", "b", new Date(2022-4-5), 14, "");


    @Test
    void testSaveEntryWithAllVariables(){
        // GIVEN
        var saveEntry = new EntryDB();
        // WHEN
        boolean b = saveEntry.saveEntry(mockEntry);
        // THEN
        assertTrue(b);
    }

    @Test
    void testSaveEntryWithProjectEmpty(){
        // GIVEN
        var saveEntry = new EntryDB();
        // WHEN
        boolean b = saveEntry.saveEntry(mockEntry2);
        // THEN
        assertFalse(b);
    }

    @Test
    void testDeleteEntryWithId(){
        // GIVEN
        var deleteEntry = new EntryDB();
        // WHEN
        boolean b = deleteEntry.deleteEntry(mockEntry.getId());
        // THEN
        assertTrue(b);
    }

    @Test
    void testDeleteEntryWithBlankId(){
        // GIVEN
        var deleteEntry = new EntryDB();
        // WHEN
        boolean b = deleteEntry.deleteEntry(mockEntry3.getId());
        // THEN
        assertFalse(b);
    }

}
