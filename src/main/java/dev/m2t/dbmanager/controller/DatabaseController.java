package dev.m2t.dbmanager.controller;

import dev.m2t.dbmanager.dto.BaseResponse;
import dev.m2t.dbmanager.dto.request.CreateDatabaseRequestDto;
import dev.m2t.dbmanager.dto.request.ListDatabasesRequestDto;
import dev.m2t.dbmanager.model.Database;
import dev.m2t.dbmanager.service.DatabaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/database")
public class DatabaseController {
    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Database>> createDatabase(@RequestBody CreateDatabaseRequestDto createDatabaseRequestDto) {
        return ResponseEntity.ok(databaseService.createDatabase(createDatabaseRequestDto));
    }

    @GetMapping("/read")
    public ResponseEntity<BaseResponse<Database>> getDatabase(@RequestParam Long id) {
        return ResponseEntity.ok(databaseService.getDatabase(id));
    }

    @PostMapping("/list")
    public ResponseEntity<BaseResponse<Page<Database>>> listDatabases(@RequestParam int page, @RequestParam int size, @RequestBody ListDatabasesRequestDto filter) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(databaseService.listDatabases(pageable, filter));
    }
}
