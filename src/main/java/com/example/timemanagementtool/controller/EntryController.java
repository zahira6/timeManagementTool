package com.example.timemanagementtool.controller;


import com.example.timemanagementtool.model.Entry;
import com.example.timemanagementtool.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    EntryService entryService;

    @GetMapping
    ResponseEntity<List<Entry>> getEntry(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (token.isBlank()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(entryService.getAllEntries());
    }

    @PostMapping
    ResponseEntity<Entry> postEntry(
            @RequestBody Entry entry,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (token.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(entryService.addEntry(entry));
    }

    @PutMapping("/{id}")
    ResponseEntity<String> updateEntry(
            @RequestBody Entry entry,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable String id) {
        if (token.isBlank()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            if (entryService.updateEntry(id, entry) != null){
                return ResponseEntity.ok("Data updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }

    @DeleteMapping
    ResponseEntity<String> deleteEntry(
            @RequestBody String id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (token.isBlank()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            if(entryService.deleteEntry(id)){
                return ResponseEntity.ok("Entry deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }
}
