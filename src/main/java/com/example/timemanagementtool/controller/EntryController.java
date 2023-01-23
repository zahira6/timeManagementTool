package com.example.timemanagementtool.controller;


import com.example.timemanagementtool.model.Entry;
import com.example.timemanagementtool.service.EntryService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("profile/entry")
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
    ResponseEntity<Entry> updateEntry(
            @RequestBody Entry entry,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable String id) {
        if (token.isBlank()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            if (entryService.updateEntry(id, entry) != null){
                return ResponseEntity.ok(entry);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }

    @DeleteMapping
    ResponseEntity<String> deleteEntry(
            @RequestBody String id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        var entryId = id.substring(6).replace("\"", "");
        var entryId2 = entryId.substring(0, 36);
        if (token.isBlank()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            if(entryService.deleteEntry(entryId2)){
                entryId2 = "{\"id\":\"" + entryId2 +"}";
                JSONObject jsonDelete = new JSONObject();
                jsonDelete.put("Entry deleted successfully", entryId2);
                return ResponseEntity.ok(jsonDelete.toJSONString());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }
}
