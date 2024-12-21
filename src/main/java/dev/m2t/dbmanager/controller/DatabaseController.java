package dev.m2t.dbmanager.controller;

import dev.m2t.dbmanager.dto.BaseResponse;
import dev.m2t.dbmanager.dto.request.CreateDatabaseRequestDto;
import dev.m2t.dbmanager.service.DatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/database")
public class DatabaseController {
    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createDatabase(@RequestBody CreateDatabaseRequestDto createDatabaseRequestDto) {
        return ResponseEntity.ok(databaseService.createDatabase(createDatabaseRequestDto));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getDatabase(@RequestParam String id) {
        return ResponseEntity.ok(databaseService.getDatabase(id));
    }
}
